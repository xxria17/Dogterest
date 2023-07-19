package com.dhxxn.dogterestapp.view.search

import com.dhxxn.dogterestapp.view.base.BaseUiAction
import com.dhxxn.dogterestapp.view.base.BaseUiEffect
import com.dhxxn.dogterestapp.view.base.BaseUiState
import com.dhxxn.dogterestapp.view.base.DogState
import com.dhxxn.dogterestapp.view.base.DogStateList

class SearchContract {

    data class SearchState(
        val tagList: DogStateList<String>,
        val searchKeyword: DogState<String>,
        val searchResult: DogStateList<String>
    ): BaseUiState

    sealed class SearchAction: BaseUiAction {
        data class ClickBreed(val breed: String): SearchAction()

        data class InputKeyword(val keyword: String): SearchAction()
    }

    sealed class SearchEffect: BaseUiEffect {

    }
}