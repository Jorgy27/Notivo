package com.notivo.common.view.composables.builders

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.notivo.common.data.AppDialogItemInfo

interface AppDialogUiBuilder {

    @Composable
    fun BuildHeader(dialogItemInfo: AppDialogItemInfo) {
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
        dialogItemInfo: AppDialogItemInfo,
        modifier: Modifier
    ) {
    }
}