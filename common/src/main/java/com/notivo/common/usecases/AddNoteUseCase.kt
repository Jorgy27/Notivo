package com.notivo.common.usecases

import com.notivo.common.data.Note
import com.notivo.common.repositories.NotesRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val repository: NotesRepository) {

    suspend operator fun invoke(note: Note) {
        return repository.insertNote(note)
    }
}