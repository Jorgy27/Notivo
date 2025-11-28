package com.notivo.common.view.composables.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.notivo.common.R
import com.notivo.common.utils.FontUtil
import com.notivo.common.utils.previewDialogInfo
import com.notivo.common.view.composables.dialogs.core.AppDialog
import com.notivo.common.view.configs.DialogConfig

@Composable
fun OptionsDialog(
    modifier: Modifier = Modifier,
    options: List<String?>,
    trailingIconList: List<Int>? = null,
    dialogItemInfo: DialogConfig.AppDialogItemInfo,
    onClick: (Int) -> Unit
) {
    AppDialog(
        header = null,
        content = {
            OptionsDialogContent(
                options,
                trailingIconList,
                onClick
            )
        },
        buttons = null,
        modifier = modifier
    )
}

@Composable
private fun OptionsDialogContent(
    options: List<String?>,
    trailingIconList: List<Int>? = null,
    onClick: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ) {
        options.forEachIndexed { index, option ->
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { onClick.invoke(index) })
            ) {
                if (trailingIconList != null) {
                    Icon(
                        painter = painterResource(trailingIconList[index]),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 6.dp)
                    )
                }

                Text(
                    text = option ?: "",
                    color = Color.Black,
                    fontFamily = FontUtil.fontRegular,
                    fontSize = FontUtil.defaultTextSize,
                    modifier = Modifier
                )
            }
        }
    }
}

@Preview
@Composable
private fun OptionsDialogPreview() {
    val options = listOf(
        "Add sub-note",
        "Bookmark",
        "Private",
        "Folder",
        "Share",
    )

    val icons = listOf(
        R.drawable.note_stack_add,
        R.drawable.bookmark,
        R.drawable.lock,
        R.drawable.folder,
        R.drawable.share,
    )

    OptionsDialog(
        Modifier
            .width(180.dp),
        options,
        icons,
        previewDialogInfo,
    ) { }
}