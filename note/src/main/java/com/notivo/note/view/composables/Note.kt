package com.notivo.note.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.notivo.note.view.model.NoteContentUi
import com.notivo.note.view.model.NoteUiState

@Composable
fun NoteScreen(
    state: NoteUiState,
    onTitleChange: (String) -> Unit,
    onContentChange: (NoteContentUi) -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(2.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            NoteHeaderItem(state.title, onTitleChange)
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
            ) {
                when (val content = state.content) {
                    is NoteContentUi.Text -> {
                        content.text?.let {
                            TextNoteContentItem(it, onTextChange = { newText ->
                                onContentChange(content.copy(text = newText))
                            })
                        }
                    }

                    else -> Text("Unsupported note type")
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    var title by remember { mutableStateOf("Preview Title") }
    var body: String? by remember { mutableStateOf("Preview Body") }

    val previewState = NoteUiState(
        title = title,
        content = NoteContentUi.Text(body)
    )

    NoteScreen(
        modifier = Modifier,
        state = previewState,
        onSave = {},
        onTitleChange = { title = it },
        onContentChange = { content ->
            when (content) {
                is NoteContentUi.Text -> body = content.text
                is NoteContentUi.Checklist -> Unit // not used in this preview
            }
        }
    )
}
