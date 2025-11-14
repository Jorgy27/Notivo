package com.notivo.common.view.composables

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.notivo.common.R
import com.notivo.common.utils.FontUtil

@Composable
fun GeneralTextButton(
    text: String?,
    fontFamily: FontFamily,
    fontSize: TextUnit,
    enabled: Boolean,
    textColor: Int?,
    buttonColors: ButtonColors?,
    onClick: () -> Unit,
) {
    TextButton(
        onClick = onClick,
        enabled = enabled,
        colors = buttonColors ?: ButtonDefaults.textButtonColors(),
        modifier = Modifier
    ) {
        text?.let {
            Text(
                text = it,
                color = colorResource(textColor ?: R.color.textColorPrimary),
                fontFamily = fontFamily,
                fontSize = fontSize,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
        }
    }
}

@Preview
@Composable
private fun GeneralTextButtonPreview() {
    GeneralTextButton(
        text = "Example",
        fontFamily = FontUtil.fontRegular,
        fontSize = FontUtil.defaultTextSize,
        enabled = true,
        textColor = R.color.textColorPrimary,
        buttonColors = ButtonColors(
            containerColor = colorResource(R.color.white),
            contentColor = Color.Transparent,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.Transparent
        ),
        onClick = {}
    )
}