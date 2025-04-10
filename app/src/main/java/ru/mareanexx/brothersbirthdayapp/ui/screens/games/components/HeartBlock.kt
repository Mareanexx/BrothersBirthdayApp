package ru.mareanexx.brothersbirthdayapp.ui.screens.games.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun OneHeartItem(
    heartColor: Color
) {
    Icon(
        imageVector = Icons.Default.Favorite,
        contentDescription = "Heart",
        tint = heartColor
    )
}