package com.notivo.common.view.composables.dialogs

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.notivo.common.R
import com.notivo.common.utils.FontUtil
import com.notivo.common.view.composables.GeneralTextButton
import com.notivo.common.view.composables.dialogs.core.AppDialog
import com.notivo.common.view.configs.DialogConfig

@Composable
fun BasicInfoDialog(
    @StringRes headerTitleRes: Int,
    @DrawableRes headerIconRes: Int,
    @StringRes infoTextRes: Int,
    dialogItemInfo: DialogConfig.AppDialogItemInfo,
    modifier: Modifier
) {
    AppDialog(
        header = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxHeight(),
                    painter = painterResource(headerIconRes),
                    tint = colorResource(R.color.black),
                    contentDescription = null
                )

                Text(
                    text = stringResource(headerTitleRes),
                    color = colorResource(R.color.textColorPrimary),
                    fontFamily = FontUtil.fontMedium,
                    fontSize = FontUtil.bigTextSize,
                )
            }
        },
        content = {
            Text(
                text = stringResource(infoTextRes),
                color = colorResource(R.color.textColorPrimary),
                fontFamily = FontUtil.fontRegular,
                fontSize = FontUtil.defaultTextSize,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
        },
        buttons = {
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
        },
        onDismiss = {},
        modifier = modifier
    )
}

@Preview
@Composable
private fun BasicInfoDialogPreview() {
    BasicInfoDialog(
        headerTitleRes = R.string.gen_info,
        headerIconRes = R.drawable.ic_info,
        infoTextRes = R.string.dialog_save_note_warning,
        dialogItemInfo = DialogConfig.AppDialogItemInfo(
            positiveButton = DialogConfig.DialogButton(text = stringResource(R.string.gen_ok)),
            negativeButton = DialogConfig.DialogButton(text = stringResource(R.string.gen_cancel)),
            onPositiveButtonClicked = {},
            onNegativeButtonClicked = {}
        ),
        modifier = Modifier
    )
}
