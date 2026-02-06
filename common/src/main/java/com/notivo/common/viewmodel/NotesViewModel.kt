package com.notivo.common.viewmodel

import com.notivo.common.data.Note
import com.notivo.common.usecases.AddNoteUseCase
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase
) :
    BaseViewModel<NotesViewModel.NotesViewState, NotesViewModel.NotesAction>(NotesViewState()) {

    suspend fun addNote(note: Note) {
        addNoteUseCase.invoke(note)
        dispatch(NotesAction.AddNoteAction(note))
    }

    override fun onReduceState(viewAction: NotesAction): NotesViewState = when (viewAction) {
        is NotesAction.AddNoteAction -> state.copy(
            note = viewAction.note,
            isLoading = false,
            error = null
        )

        is NotesAction.Failure -> state.copy(
            isLoading = false,
            error = viewAction.error
        )
    }

    data class NotesViewState(
        var note: Note? = null,
        var isLoading: Boolean = true,
        var error: Throwable? = null,
    ) : BaseViewState

    sealed class NotesAction : BaseAction {
        class Failure(var error: Throwable) : NotesAction()
        class AddNoteAction(var note: Note) : NotesAction()
    }
}