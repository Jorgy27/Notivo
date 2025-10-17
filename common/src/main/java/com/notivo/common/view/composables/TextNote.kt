package com.notivo.common.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.notivo.common.view.model.NoteUiState
import com.notivo.common.view.model.UiNote

//TODO: Move this.
@Composable
fun NoteScreen(
    state: NoteUiState,
    onTitleChange: (String) -> Unit,
    onBodyChange: (String) -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(12.dp)) {
        TextViewItem(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            noteState = state,
            onTitleChange = onTitleChange,
            onBodyChange = onBodyChange
        )
    }
}

@Composable
fun TextViewItem(
    modifier: Modifier = Modifier,
    noteState: NoteUiState,
    onTitleChange: (String) -> Unit,
    onBodyChange: (String) -> Unit
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
            ) {
                TextField(
                    value = noteState.title,
                    placeholder = { Text(text = "Title", modifier = Modifier.fillMaxSize()) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    onValueChange = onTitleChange,
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
            ) {
                when (val content = noteState.content) {
                    is UiNote.Text -> {
                        TextField(
                            value = content.text,
                            placeholder = { Text(text = "Add Text Here", modifier = Modifier.fillMaxSize()) },
                            modifier = Modifier
                                .fillMaxSize(),
                            onValueChange = onBodyChange
                        )
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
    var body by remember { mutableStateOf("Preview Body") }

    val previewNote = remember(title, body) {
        NoteUiState(title = title, content = UiNote.Text(body))
    }
    NoteScreen(
        state = previewNote,
        onTitleChange = { title = it },
        onBodyChange = { body = it },
        onSave = {},
        modifier = Modifier
    )
}