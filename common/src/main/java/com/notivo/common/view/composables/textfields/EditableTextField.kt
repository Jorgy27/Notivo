package com.notivo.common.view.composables.textfields

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.notivo.common.R

@Composable
fun EditableTextField(
    valueText: String?,
    labelText: String? = null,
    onValueChange: (String) -> Unit,
    textFieldColors: TextFieldColors,
    modifier: Modifier
) {
    OutlinedTextField(
        modifier = modifier,
        value = valueText ?: "",
        onValueChange = { newText -> onValueChange(newText) },
        label = {
            labelText?.let {
                Text(
                    text = it,
                    color = colorResource(R.color.textColorPrimary)
                )
            }
        },
        colors = textFieldColors,
    )
}