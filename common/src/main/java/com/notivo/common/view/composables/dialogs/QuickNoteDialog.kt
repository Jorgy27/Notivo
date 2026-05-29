package com.notivo.common.view.composables.dialogs

import android.Manifest.permission_group.PHONE
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.notivo.common.R
import com.notivo.common.utils.FontUtil
import com.notivo.common.utils.previewDialogInfo
import com.notivo.common.view.composables.dialogs.core.AppDialog
import com.notivo.common.view.composables.textfields.EditableTextField
import com.notivo.common.view.configs.DialogConfig
import com.notivo.common.view.models.NoteContentUi
import com.notivo.common.view.models.NoteUiState

@Composable
fun QuickNoteDialog(
    dialogItemInfo: DialogConfig.AppDialogItemInfo,
    isVisible: Boolean,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val height = LocalView.current.height
    val showWarning = remember { mutableStateOf(false) }
    var headerText by remember { mutableStateOf("") }
    var bodyText by remember { mutableStateOf("") }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically {
            with(density) { -height }
        },
        exit = slideOutVertically {
            with(density) { -height }
        } + shrinkVertically() + fadeOut()
    ) {
        AppDialog(
            header = {
                QuickNoteDialogHeader(
                    onBackSelected = {
                        if (bodyText.isNotEmpty() || headerText.isNotEmpty()) {
                            showWarning.value = true
                        } else {
                            dialogItemInfo.onNegativeButtonClicked()
                        }
                    },
                    onSaveSelected = { dialogItemInfo.onPositiveButtonClicked() }
                )
            },
            content = {
                Column {
                    EditableTextField(
                        valueText = headerText,
                        labelText = "Note Title",
                        onValueChange = { headerText = it },
                        textFieldColors = TextFieldDefaults.colors(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                    )

                    EditableTextField(
                        valueText = bodyText,
                        onValueChange = { bodyText = it },
                        textFieldColors = TextFieldDefaults.colors(),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(2.dp)
                    )
                }
            },
            buttons = null,
            onDismiss = {
                if (bodyText.isNotEmpty() || headerText.isNotEmpty()) {
                    showWarning.value = true
                }
            },
            modifier = modifier
        )

        if (showWarning.value) {
            BasicInfoDialog(
                headerTitleRes = R.string.gen_info,
                headerIconRes = R.drawable.ic_info,
                infoTextRes = R.string.dialog_save_note_warning,
                dialogItemInfo = DialogConfig.AppDialogItemInfo(
                    positiveButton = DialogConfig.DialogButton(text = stringResource(R.string.gen_ok)),
                    negativeButton = DialogConfig.DialogButton(text = stringResource(R.string.gen_cancel)),
                    onPositiveButtonClicked = {
                        showWarning.value = false
                        dialogItemInfo.onNegativeButtonClicked()
                    },
                    onNegativeButtonClicked = {
                        showWarning.value = false
                    }
                ),
                modifier = Modifier
            )
        }
    }
}

@Composable
private fun QuickNoteDialogHeader(
    modifier: Modifier = Modifier,
    onBackSelected: () -> Unit,
    onSaveSelected: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier
                .fillMaxHeight()
                .clickable { onBackSelected() },
            painter = painterResource(R.drawable.ic_arrow_back),
            tint = colorResource(R.color.black),
            contentDescription = null
        )

        Text(
            text = "Add Note",
            color = colorResource(R.color.textColorPrimary),
            fontFamily = FontUtil.fontMedium,
            fontSize = FontUtil.bigTextSize,
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .clickable { onSaveSelected() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxHeight()
                ,
                painter = painterResource(R.drawable.ic_save),
                tint = colorResource(R.color.black),
                contentDescription = null
            )
        }
    }
}


@Preview(device = PHONE)
@Composable
private fun QuickNoteDialogPreview() {

    QuickNoteDialog(
        dialogItemInfo = previewDialogInfo,
        isVisible = true,
        modifier = Modifier.fillMaxWidth()
    )
}