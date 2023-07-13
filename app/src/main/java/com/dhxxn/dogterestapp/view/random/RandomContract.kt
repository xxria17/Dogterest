package com.dhxxn.dogterestapp.view.random

import com.dhxxn.dogterestapp.view.base.BaseUiAction
import com.dhxxn.dogterestapp.view.base.BaseUiEffect
import com.dhxxn.dogterestapp.view.base.BaseUiState
import com.dhxxn.dogterestapp.view.base.DogState

class RandomContract {
    data class RandomState(
        val randomImg: DogState<String>
    ): BaseUiState

    sealed class RandomAction : BaseUiAction {
        object RequestNewImage: RandomAction()
    }

    sealed class RandomEffect: BaseUiEffect {
        data class ToastMessage(val message: String): RandomEffect()
    }
}