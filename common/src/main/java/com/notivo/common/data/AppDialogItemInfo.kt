package com.notivo.common.data

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.notivo.common.R
import com.notivo.common.utils.FontUtil.bigTextSize
import com.notivo.common.utils.FontUtil.defaultTextSize
import com.notivo.common.utils.FontUtil.fontMedium

data class AppDialogItemInfo(
    val title: DialogTitle,
    val positiveButton: DialogButton? = null,
    val negativeButton: DialogButton? = null,
    val isCancelable: Boolean = false,
    val onPositiveButtonClicked: () -> Unit = {},
    val onNegativeButtonClicked: () -> Unit = {},
)


data class DialogTitle(
    val text: String,
    @ColorRes val colorRes: Int = R.color.textColorPrimary,
    val fontSize: TextUnit = bigTextSize,
    val textAlign: TextAlign = TextAlign.Center,
    val fontFamily: FontFamily = fontMedium
)

data class DialogIcon(
    @DrawableRes val icon: Int,
    @ColorRes val iconColor: Int = R.color.white,
    @ColorRes val bgColor: Int? = null,
    @DimenRes val size: Int = R.dimen.gd_default_icon_size,
)

data class DialogButton(
    val text: String?,
    @ColorRes val bgColor: Int? = null,
    val fontSize: TextUnit = defaultTextSize,
    val fontFamily: FontFamily = fontMedium,
    val isEnabled: MutableState<Boolean> = mutableStateOf(true)
)

data class DialogTextField(
    val input: MutableState<String?> = mutableStateOf(""),
    val placeholder: String = "",
    val hasTrailingIcon: Boolean = false,
    val maxInputLength: Int? = null
)