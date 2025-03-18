package ru.mareanexx.brothersbirthdayapp.ui.view.components.games

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.R

@Composable
fun PreviousScreenButton(
    navController: NavController?,
    backgroundColor: Color,
    iconColor: Color
) {
    Icon(
        modifier = Modifier
            .size(28.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(10.dp)
            ).clickable {
                navController?.navigate("games") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    launchSingleTop = true
                }
            },
        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
        contentDescription = stringResource(R.string.arrow_back),
        tint = iconColor,
    )
}