package com.notivo.common.utils

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.notivo.common.R

object FontUtil {

    val fontRegular = FontFamily(Font(R.font.font_regular))
    val fontMedium = FontFamily(Font(R.font.font_medium))
    val fontBold = FontFamily(Font(R.font.font_bold))


    val extraSmallTextSize: TextUnit
        get() = if (isPreview) 10.sp else if (Config.isTablet) 14.sp else 10.sp

    val smallTextSize: TextUnit
        get() = if (isPreview) 12.sp else if (Config.isTablet) 16.sp else 12.sp

    val defaultTextSize: TextUnit
        get() = if (isPreview) 14.sp else if (Config.isTablet) 18.sp else 14.sp

    val bigTextSize: TextUnit
        get() = if (isPreview) 16.sp else if (Config.isTablet) 20.sp else 16.sp

    val largeTextSize: TextUnit
        get() = if (isPreview) 18.sp else if (Config.isTablet) 22.sp else 18.sp

    val hugeTextSize: TextUnit
        get() = if (isPreview) 20.sp else if (Config.isTablet) 24.sp else 20.sp

}