package com.dhxxn.dogterestapp.ui.page.list

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.dhxxn.dogterestapp.ui.base.BaseUiAction
import com.dhxxn.dogterestapp.ui.base.BaseUiState
import com.dhxxn.dogterestapp.ui.base.BaseViewModel
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

    init {
        initialData()
        loadData()
    }

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

    override fun handleEvents(action: BaseUiAction) {

    }

    override fun initialState(): BaseUiState {
        return ListContract.ListState(
            imageList = mutableDogStateListOf<String>(emptyList())
        )
    }
}