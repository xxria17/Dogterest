package com.dhxxn.dogterestapp.ui.base

import androidx.compose.runtime.Composable

abstract class BaseScreen {
    fun <T> DogState<T>.value(): T {
        return this.getValue(this@BaseScreen)
    }

    fun <T> DogStateList<T>.value(): List<T> {
        return this.getValue(this@BaseScreen)
    }

    @Composable
    abstract fun CreateContent()
}