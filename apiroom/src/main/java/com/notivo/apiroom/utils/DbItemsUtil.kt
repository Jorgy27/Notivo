package com.notivo.apiroom.utils

import com.notivo.apiroom.data.NoteDbItem
import com.notivo.common.data.ChecklistNote
import com.notivo.common.data.Note
import com.notivo.common.data.NoteType
import com.notivo.common.data.TextNote
import java.util.UUID

object DbItemsUtil {

    fun Note.toDbItem(): NoteDbItem {
        val type = when (this) {
            is TextNote -> NoteType.TEXT_NOTE
            is ChecklistNote -> NoteType.CHECK_LIST
        }

        return NoteDbItem(
            id = UUID.randomUUID().toString(),
            title = title,
            folderId = folderId,
            type = type
        )
    }
}