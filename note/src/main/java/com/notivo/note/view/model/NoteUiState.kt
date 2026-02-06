package com.notivo.note.view.model

data class NoteUiState(
    val title: String = "",
    val content: NoteContentUi = NoteContentUi.Text(""),
    val isEditing: Boolean = false,
    val isSaving: Boolean = false,
    val error: String? = null
)

sealed class NoteContentUi {
    data class Text(
        val text: String? = null
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
