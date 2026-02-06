package com.notivo.note.viewmodel

import com.notivo.common.data.Note
import com.notivo.common.usecases.AddNoteUseCase
import com.notivo.common.viewmodel.BaseAction
import com.notivo.common.viewmodel.BaseViewModel
import com.notivo.common.viewmodel.BaseViewState
import com.notivo.note.utils.toUiState
import com.notivo.note.view.model.NoteUiState
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase
) : BaseViewModel<NotesViewModel.NotesViewState, NotesViewModel.NotesAction>(NotesViewState()) {

    suspend fun addNote(note: Note) {
        addNoteUseCase.invoke(note)
        dispatch(NotesAction.AddNoteAction(note.toUiState()))
    }

    override fun onReduceState(viewAction: NotesAction): NotesViewState = when (viewAction) {
        is NotesAction.AddNoteAction -> state.copy(
            noteUiState = viewAction.note,
            isLoading = true,
            error = null
        )

        is NotesAction.Failure -> state.copy(
            isLoading = false,
            error = viewAction.error
        )
    }

    data class NotesViewState(
        var noteUiState: NoteUiState? = null,
        var isLoading: Boolean = true,
        var error: Throwable? = null,
    ) : BaseViewState

    sealed class NotesAction : BaseAction {
        class Failure(var error: Throwable) : NotesAction()
        class AddNoteAction(var note: NoteUiState) : NotesAction()
    }
}