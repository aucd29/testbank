package com.example.testbank.repository.remote

import com.example.testbank.base.dummy.Dummy
import com.example.testbank.repository.local.model.search.SearchModel
import com.example.testbank.repository.remote.dto.*
import com.example.testbank.repository.remote.service.KakaoRestSearchService
import com.markmount.wadiz.base.BaseTest
import com.nhaarman.expect.expect
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Test

@Suppress("NonAsciiCharacters")
class TestRemoteRepository : BaseTest() {
    val repository: RemoteRepository by lazy { RemoteRepository(searchService) }
    var searchService = mock<KakaoRestSearchService>()

    @Test
    fun `검색과 우선순위 변경 확인`() {
        // given
        searchService = mock {
            on { image(any(), any(), any(), any()) } doReturn Single.just(Dummy.imageDto())
            on { vclip(any(), any(), any(), any()) } doReturn Single.just(Dummy.vclipDto())
        }

        // when
        val result = repository.search("1", 1).blockingGet()

        // then
        expect(result.list?.size).toBe(4)
        expect(result.list?.first() is SearchModel).toBe(true)
        expect(result.list?.first()?.thumbnailUrl).toBe("3")
        expect(result.list?.get(1)?.thumbnailUrl).toBe("2")
        expect(result.list?.get(2)?.thumbnailUrl).toBe("1")
        expect(result.list?.get(3)?.thumbnailUrl).toBe("4")
    }

    @Test
    fun `image 결과 중 isEnd 값 true`() {
        // given
        searchService = mock {
            on { image(any(), any(), any(), any()) } doReturn Single.just(Dummy.imageDto(true))
            on { vclip(any(), any(), any(), any()) } doReturn Single.just(Dummy.vclipDto())
        }

        // when
        val result = repository.search("1", 1).blockingGet()

        // then
        expect(result.isImageEnd).toBe(true)
        expect(result.isVideoEnd).toBe(false)
    }

    @Test
    fun `vclip 결과 중 isEnd 값 true`() {
        // given
        searchService = mock {
            on { image(any(), any(), any(), any()) } doReturn Single.just(Dummy.imageDto())
            on { vclip(any(), any(), any(), any()) } doReturn Single.just(Dummy.vclipDto(true))
        }

        // when
        val result = repository.search("1", 1).blockingGet()

        // then
        expect(result.isImageEnd).toBe(false)
        expect(result.isVideoEnd).toBe(true)
    }

    @Test
    fun `image 결과 invalid argument`() {
        // given
        val imageDto = mock<KakaoImageSearchDto> {
            on { errorType } doReturn "InvalidArgument"
            on { message } doReturn "page is more than max"
        }
        searchService = mock {
            on { image(any(), any(), any(), any()) } doReturn Single.just(imageDto)
            on { vclip(any(), any(), any(), any()) } doReturn Single.just(Dummy.vclipDto())
        }

        // when
        val result = repository.search("1", 1).blockingGet()

        // then
        expect(result.isImageEnd).toBe(true)
        expect(result.isVideoEnd).toBe(false)
        expect(result.list?.size).toBe(2)
    }

    @Test
    fun `image 와 vclip 결과 invalid argument`() {
        // given
        val imageDto = mock<KakaoImageSearchDto> {
            on { errorType } doReturn "InvalidArgument"
            on { message } doReturn "page is more than max"
        }
        val vclipDto = mock<KakaoVClipSearchDto> {
            on { errorType } doReturn "InvalidArgument"
            on { message } doReturn "page is more than max"
        }
        searchService = mock {
            on { image(any(), any(), any(), any()) } doReturn Single.just(imageDto)
            on { vclip(any(), any(), any(), any()) } doReturn Single.just(vclipDto)
        }

        // when
        val result = repository.search("1", 1).blockingGet()

        // then
        expect(result.isImageEnd).toBe(true)
        expect(result.isVideoEnd).toBe(true)
        expect(result.list?.size).toBe(0)
    }

    @Test
    fun `image 결과 exception`() {
        // given
        val throwable = Throwable("error")
        searchService = mock {
            on { image(any(), any(), any(), any()) } doReturn Single.error(throwable)
            on { vclip(any(), any(), any(), any()) } doReturn Single.just(Dummy.vclipDto())
        }

        // when
        val result = repository.search("1", 1).test()

        // then
        result.assertError(throwable)
    }
}