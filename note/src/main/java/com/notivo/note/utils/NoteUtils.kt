package com.notivo.note.utils

import com.notivo.common.data.ChecklistNote
import com.notivo.common.data.Note
import com.notivo.common.data.TextNote
import com.notivo.common.view.models.ChecklistItemUi
import com.notivo.common.view.models.NoteContentUi
import com.notivo.common.view.models.NoteUiState

fun Note.toUiState(): NoteUiState = NoteUiState(
    title = title,
    content = when (this) {
        is TextNote -> NoteContentUi.Text(text)
        is ChecklistNote -> NoteContentUi.Checklist(
            items.map {
                ChecklistItemUi(
                    id = it.id,
                    title = title,
                    description = it.description,
                    isChecked = it.isChecked == true
                )
            }
        )
    },
    isEditing = true
)