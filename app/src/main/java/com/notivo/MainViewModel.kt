package com.notivo

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.notivo.common.view.configs.AppFloatingButtonConfig
import com.notivo.ui.view.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel()
class MainViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun addQuickNote(actionType: AppFloatingButtonConfig.FloatingButtonAction, context: Context) {
        val note = uiState.value.quickNote
        Toast.makeText(context, "Add quickNote with title ${note?.title}", Toast.LENGTH_SHORT).show()
    }
}