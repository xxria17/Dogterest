package com.dhxxn.dogterestapp.view.list

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.dhxxn.dogterestapp.view.base.BaseAction
import com.dhxxn.dogterestapp.view.base.BaseEffect
import com.dhxxn.dogterestapp.view.base.BaseState

class ListContract {
    data class ListState(
        val imageList: SnapshotStateList<String>
    ): BaseState

    sealed class Action: BaseAction {

    }

    sealed class Effect: BaseEffect {

    }
}