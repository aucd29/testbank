package com.example.testbank.view.main.service

import com.example.testbank.base.BaseViewModelTest
import com.example.testbank.repository.RepositoryInterface
import com.example.testbank.repository.dummy.DummyRepository
import com.example.testbank.repository.local.model.service.ServiceSubjectModel
import com.nhaarman.expect.expect
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Test

@Suppress("NonAsciiCharacters")
class TestServiceViewModel : BaseViewModelTest<ServiceViewModel>() {
    override fun createViewModel(): ServiceViewModel =
        ServiceViewModel(repository)

    var repository = mock<RepositoryInterface>()

    @Test
    fun `emptyList 반환시 초기화`() {
        // given
        repository = mock {
            on { serviceMenus() } doReturn Single.fromCallable { emptyList() }
        }

        // when
        viewmodel.init()

        // then
        expect(viewmodel.items.get()?.size).toBe(0)
        expect(viewmodel.tabIndexes.get()?.size).toBe(0)
    }

    @Test
    fun `Throwable 반환시 초기화`() {
        // given
        repository = mock {
            on { serviceMenus() } doReturn Single.error(Throwable("error"))
        }

        // when
        viewmodel.init()

        // then
        expect(viewmodel.items.get()).toBeNull()
        expect(viewmodel.tabIndexes.get()).toBeNull()
    }

    @Test
    fun `dummy 초기화`() {
        // given
        val dummyRepository = DummyRepository()
        val size = dummyRepository.serviceMenus().blockingGet().size

        repository = mock {
            on { serviceMenus() } doReturn dummyRepository.serviceMenus()
        }

        // when
        viewmodel.init()

        // then
        expect(viewmodel.items.get()?.size).toBe(size)
    }

    @Test
    fun `dummy 초기화 indexes 개수`() {
        // given
        val dummyRepository = DummyRepository()
        val size = dummyRepository.serviceMenus().blockingGet().count { it is ServiceSubjectModel } + 1

        repository = mock {
            on { serviceMenus() } doReturn dummyRepository.serviceMenus()
        }

        // when
        viewmodel.init()

        // then
        expect(viewmodel.tabIndexes.get()?.size).toBe(size)
    }
}