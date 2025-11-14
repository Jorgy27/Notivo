package com.notivo.common.utils

val isPreview by lazy {
    Thread.currentThread().stackTrace.any {
        it.className.startsWith("androidx.compose.ui.tooling")
    }
}
