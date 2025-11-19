package com.notivo.common.view.composables.dialogs.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.notivo.common.utils.previewDialogInfo
import com.notivo.common.view.composables.GeneralTextButton
import com.notivo.common.view.configs.DialogConfig

@Composable
fun DefaultDialogHeader(dialogItemInfo: DialogConfig.AppDialogItemInfo) {
    Text(
        text = dialogItemInfo.title.text,
        color = colorResource(dialogItemInfo.title.colorRes),
        fontFamily = dialogItemInfo.title.fontFamily,
        fontSize = dialogItemInfo.title.fontSize,
        textAlign = dialogItemInfo.title.textAlign,
        modifier = Modifier.fillMaxWidth()
    )

    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp),
        color = Color.Black,
        thickness = 1.dp
    )
}

@Composable
fun DefaultDialogButtons(
    modifier: Modifier = Modifier,
    dialogItemInfo: DialogConfig.AppDialogItemInfo
) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = modifier
    ) {
        dialogItemInfo.negativeButton?.let { button ->
            GeneralTextButton(
                text = button.text,
                fontFamily = button.fontFamily,
                fontSize = button.fontSize,
                enabled = button.isEnabled.value,
                textColor = button.bgColor,
                buttonColors = null,
                onClick = dialogItemInfo.onNegativeButtonClicked
            )
        }

        dialogItemInfo.positiveButton?.let { button ->
            GeneralTextButton(
                text = button.text,
                fontFamily = button.fontFamily,
                fontSize = button.fontSize,
                enabled = button.isEnabled.value,
                textColor = button.bgColor,
                buttonColors = null,
                onClick = dialogItemInfo.onPositiveButtonClicked
            )
        }
    }
}

@Preview
@Composable
private fun DefaultDialogHeaderPreview() {
    Box(
        modifier = Modifier.background(Color.White)
    ) {
        DefaultDialogHeader(previewDialogInfo)
    }
}

@Preview
@Composable
private fun DefaultDialogButtonsPreview() {
    Box(
        modifier = Modifier.background(Color.White)
    ) {
        DefaultDialogButtons(Modifier.fillMaxWidth(), previewDialogInfo)
    }
}