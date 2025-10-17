package com.notivo.common.view.model

// Model
data class NoteUiState(
    var title: String = "",
    var content: UiNote = UiNote.Text(""),
    var isEditing: Boolean = false,
    var isSaving: Boolean = false,
    var error: String? = null
)

sealed interface UiNote {
    data class Text(var text: String) : UiNote
    data class CheckItem(
        var key: String,
        var value: String,
        var isChecked: Boolean
    ) : UiNote
    data class SubNote(
        var title: String,
        var note: UiNote
    ) : UiNote
}
