package com.dhxxn.dogterestapp.view.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseState<T, R>(
    selectedFlow: StateFlow<Boolean>?,
    viewModelScope: CoroutineScope
) {
    protected abstract var state: R

    protected abstract var waitState: R

    protected var isVisibleScreen = true

    init {
        viewModelScope.launch {
            selectedFlow?.collect {
                setIsVisibleScreen(it)
            }
        }
    }

    protected abstract fun setIsVisibleScreen(isCurrentVisibleScreen: Boolean)

    abstract fun getValue(valueOwner: Any): T

    internal abstract fun setValue(newValue: T)

    protected fun getCurrentState(): R {
        return if (isVisibleScreen) {
            state
        } else {
            waitState
        }
    }
}