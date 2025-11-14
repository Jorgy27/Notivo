package com.notivo.common.view.composables.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.notivo.common.R
import com.notivo.common.data.AppDialogItemInfo
import com.notivo.common.data.DialogButton
import com.notivo.common.data.DialogTitle
import com.notivo.common.utils.FontUtil
import com.notivo.common.view.composables.GeneralTextButton
import com.notivo.common.view.composables.builders.AppDialogUiBuilder

data class RadioDialogBuilder(
    val options: List<String?>,
    val trailingIconList: List<Int>? = null,
    val selectedIndex: MutableState<Int>
) : AppDialogUiBuilder {

    @Composable
    override fun BuildHeader(dialogItemInfo: AppDialogItemInfo) {
        Text(
            text = dialogItemInfo.title.text,
            color = colorResource(dialogItemInfo.title.colorRes),
            fontFamily = dialogItemInfo.title.fontFamily,
            fontSize = dialogItemInfo.title.fontSize,
            textAlign = dialogItemInfo.title.textAlign,
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    override fun BuildContent() {
        val scrollState = rememberScrollState()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
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
                            painter = if (trailingIconList.isNullOrEmpty()) {
                                if (index == selectedIndex.value) {
                                    painterResource(R.drawable.radio_button_checked)
                                } else {
                                    painterResource(R.drawable.radio_button_unchecked)
                                }
                            } else {
                                painterResource(trailingIconList[index])
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

    @Composable
    override fun BuildButtons(
        dialogItemInfo: AppDialogItemInfo,
        modifier: Modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = modifier
                .height(50.dp)
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
}

@Preview
@Composable
fun RadioDialogPreview() {
    val dialog = RadioDialogBuilder(
        options = listOf(
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
        ),
        selectedIndex = remember { mutableIntStateOf(0) }
    )

    val dialogItemInfo = AppDialogItemInfo(
        title = DialogTitle("Radio Dialog"),
        positiveButton = DialogButton(stringResource(R.string.gen_save)),
        negativeButton = DialogButton(stringResource(R.string.gen_cancel))
    )

    Column(
        modifier = Modifier
            .width(300.dp)
            .height(280.dp)
            .background(color = Color.LightGray, shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 26.dp, vertical = 20.dp)
    ) {
        dialog.BuildHeader(dialogItemInfo)
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp),
            color = Color.Black,
            thickness = 1.dp
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            dialog.BuildContent()
        }

        dialog.BuildButtons(
            dialogItemInfo,
            Modifier.fillMaxWidth()
        )
    }
}