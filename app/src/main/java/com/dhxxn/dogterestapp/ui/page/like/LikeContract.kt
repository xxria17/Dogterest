package com.dhxxn.dogterestapp.ui.page.like

import com.dhxxn.dogterestapp.ui.base.BaseUiAction
import com.dhxxn.dogterestapp.ui.base.BaseUiEffect
import com.dhxxn.dogterestapp.ui.base.BaseUiState
import com.dhxxn.dogterestapp.ui.base.DogStateList
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