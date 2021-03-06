package com.example.testbank.view.main

import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import com.example.testbank.base.BaseViewModelTest
import com.example.testbank.base.dummy.Dummy
import com.nhaarman.expect.expect
import org.junit.Test

@Suppress("NonAsciiCharacters")
class TestMainViewModel : BaseViewModelTest<MainViewModel>() {
    override fun createViewModel(): MainViewModel =
        MainViewModel()

    @Test
    fun `기본 토글`() {
        // given
        val model = Dummy.searchModel()

        // when
        viewmodel.toggleLike(model)

        // then
        expect(viewmodel.likeItems.value?.size).toBe(1)
        expect(viewmodel.likeItems.value?.first()?.like?.get()).toBe(true)
    }

    @Test
    fun `토글 연속 하면 삭제`() {
        // given
        val model = Dummy.searchModel()

        // when
        viewmodel.toggleLike(model)
        viewmodel.toggleLike(model)

        // then
        expect(viewmodel.likeItems.value?.firstOrNull()).toBeNull()
    }

    @Test
    fun `토글 옵저브`() {
        // given
        val model = Dummy.searchModel()

        // when
        viewmodel.toggleLike(model)

        // then
        val result = viewmodel.likeItems.getOrAwaitValue()
        expect(result.first()).toBe(model)
    }
}