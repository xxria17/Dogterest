package com.dhxxn.dogterestapp.ui.page.detail

import com.dhxxn.dogterestapp.ui.base.BaseUiAction
import com.dhxxn.dogterestapp.ui.base.BaseUiEffect
import com.dhxxn.dogterestapp.ui.base.BaseUiState
import com.dhxxn.dogterestapp.ui.base.DogState

class DetailContract {

    data class DetailState(
        val image: DogState<String>,
        val breed: DogState<String>
    ): BaseUiState

    sealed class Action: BaseUiAction {
        data class DownloadDevice(val imageUrl: String): Action()

        data class LikeImage(val imageUrl: String): Action()
    }

    sealed class Effect: BaseUiEffect {
        data class ShowToast(val msg: String): Effect()

        data class DownloadDevice(val imageUrl: String): Effect()
    }

}