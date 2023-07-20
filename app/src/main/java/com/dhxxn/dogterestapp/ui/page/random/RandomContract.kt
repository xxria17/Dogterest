package com.dhxxn.dogterestapp.ui.page.random

import com.dhxxn.dogterestapp.ui.base.BaseUiAction
import com.dhxxn.dogterestapp.ui.base.BaseUiEffect
import com.dhxxn.dogterestapp.ui.base.BaseUiState
import com.dhxxn.dogterestapp.ui.base.DogState

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