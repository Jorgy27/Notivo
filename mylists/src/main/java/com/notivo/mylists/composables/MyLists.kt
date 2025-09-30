package com.notivo.mylists.composables

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
fun MyListsScreen(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Magenta),
        contentAlignment = Alignment.Center,
        propagateMinConstraints = false
    ) {
        Text(
            text = "My Lists",
            modifier = Modifier
                .clickable { onClick() } // invoke the click lambda
        )
    }
}