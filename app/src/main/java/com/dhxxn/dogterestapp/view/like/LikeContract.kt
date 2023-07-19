package com.dhxxn.dogterestapp.view.like

import com.dhxxn.dogterestapp.view.base.BaseUiAction
import com.dhxxn.dogterestapp.view.base.BaseUiEffect
import com.dhxxn.dogterestapp.view.base.BaseUiState
import com.dhxxn.dogterestapp.view.base.DogStateList
import com.dhxxn.domain.common.model.Like

class LikeContract {

    data class LikeState(
        val likeImgList: DogStateList<Like>
    ): BaseUiState

    sealed class LikeAction: BaseUiAction {
        data class DeleteLike(val data: Like): LikeAction()
    }

    sealed class LikeEffect: BaseUiEffect {
        data class ShowToast(val msg: String): LikeEffect()
    }
}