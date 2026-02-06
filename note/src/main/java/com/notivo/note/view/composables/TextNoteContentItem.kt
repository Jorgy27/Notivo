package com.notivo.note.view.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextNoteContentItem(
    text: String,
    onTextChange: (String) -> Unit
) {
    TextField(
        value = text,
        placeholder = {
            Text(
                text = "Add Text Here",
                modifier = Modifier.fillMaxSize()
            )
        },
        modifier = Modifier.fillMaxSize(),
        onValueChange = onTextChange
    )
}