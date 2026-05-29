package com.notivo.common.view.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class NoteUiState(
    var title: MutableState<String> = mutableStateOf(""),
    val content: NoteContentUi = NoteContentUi.Text(mutableStateOf("")),
    val isEditing: Boolean = false,
    val isSaving: Boolean = false,
    val error: String? = null
)

sealed class NoteContentUi {
    data class Text(
        var text: MutableState<String> = mutableStateOf("")
    ) : NoteContentUi()

    data class Checklist(
        val items: List<ChecklistItemUi>
    ) : NoteContentUi()
}

data class ChecklistItemUi(
    val id: String?,
    val title: String? = null,
    val description: String? = null,
    val isChecked: Boolean
)
