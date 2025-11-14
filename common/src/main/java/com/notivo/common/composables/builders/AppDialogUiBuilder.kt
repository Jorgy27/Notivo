package com.notivo.common.composables.builders

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface AppDialogUiBuilder {

    @Composable
    fun BuildHeader(title: String) {
    }

    @Composable
    fun BuildContent() {
    }


    /**
     * @param modifier A Modifier instance to customize the appearance or behavior of the buttons.
     * @param onPositiveButtonClicked Callback invoked when the positive button is clicked.
     * @param onNegativeButtonClicked Callback invoked when the negative button is clicked.
     * */
    @Composable
    fun BuildButtons(
        modifier: Modifier,
        onPositiveButtonClicked: () -> Unit,
        onNegativeButtonClicked: () -> Unit
    ) {
    }
}