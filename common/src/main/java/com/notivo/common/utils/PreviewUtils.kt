package com.notivo.common.utils

import com.notivo.common.view.configs.DialogConfig

val isPreview by lazy {
    Thread.currentThread().stackTrace.any {
        it.className.startsWith("androidx.compose.ui.tooling")
    }
}

val previewDialogInfo = DialogConfig.AppDialogItemInfo(
    title = DialogConfig.DialogTitle("Radio Dialog"),
    positiveButton = DialogConfig.DialogButton("Save"),
    negativeButton = DialogConfig.DialogButton("Cancel")
)