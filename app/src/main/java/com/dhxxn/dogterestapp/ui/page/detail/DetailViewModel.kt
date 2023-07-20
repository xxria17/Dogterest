package com.dhxxn.dogterestapp.ui.page.detail

import android.util.Log
import com.dhxxn.dogterestapp.ui.base.BaseUiAction
import com.dhxxn.dogterestapp.ui.base.BaseUiState
import com.dhxxn.dogterestapp.ui.base.BaseViewModel
import com.dhxxn.domain.common.model.Like
import com.dhxxn.domain.common.usecase.LikeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val likeDataUseCase: LikeUseCase
): BaseViewModel() {

    val state: DetailContract.DetailState
        get() = state()

    val effect: Flow<DetailContract.Effect>
        get() = effect()

    override fun loadData() {

    }

    override fun initialData() {

    }

    override fun handleEvents(action: BaseUiAction) {
        when (action) {
            is DetailContract.Action.DownloadDevice -> {
                sendEffect(
                    DetailContract.Effect.DownloadDevice(action.imageUrl)
                )
            }
            is DetailContract.Action.LikeImage -> {
                addLikeDog(action.imageUrl)
            }
        }
    }

    override fun initialState(): BaseUiState {
        return DetailContract.DetailState(
            image = mutableDogStateOf(""),
            breed = mutableDogStateOf("")
        )
    }

    private fun addLikeDog(image: String) {
        likeDataUseCase.addLikeDog(Like(imageUrl = image))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                sendEffect(
                    DetailContract.Effect.ShowToast("내 컬렉션에 저장되었습니다")
                )
            }, {
                Log.e("DetailViewModel", "addLike Error :${it.message}")
                DetailContract.Effect.ShowToast("실패하였습니다. 다시 한번 시도해주세요.")
            })
    }
}