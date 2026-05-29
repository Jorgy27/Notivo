package com.notivo.common.view.composables.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.notivo.common.R
import com.notivo.common.view.configs.AppFloatingButtonConfig

@Composable
fun AppFloatingButton(
    floatingButtonInfo: AppFloatingButtonConfig.AppFloatingButtonInfo,
    modifier: Modifier = Modifier,
    onOptionClicked: (AppFloatingButtonConfig.FloatingButtonAction) -> Unit
) {
    val items: List<@Composable (() -> Unit)> = floatingButtonInfo.childrenInfo.map { info ->
        {
            CircularFloatingButton(
                modifier = Modifier,
                buttonSize = 49.dp,
                iconSize = 24.dp,
                iconTint = R.color.black,
                backgroundColor = R.color.accessColor,
                floatingShadowConfig = null,
                iconRes = info.icon,
                onClick = { onOptionClicked(info.action) }
            )
        }
    }

    RadialFloatingButton(
        modifier = modifier
            .padding(15.dp)
            .size(64.dp),
        distanceRadius = 60.dp,
        buttonSize = 55.dp,
        items = items,
        iconSize = 30.dp,
        iconTint = R.color.black,
        backgroundColor = R.color.accessColor,
        floatingShadowConfig = FloatingShadowConfig(
            R.color.accessShadowColor,
            R.color.accessShadowColor
        ),
        iconRes = R.drawable.ic_add
    )
}
