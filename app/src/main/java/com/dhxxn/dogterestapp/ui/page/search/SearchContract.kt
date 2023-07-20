package com.dhxxn.dogterestapp.ui.page.search

import androidx.compose.ui.graphics.Color
import com.dhxxn.dogterestapp.ui.base.BaseUiAction
import com.dhxxn.dogterestapp.ui.base.BaseUiEffect
import com.dhxxn.dogterestapp.ui.base.BaseUiState
import com.dhxxn.dogterestapp.ui.base.DogState
import com.dhxxn.dogterestapp.ui.base.DogStateList

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