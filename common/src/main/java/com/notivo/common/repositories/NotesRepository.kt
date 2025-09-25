package com.notivo.common.repositories

import com.notivo.common.data.Note

interface NotesRepository {

    suspend fun insertNote(note: Note) { }
}