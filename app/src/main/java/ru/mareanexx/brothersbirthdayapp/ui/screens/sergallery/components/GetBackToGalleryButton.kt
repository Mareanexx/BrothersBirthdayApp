package ru.mareanexx.brothersbirthdayapp.ui.screens.sergallery.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes

@Composable
fun GetBackToGalleryButton(
    navController: NavController?
) {
    IconButton(
        modifier = Modifier
            .padding(top = 10.dp, start = 10.dp)
            .background(
                color = Color.White.copy(alpha = 0.3f),
                shape = Shapes.medium
            ),
        onClick = { navController?.popBackStack() }
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            tint = Color.White,
            contentDescription = null
        )
    }
}
