package com.example.testbank.repository.local.model.alarm

import androidx.databinding.ObservableBoolean
import com.example.testbank.base.adapter.ListAdapterViewType

open class BaseAlarmModel (
    var id: Int,
    override val viewType: Int
) : ListAdapterViewType<Int> {
    override fun diff(): Int =
        id

    companion object {
        const val TYPE_SUBJECT = 0
        const val TYPE_MENU = 1
    }
}

class AlarmSubjectModel(
    id: Int,
    val title: String,
) : BaseAlarmModel(id, TYPE_SUBJECT)

class AlarmMenuModel(
    id: Int,
    val title: String,
    val icon: String,
    val description: String,
    val date: String,
    val expand: ObservableBoolean = ObservableBoolean(false),
    val mores: List<AlarmDeposit>? = null
) : BaseAlarmModel(id, TYPE_MENU) {
    fun toggle() {
        expand.set(!expand.get())
    }
}

data class AlarmDeposit(
    val type: String,
    val value: String,
    val name: String,
    val date: String,
)