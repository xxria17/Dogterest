package com.dhxxn.dogterestapp.ui.page.like

import android.util.Log
import com.dhxxn.dogterestapp.ui.base.BaseUiAction
import com.dhxxn.dogterestapp.ui.base.BaseUiState
import com.dhxxn.dogterestapp.ui.base.BaseViewModel
import com.dhxxn.domain.common.model.Like
import com.dhxxn.domain.common.usecase.LikeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor(
    private val likeDataUseCase: LikeUseCase
) : BaseViewModel() {

    val state: LikeContract.LikeState
        get() = state()

    val effect: Flow<LikeContract.LikeEffect>
        get() = effect()

    private val compositeDisposable = CompositeDisposable()

    init {
        initialData()
        loadData()
    }

    override fun loadData() {
        requestAllLikeDogs()
    }

    private fun requestAllLikeDogs() {
        compositeDisposable.add(
            likeDataUseCase.requestAllLikeDogs()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ _result ->
                    if (_result.isNullOrEmpty()) {
                        Log.e("LikeViewModel", "requestAllLikes null or Empty")
                    } else {
                        state.likeImgList.sendState { _result }
                    }
                }, {
                    Log.e("LikeViewModel", "requestAllLikes error : ${it.message}")
                })
        )
    }

    private fun deleteLike(data: Like) {
        likeDataUseCase.deleteLikeDog(data)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                sendEffect(
                    LikeContract.LikeEffect.ShowToast("삭제되었습니다!")
                )
            }, {
                Log.e("LikeViewModel", "deleteError : ${it.message}")
                sendEffect(LikeContract.LikeEffect.ShowToast("삭제하는데 실패하였습니다. 다시 한번 시도해주세요."))
            })
    }

    override fun initialData() {
        state.likeImgList.sendState { emptyList() }
    }

    override fun handleEvents(action: BaseUiAction) {
        when (action) {
            is LikeContract.LikeAction.DeleteLike -> {
                deleteLike(action.data)
            }
        }
    }

    override fun initialState(): BaseUiState {
        return LikeContract.LikeState(
            likeImgList = mutableDogStateListOf(emptyList())
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}