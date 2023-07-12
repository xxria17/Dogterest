package com.dhxxn.dogterestapp.view.list

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhxxn.dogterestapp.view.base.BaseAction
import com.dhxxn.dogterestapp.view.base.BaseState
import com.dhxxn.dogterestapp.view.base.BaseViewModel
import com.dhxxn.dogterestapp.view.random.RandomContract
import com.dhxxn.domain.common.NetworkResponse
import com.dhxxn.domain.common.usecase.ListDogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val listDogUseCase: ListDogUseCase
): BaseViewModel() {

    private val PAGE_SIZE = 30
    var offset: Int = PAGE_SIZE
    private var job: Job? = null

    val state: ListContract.ListState
        get() = state()

    private fun requestListDogData() {
        if (job != null && job?.isActive == true) return

        job = viewModelScope.launch {
            val response = listDogUseCase.invoke(offset)
            when (response) {
                is NetworkResponse.Success -> {
                    state.imageList.sendState { response.body.list }
                    offset += PAGE_SIZE
                }
                else -> {
                    Log.d("ListViewModel", "!!!! error ${response}")
                }
            }
        }
    }

    override fun loadData() {
        requestListDogData()
    }

    override fun initialData() {
        state.imageList.sendState { emptyList<String>() }
    }

    override fun handleEvents(action: BaseAction) {

    }

    override fun initialState(): BaseState {
        return ListContract.ListState(
            imageList = mutableStateListOf<String>()
        )
    }
}