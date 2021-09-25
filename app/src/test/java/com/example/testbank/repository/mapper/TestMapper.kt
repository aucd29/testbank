package com.example.testbank.repository.mapper

import com.example.testbank.base.dummy.Dummy
import com.markmount.wadiz.base.BaseTest
import com.nhaarman.expect.expect
import org.junit.Test

@Suppress("NonAsciiCharacters")
class TestMapper : BaseTest() {
    @Test
    fun `imageDto 데이터 변환`() {
        // given
        val dto = Dummy.imageDto()

        // when
        val result = dto.toSearchModels()

        // then
        expect(result?.first()?.thumbnailUrl).toBe("1")
        expect(result?.first()?.displaySiteName).toBe("display_sitename")
        expect(result?.first()?.datetime).toBe("2017-07-24T08:10:00.000+09:00")
        expect(result?.first()?.url).toBe("doc_url")
    }

    @Test
    fun `vclipDto 데이터 변환`() {
        // given
        val dto = Dummy.vclipDto()

        // when
        val result = dto.toSearchModels()

        // then
        expect(result?.first()?.thumbnailUrl).toBe("3")
        expect(result?.first()?.displaySiteName).toBe("author")
        expect(result?.first()?.datetime).toBe("2021-08-27T17:15:36.000+09:00")
        expect(result?.first()?.url).toBe("url")
    }
}