package com.notivo.apiRoom.repositoriesImpl

import com.notivo.apiRoom.Dao.NotesDao
import com.notivo.apiRoom.Utils.DbItemsUtil.toDbItem
import com.notivo.common.data.Note
import com.notivo.common.repositories.NotesRepository
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao
) : NotesRepository {

    override suspend fun insertNote(note: Note) {
        val noteDbItem = note.toDbItem()
        notesDao.insertNote(noteDbItem)
    }
}