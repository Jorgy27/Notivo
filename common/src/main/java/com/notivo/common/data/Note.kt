package com.notivo.common.data

import com.notivo.common.view.model.UiNote

data class Note(
    val id: String,
) {
    companion object {
        fun fromUi(uiNote: UiNote): Note {
            return Note("test")
        }
    }
}
