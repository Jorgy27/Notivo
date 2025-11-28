package com.notivo.common.view.composables.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.notivo.common.R
import com.notivo.common.utils.FontUtil
import com.notivo.common.utils.previewDialogInfo
import com.notivo.common.view.composables.dialogs.core.AppDialog
import com.notivo.common.view.composables.dialogs.core.DefaultDialogButtons
import com.notivo.common.view.composables.dialogs.core.DefaultDialogHeader
import com.notivo.common.view.configs.DialogConfig

@Composable
fun RadioDialog(
    options: List<String?>,
    selectedIndex: MutableState<Int>,
    dialogItemInfo: DialogConfig.AppDialogItemInfo,
    modifier: Modifier = Modifier
) {
    AppDialog(
        header = { DefaultDialogHeader(dialogItemInfo) },
        content = {
            RadioDialogContent(
                options,
                selectedIndex
            )
        },
        buttons = {
            DefaultDialogButtons(
                Modifier
                    .fillMaxWidth()
                    .height(35.dp),
                dialogItemInfo
            )
        },
        onDismiss = {
            if (dialogItemInfo.isCancelable) {
                dialogItemInfo.onNegativeButtonClicked.invoke()
            }
        },
        modifier = modifier
    )
}

@Composable
fun RadioDialogContent(
    options: List<String?>,
    selectedIndex: MutableState<Int>
) {
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        options.forEachIndexed { index, option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(22.dp)
                    .selectable(
                        selected = index == selectedIndex.value,
                        onClick = { selectedIndex.value = index },
                        role = Role.Button
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Icon(
                        painter = if (index == selectedIndex.value) {
                            painterResource(R.drawable.radio_button_checked)
                        } else {
                            painterResource(R.drawable.radio_button_unchecked)
                        },
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(top = 2.dp, end = 6.dp)
                    )

                    Text(
                        text = option ?: "",
                        color = Color.Black,
                        fontFamily = FontUtil.fontRegular,
                        fontSize = FontUtil.defaultTextSize,
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RadioDialogPreview() {
    val options = listOf(
        "Option 1",
        "Option 2",
        "Option 3",
        "Option 4",
        "Option 5",
        "Option 6",
        "Option 7",
        "Option 8",
        "Option 9",
        "Option 10",
        "Option 11",
        "Option 12",
    )

    RadioDialog(
        options,
        remember { mutableIntStateOf(0) },
        previewDialogInfo
    )
}