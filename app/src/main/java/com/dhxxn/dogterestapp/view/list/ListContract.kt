package com.dhxxn.dogterestapp.view.list

import com.dhxxn.dogterestapp.view.base.BaseUiAction
import com.dhxxn.dogterestapp.view.base.BaseUiEffect
import com.dhxxn.dogterestapp.view.base.BaseUiState
import com.dhxxn.dogterestapp.view.base.DogStateList

class ListContract {
    data class ListState(
        val imageList: DogStateList<String>
    ): BaseUiState

    sealed class Action: BaseUiAction {

    }

    sealed class Effect: BaseUiEffect {

    }
}