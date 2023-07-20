package com.dhxxn.dogterestapp.ui.page.random

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.dhxxn.dogterestapp.ui.base.BaseUiAction
import com.dhxxn.dogterestapp.ui.base.BaseUiState
import com.dhxxn.dogterestapp.ui.base.BaseViewModel
import com.dhxxn.domain.common.NetworkResponse
import com.dhxxn.domain.common.model.Dog
import com.dhxxn.domain.common.usecase.RandomDogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomViewModel @Inject constructor(
    private val randomDogUseCase: RandomDogUseCase
): BaseViewModel()  {

    val state: RandomContract.RandomState
        get() = state()

    private var job: Job? = null
    private fun requestRandomDogData() {
        if (job != null && job?.isActive == true) return

        job = viewModelScope.launch {
            val response : NetworkResponse<Dog> = randomDogUseCase.requestRandomImage()
            when (response) {
                is NetworkResponse.Success -> {
                    state.randomImg.sendState {
                        response.body.imageUrl
                    }
                }
                else -> {
                    Log.d("RandomViewModel", "!!!! error ${response}")
                }
            }
        }
    }

    init {
        initialData()
        loadData()
    }

    override fun loadData() {
        requestRandomDogData()
    }

    override fun initialData() {
        state.randomImg.sendState { "" }
    }

    override fun handleEvents(action: BaseUiAction) {
        when (action) {
            is RandomContract.RandomAction.RequestNewImage -> {
                requestRandomDogData()
            }
        }
    }

    override fun initialState(): BaseUiState {
        return RandomContract.RandomState(
            randomImg = mutableDogStateOf("")
        )
    }
}