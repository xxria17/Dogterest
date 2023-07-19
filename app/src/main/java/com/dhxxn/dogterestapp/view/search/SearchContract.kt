package com.dhxxn.dogterestapp.view.search

import androidx.compose.ui.graphics.Color
import com.dhxxn.dogterestapp.view.base.BaseUiAction
import com.dhxxn.dogterestapp.view.base.BaseUiEffect
import com.dhxxn.dogterestapp.view.base.BaseUiState
import com.dhxxn.dogterestapp.view.base.DogState
import com.dhxxn.dogterestapp.view.base.DogStateList

class SearchContract {

    data class SearchState(
        val tagList: DogStateList<Pair<String, String>>,
        val searchKeyword: DogState<String>,
        val searchResult: DogStateList<String>,
        val colorList: DogStateList<Color>,
        val screenType: DogState<SEARCH_SCREEN_TYPE>
    ): BaseUiState

    sealed class SearchAction: BaseUiAction {
        data class SearchBreed(val breed: String): SearchAction()

        data class InputKeyword(val keyword: String): SearchAction()

        object BackToDefault: SearchAction()
    }

    sealed class SearchEffect: BaseUiEffect {

    }
}

enum class SEARCH_SCREEN_TYPE {
    DEFAULT,
    RESULT
}