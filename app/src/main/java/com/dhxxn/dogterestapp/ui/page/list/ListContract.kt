package com.dhxxn.dogterestapp.ui.page.list

import com.dhxxn.dogterestapp.ui.base.BaseUiAction
import com.dhxxn.dogterestapp.ui.base.BaseUiEffect
import com.dhxxn.dogterestapp.ui.base.BaseUiState
import com.dhxxn.dogterestapp.ui.base.DogStateList

class ListContract {
    data class ListState(
        val imageList: DogStateList<String>
    ): BaseUiState

    sealed class Action: BaseUiAction {

    }

    sealed class Effect: BaseUiEffect {

    }
}