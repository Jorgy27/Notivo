package com.notivo.common.viewmodel

import com.notivo.common.data.Note
import com.notivo.common.view.model.UiNote

class NotesViewModel : BaseViewModel<NotesViewModel.NotesViewState, NotesViewModel.NotesAction>(NotesViewState()) {

    override fun onReduceState(viewAction: NotesAction): NotesViewState = when (viewAction) {
        is NotesAction.AddNoteAction -> state.copy(
            note = Note.fromUi(viewAction.note),
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
        class AddNoteAction(var note: UiNote) : NotesAction()
    }
}