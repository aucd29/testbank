package com.example.testbank.repository.remote

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
            on { image(any(), any(), any(), any()) } doReturn Single.just(dummyImageDto())
            on { vclip(any(), any(), any(), any()) } doReturn Single.just(dummyVclipDto())
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
            on { image(any(), any(), any(), any()) } doReturn Single.just(dummyImageDto(true))
            on { vclip(any(), any(), any(), any()) } doReturn Single.just(dummyVclipDto())
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
            on { image(any(), any(), any(), any()) } doReturn Single.just(dummyImageDto())
            on { vclip(any(), any(), any(), any()) } doReturn Single.just(dummyVclipDto(true))
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
        searchService = mock {
            on { image(any(), any(), any(), any()) } doReturn Single.just(dummyImageInvalidArgumentDto())
            on { vclip(any(), any(), any(), any()) } doReturn Single.just(dummyVclipDto())
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
        searchService = mock {
            on { image(any(), any(), any(), any()) } doReturn Single.just(dummyImageInvalidArgumentDto())
            on { vclip(any(), any(), any(), any()) } doReturn Single.just(dummyVclipInvalidArgumentDto())
        }

        // when
        val result = repository.search("1", 1).blockingGet()

        // then
        expect(result.isImageEnd).toBe(true)
        expect(result.isVideoEnd).toBe(true)
        expect(result.list?.size).toBe(0)
    }

    private fun dummyImageDto(isEnd: Boolean = false) = KakaoImageSearchDto(
        documents = listOf(KakaoImageResult(collection = "",
            thumbnail_url = "1",
            image_url = null,
            width = 0,
            height = 0,
            display_sitename = null,
            doc_url = null,
            datetime = "2017-07-24T08:10:00.000+09:00"
        ),KakaoImageResult(collection = "",
            thumbnail_url = "2",
            image_url = null,
            width = 0,
            height = 0,
            display_sitename = null,
            doc_url = null,
            datetime = "2019-07-03T07:30:00.000+09:00"
        )),
        meta = KakaoMetaResult(
            is_end = isEnd,
            pageable_count = 1,
            total_count = 2
        )
    )

    private fun dummyImageInvalidArgumentDto() = KakaoImageSearchDto(
        documents = null,
        meta = null,
        errorType = "InvalidArgument",
        message = "size is more than max"
    )

    private fun dummyVclipDto(isEnd: Boolean = false) = KakaoVClipSearchDto(
        documents = listOf(KakaoVClipResult(title = "",
            url = "",
            datetime = "2021-08-27T17:15:36.000+09:00",
            play_time = 0,
            thumbnail = "3",
            author = ""
        ),KakaoVClipResult(title = "",
            url = "",
            datetime = "2017-07-03T07:30:00.000+09:00",
            play_time = 0,
            thumbnail = "4",
            author = ""
        )),
        meta = KakaoMetaResult(
            is_end = isEnd,
            pageable_count = 1,
            total_count = 2
        )
    )

    private fun dummyVclipInvalidArgumentDto() = KakaoVClipSearchDto(
        documents = null,
        meta = null,
        errorType = "InvalidArgument",
        message = "size is more than max"
    )
}