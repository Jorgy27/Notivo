package com.notivo.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun HomeScreen(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .clickable { onClick() },// invoke the click lambda
        contentAlignment = Alignment.Center,
        propagateMinConstraints = false
    ) {
        Text(
            text = "Hello Home!",
            modifier = Modifier
        )
    }
}