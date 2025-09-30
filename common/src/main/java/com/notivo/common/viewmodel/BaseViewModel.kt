package com.notivo.common.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.notivo.common.extensions.toLiveData
import kotlin.properties.Delegates

abstract class BaseViewModel<ViewState : BaseViewState, ViewAction : BaseAction>(initialState: ViewState) : ViewModel() {

    private val _stateMutableLiveData = MutableLiveData<ViewState>()
    val stateLiveData = _stateMutableLiveData.toLiveData()

    protected var state by Delegates.observable(initialState) { _, old, new ->
        _stateMutableLiveData.value = new
    }

    /**Is used to update the viewModel liveData and trigger the observe delegate function.*/
    fun sendAction(viewAction: ViewAction){
        state = onReduceState(viewAction)
    }

    /**Is used to set the state of each Ui action.*/
    protected abstract fun onReduceState(viewAction: ViewAction): ViewState
}