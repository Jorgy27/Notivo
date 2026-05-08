package com.notivo.common.view.composables.buttons

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.notivo.common.R
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun RadialFloatingButton(
    modifier: Modifier = Modifier,
    items: List<@Composable () -> Unit>,
    distanceRadius: Dp = 50.dp,
    buttonSize: Dp,
    iconSize: Dp,
    @DrawableRes iconRes: Int,
    @ColorRes iconTint: Int,
    floatingShadowConfig: FloatingShadowConfig? = null,
    @ColorRes backgroundColor: Int,
) {
    var visible by remember { mutableStateOf(false) }
    var isAnimating by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ),
        finishedListener = { isAnimating = false },
        label = "animatedAngleProgress"
    )
    val animatedScale by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ),
        finishedListener = { isAnimating = false },
        label = "animatedScale"
    )
    //Get the top left part of the circle.
    val startAngle = 180f
    val endAngle = 280f
    val currentSweep = (endAngle - startAngle) * progress

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        items.forEachIndexed { index, composable ->
            //Calculate the position of each item based on the angle.
            val angleStep = if (items.size > 1) {
                currentSweep / (items.size - 1)
            } else {
                0f
            }
            //StartAngle is added so each item starts from that part of the circle.
            val angleDeg = startAngle + angleStep * index
            val angle = Math.toRadians((angleDeg).toDouble())
            val x = distanceRadius * cos(angle).toFloat()
            val y = distanceRadius * sin(angle).toFloat()

            Box(
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = animatedScale
                        scaleY = animatedScale
                        transformOrigin = TransformOrigin.Center
                    }
                    .offset(x = x, y = y)
                    .size(30.dp)
            ) {
                composable()
            }
        }

        CircularFloatingButton(
            modifier = Modifier.fillMaxSize(),
            backgroundColor = backgroundColor,
            buttonSize = buttonSize,
            iconSize = iconSize,
            iconRes = iconRes,
            iconTint = iconTint,
            floatingShadowConfig = floatingShadowConfig,
            onClick = {
                if (!isAnimating) {
                    isAnimating = true
                    visible = !visible
                }
            }
        )
    }
}

@Composable
fun CircularFloatingButton(
    modifier: Modifier = Modifier,
    buttonSize: Dp,
    @ColorRes backgroundColor: Int,
    iconSize: Dp,
    @DrawableRes iconRes: Int,
    @ColorRes iconTint: Int,
    floatingShadowConfig: FloatingShadowConfig?,
    onClick: () -> Unit
) {

    val shadowSize = if (floatingShadowConfig != null) {
        buttonSize + 1.dp
    } else {
        buttonSize
    }
    val totalSize = shadowSize

    Box(
        modifier = modifier.size(totalSize),
        contentAlignment = Alignment.TopStart
    ) {

        // SHADOW LAYER (offset illusion)
        if (floatingShadowConfig != null) {
            Box(
                modifier = Modifier
                    .size(shadowSize)
                    .offset(x = 1.dp, y = 1.dp)
                    .shadow(
                        elevation = 10.dp,
                        shape = CircleShape,
                        ambientColor = colorResource(floatingShadowConfig.shadowAmbienceColor),
                        spotColor = colorResource(floatingShadowConfig.shadowAmbienceColor)
                    )
                    .background(
                        colorResource(floatingShadowConfig.shadowBackgroundColor),
                        CircleShape
                    )
            )
        }

        // BUTTON LAYER
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(buttonSize)
                .clip(CircleShape)
                .background(colorResource(backgroundColor))
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                tint = colorResource(iconTint),
                modifier = Modifier.size(iconSize)
            )
        }
    }
}

data class FloatingShadowConfig(
    @ColorRes val shadowAmbienceColor: Int,
    @ColorRes val shadowBackgroundColor: Int,
)

@Preview
@Composable
fun RadialFloatingButtonPreview() {
    val testOptionButton = CircularFloatingButton(
        modifier = Modifier,
        buttonSize = 49.dp,
        iconSize = 24.dp,
        iconTint = R.color.black,
        backgroundColor = R.color.accessColor,
        floatingShadowConfig = null,
        iconRes = R.drawable.ic_add,
        onClick = {}
    )

    RadialFloatingButton(
        modifier = Modifier
            .size(54.dp),
        buttonSize = 49.dp,
        items = listOf(
            { testOptionButton },
            { testOptionButton },
            { testOptionButton },
        ),
        iconSize = 24.dp,
        iconTint = R.color.black,
        backgroundColor = R.color.accessColor,
        floatingShadowConfig = FloatingShadowConfig(
            R.color.accessShadowColor,
            R.color.accessShadowColor
        ),
        iconRes = R.drawable.ic_add
    )
}