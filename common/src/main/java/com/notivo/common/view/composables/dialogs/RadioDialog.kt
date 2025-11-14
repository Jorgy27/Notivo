package com.notivo.common.view.composables.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.notivo.common.view.composables.builders.AppDialogUiBuilder
import com.notivo.common.utils.FontUtil

data class RadioDialog(
    val options: List<String?>,
    val selectedIndex: MutableState<Int>
) : AppDialogUiBuilder {

    @Composable
    override fun BuildHeader(title: String) {
        Text(
            text = title,
            color = Color.Black,
            fontFamily = FontUtil.fontBold,
            fontSize = FontUtil.bigTextSize,
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    override fun BuildContent() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
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
                    Text(
                        text = option ?: "",
                        color = Color.Black,
                        fontFamily = FontUtil.fontRegular,
                        fontSize = FontUtil.defaultTextSize
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RadioDialogPreview() {
    val dialog = RadioDialog(
        options = listOf("Option 1", "Option 2", "Option 3"),
        selectedIndex = mutableStateOf(0)
    )
    Column (modifier = Modifier
        .width(300.dp)
        .height(280.dp)
        .background(color = Color.LightGray, shape = RoundedCornerShape(20.dp))
        .padding(horizontal = 26.dp, vertical = 20.dp)
    ){
        dialog.BuildHeader("Radio Dialog")
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color.Black, thickness = 1.dp)
        dialog.BuildContent()
    }
}