package com.notivo.common.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<ViewState : BaseViewState, ViewAction : BaseAction>(initialState: ViewState) : ViewModel() {

    private val _stateFlow = MutableStateFlow(initialState)
    val stateFlow: StateFlow<ViewState> = _stateFlow.asStateFlow()

    protected var state: ViewState
        get() = _stateFlow.value
        set(value) {
            _stateFlow.value = value
        }

    /**Is used to update the viewModel liveData and trigger the observe delegate function.*/
    fun dispatch(viewAction: ViewAction) {
        state = onReduceState(viewAction)
    }

    /**Is used to set the state of each Ui action.*/
    protected abstract fun onReduceState(viewAction: ViewAction): ViewState
}