package com.dhxxn.dogterestapp.view.random

import androidx.compose.runtime.State
import com.dhxxn.dogterestapp.view.base.BaseAction
import com.dhxxn.dogterestapp.view.base.BaseEffect
import com.dhxxn.dogterestapp.view.base.BaseState

class RandomContract {
    data class RandomState(
        val randomImg: State<String>
    ): BaseState

    sealed class RandomAction : BaseAction {
        object RequestNewImage: RandomAction()
    }

    sealed class RandomEffect: BaseEffect {
        data class ToastMessage(val message: String): RandomEffect()
    }
}