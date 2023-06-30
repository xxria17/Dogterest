package com.dhxxn.dogterestapp.common

import androidx.annotation.StringRes

sealed class UiState {
    object Loading: UiState()
    data class Success<out T>(val item: T): UiState()
    data class Failure(@StringRes val message: Int): UiState()
    object NetworkFailure: UiState()
    object EmptyResult: UiState()
}
