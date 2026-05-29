package com.notivo.common.view.configs

import androidx.annotation.DrawableRes

object AppFloatingButtonConfig {

    data class AppFloatingButtonInfo(
        val childrenInfo: List<AppFloatingButtonChildInfo>
    )

    data class AppFloatingButtonChildInfo(
        @DrawableRes val icon: Int,
        val action: FloatingButtonAction
    )

    enum class FloatingButtonAction { ADD_NOTE, ADD_FOLDER, ADD_QUICK_REMINDER }
}