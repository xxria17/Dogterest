package com.dhxxn.dogterestapp.view.random

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhxxn.dogterestapp.model.DogUiModel
import com.dhxxn.dogterestapp.model.toUiModel
import com.dhxxn.domain.common.ResultWrapper
import com.dhxxn.domain.common.model.Dog
import com.dhxxn.domain.common.succeeded
import com.dhxxn.domain.common.usecase.RandomDogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomViewModel @Inject constructor(
    private val randomDogUseCase: RandomDogUseCase
): ViewModel()  {

    private val _randomDog = MutableStateFlow<DogUiModel?>(null)
    val randomDog = _randomDog.asStateFlow()

    private var job: Job? = null
    fun requestRandomDogData() {
        if (job != null && job?.isActive == true) return

        job = viewModelScope.launch {
            val result = randomDogUseCase.invoke()
            if (result.succeeded) {
                emitSuccessData(result)
            } else {

            }
            Log.d("!!!!!", "result : ${randomDog.value?.imageUrl}")
        }
    }

    private suspend fun emitSuccessData(result: ResultWrapper<Dog>) {
        val data = result.data
        if (data == null && randomDog == null) {

        }

        data?.let {
            _randomDog.value = it.toUiModel()
        }
    }
}