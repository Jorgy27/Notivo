package com.notivo.common.view.model

import com.notivo.common.data.Note
import com.notivo.common.data.TextNote

// Model
data class NoteUiState(
    var title: String? = "",
    var content: Note? = TextNote("1", "Sample", null, "Test"),
    var isEditing: Boolean = false,
    var isSaving: Boolean = false,
    var error: String? = null
)
