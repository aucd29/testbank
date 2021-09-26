package com.example.testbank.view.main.service

import androidx.databinding.ObservableField
import com.example.testbank.base.BaseViewModel
import com.example.testbank.repository.HiltRepositoryManager
import com.example.testbank.repository.RepositoryInterface
import com.example.testbank.repository.local.model.service.BaseServiceModel
import com.example.testbank.repository.local.model.service.ServiceSubjectModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(
    @HiltRepositoryManager
    private val repositoryManager: RepositoryInterface
) : BaseViewModel() {
    val items = ObservableField<List<BaseServiceModel>>()
    val tabIndexes = ObservableField<List<Int>>()

    fun init() {
        disposable += repositoryManager.serviceMenus()
            .map {
                val indexes = arrayListOf<Int>()
                if (it.isNotEmpty()) {
                    var oldPos = 0
                    it.forEachIndexed { index, data ->
                        if (data is ServiceSubjectModel) {
                            indexes += if (oldPos == 0) {
                                index - oldPos + 1
                            } else {
                                index - oldPos - 1
                            }

                            oldPos = index
                        }
                    }
                    indexes += (it.size - oldPos)

                    Timber.d("[SERVICE] indexes $indexes")
                }
                tabIndexes.set(indexes)

                it
            }
            .subscribe(items::set, Timber::e)
    }
}