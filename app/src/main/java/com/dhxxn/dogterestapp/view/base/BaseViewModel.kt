package com.dhxxn.dogterestapp.view.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected var activeFlowJob: Job = SupervisorJob((viewModelScope.coroutineContext[Job]))

    protected fun cancelActiveFlowJob() {
        activeFlowJob.cancel()
        activeFlowJob = SupervisorJob(viewModelScope.coroutineContext[Job])
    }

    override fun onCleared() {
        activeFlowJob.cancel()
        super.onCleared()
    }

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState

    fun <T> Flow<T>.loading(): Flow<T> {
        return onStart { _loadingState.value = true }
            .onCompletion { _loadingState.value = false }
    }

    open fun loadMore() {
        if (loadingState.value != true) {
            loadData()
        }
    }

    protected abstract fun loadData()
    abstract fun initialData()

    private var enableMoreToLoad: Boolean = true

    fun enableMoreToLoad(enableMoreToLoad: Boolean) {
        this.enableMoreToLoad = enableMoreToLoad
    }

    open fun onRefresh() {
        enableMoreToLoad(true)
        initialData()
    }

    protected abstract fun handleEvents(action: BaseAction)
    protected abstract fun initialState(): BaseState

    private val _state: BaseState by lazy { initialState() }

    fun <T: BaseState> state(): T {
        return _state as T
    }

    fun <T: BaseEffect> effect(): Flow<T> {
        return effect as Flow<T>
    }

    private val _action: MutableSharedFlow<BaseAction> = MutableSharedFlow()
    private val _effect: Channel<BaseEffect> = Channel()
    private val effect: Flow<BaseEffect> = _effect.receiveAsFlow()

    init {
        subscribeToActions()
    }

    private fun subscribeToActions() {
        viewModelScope.launch {
            _action.collect { _action ->
                _handleActions(_action)
            }
        }
    }

    private fun _handleActions(action: BaseAction) {

    }

    fun sendAction(action: BaseAction) {
        viewModelScope.launch {
            _action.emit(action)
        }
    }

    protected fun sendEffect(effect: () -> BaseEffect) {
        val effectValue = effect()
        viewModelScope.launch {
            _effect.send(effectValue)
        }
    }

    protected fun sendEffect(effect: BaseEffect) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }

    protected fun <T> State<T>.sendState(newState: T) {
        if (this is MutableState) {
            value = newState
        }
    }

    protected fun <T> State<T>.sendState(block: T.() -> T) {
        if (this is MutableState) {
            value = block(value)
        }
    }

    protected fun <T> List<T>.sendState(block: SnapshotStateList<T>.() -> Unit) {
        if (this is SnapshotStateList) {
            block(this)
        } else {
            throw IllegalStateException("List used in ViewModel must be castable to SnapshotStateList unconditionally.")
        }
    }

    protected fun <K, V> Map<K, V>.sendState(block: SnapshotStateMap<K, V>.() -> Unit) {
        if (this is SnapshotStateMap) {
            block(this)
        } else {
            throw java.lang.IllegalStateException("Map used in ViewModel must be castable to SnapshotStateMap unconditionally.")
        }
    }


}