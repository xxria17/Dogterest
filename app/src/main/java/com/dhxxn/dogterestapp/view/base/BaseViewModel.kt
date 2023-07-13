package com.dhxxn.dogterestapp.view.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList

abstract class BaseViewModel : ViewModel() {

    protected var activeFlowJob: Job = SupervisorJob((viewModelScope.coroutineContext[Job]))

    internal var selectedflow: StateFlow<Boolean>? = null

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

    protected abstract fun handleEvents(action: BaseUiAction)
    protected abstract fun initialState(): BaseUiState

    private val _state: BaseUiState by lazy { initialState() }

    fun <T: BaseUiState> state(): T {
        return _state as T
    }

    fun <T: BaseUiEffect> effect(): Flow<T> {
        return effect as Flow<T>
    }

    private val _action: MutableSharedFlow<BaseUiAction> = MutableSharedFlow()
    private val _effect: Channel<BaseUiEffect> = Channel()
    private val effect: Flow<BaseUiEffect> = _effect.receiveAsFlow()

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

    private fun _handleActions(action: BaseUiAction) {
        handleEvents(action)
    }

    fun sendAction(action: BaseUiAction) {
        viewModelScope.launch {
            _action.emit(action)
        }
    }

    protected fun sendEffect(effect: () -> BaseUiEffect) {
        val effectValue = effect()
        viewModelScope.launch {
            _effect.send(effectValue)
        }
    }

    protected fun sendEffect(effect: BaseUiEffect) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }

    protected fun <T> DogState<T>.sendState(block: T.() -> T) {
        this.setValue(block(value()))
    }

    protected fun <T> DogStateList<T>.sendState(block: List<T>.() -> List<T>) {
        this.setValue(block(value()))
    }

    protected fun <T> DogStateList<T>.add(index: Int, element: T) {
        this.add(index, element)
    }

    protected fun <T> DogStateList<T>.addAll(elements: Collection<T>) {
        this.addAll(elements)
    }

    protected fun <T> DogStateList<T>.clear() {
        this.clear()
    }

    protected fun <T> DogStateList<T>.remove(element: T) {
        this.remove(element)
    }

    protected fun <T> DogStateList<T>.removeAt(index: Int) {
        this.removeAt(index)
    }

    fun <T> DogState<T>.value(): T {
        return this.getValue(this@BaseViewModel)
    }

    fun <T> DogStateList<T>.value(): List<T> {
        return this.getValue(this@BaseViewModel)
    }

    fun <T> mutableDogStateOf(initialValue: T): DogState<T> {
        return DogState(initialValue, this.selectedflow, viewModelScope)
    }

    fun <T> mutableDogStateListOf(initialValue: List<T>): DogStateList<T> {
        return DogStateList(initialValue, this.selectedflow, viewModelScope)
    }
}

class DogState<T>(
    initialValue: T,
    selectedFlow: StateFlow<Boolean>?,
    viewModelScope: CoroutineScope
) : BaseState<T, State<T>>(selectedFlow, viewModelScope) {

    override var state: State<T> = mutableStateOf(initialValue)
    override var waitState: State<T> = mutableStateOf(initialValue)

    override fun setIsVisibleScreen(isCurrentVisibleScreen: Boolean) {
        this.isVisibleScreen = isCurrentVisibleScreen
        if (isVisibleScreen) {
            (state as? MutableState)?.value = waitState.value
        }
    }

    override fun getValue(valueOwner: Any): T {
        return if (valueOwner is BaseViewModel) {
            getCurrentState().value
        } else {
            state.value
        }
    }

    override fun setValue(newValue: T) {
        if (isVisibleScreen) {
            (state as? MutableState)?.value = newValue
        }
        (waitState as? MutableState)?.value = newValue
    }

}

class DogStateList<T>(
    initialValue: List<T>,
    selectedFlow: StateFlow<Boolean>?,
    viewModelScope: CoroutineScope
): BaseState<List<T>, SnapshotStateList<T>>(selectedFlow, viewModelScope) {

    override var state: SnapshotStateList<T> = initialValue.toMutableStateList()
    override var waitState: SnapshotStateList<T> = initialValue.toMutableStateList()

    override fun setIsVisibleScreen(isCurrentVisibleScreen: Boolean) {
        this.isVisibleScreen = isCurrentVisibleScreen
        if (isVisibleScreen) {
            with(state) {
                clear()
                addAll(waitState)
            }
        }
    }

    override fun getValue(valueOwner: Any): List<T> {
        return if (valueOwner is BaseViewModel) {
            getCurrentState().toImmutableList()
        } else {
            state.toImmutableList()
        }
    }

    override fun setValue(newValue: List<T>) {
        if (isVisibleScreen) {
            with(state) {
                clear()
                addAll(newValue)
            }
        } else {
            with(state) {
                clear()
                addAll(newValue)
            }
        }
    }

    internal fun add(element: T) {
        if (isVisibleScreen) {
            state.add(element)
        }
        waitState.add(element)
    }

    internal fun add(
        index: Int,
        element: T
    ) {
        if (isVisibleScreen) {
            state.add(index, element)
        }
        waitState.add(index, element)
    }

    internal fun addAll(elements: Collection<T>) {
        if (isVisibleScreen) {
            state.addAll(elements)
        }
        waitState.addAll(elements)
    }

    internal fun clear() {
        if (isVisibleScreen) {
            state.clear()
        }
        waitState.clear()
    }

    internal fun remove(element: T) {
        if (isVisibleScreen) {
            state.remove(element)
        }
        waitState.remove(element)
    }

    internal fun removeAt(index: Int) {
        if (isVisibleScreen) {
            state.removeAt(index)
        }
        waitState.removeAt(index)
    }

    internal fun removeAll(elements: Collection<T>) {
        if (isVisibleScreen) {
            state.removeAll(elements)
        }
        waitState.removeAll(elements)
    }
}