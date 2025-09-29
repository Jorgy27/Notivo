package com.notivo.apiroom.repositoriesImpl

import com.notivo.apiroom.dao.NotesDao
import com.notivo.apiroom.utils.DbItemsUtil.toDbItem
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