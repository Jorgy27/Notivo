package com.notivo.apiroom.utils

import com.notivo.apiRoom.data.NoteDbItem
import com.notivo.common.data.Note

object DbItemsUtil {

    fun Note.toDbItem(): NoteDbItem {
        return NoteDbItem(
            id = this.id
        )
    }
}