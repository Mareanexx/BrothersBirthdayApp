package ru.mareanexx.brothersbirthdayapp.data.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import ru.mareanexx.brothersbirthdayapp.ui.theme.robloxTitle

data class GameCard(
    @DrawableRes val imageRes: Int,
    val reward: Int,
    val name: String,
    val caption: String,
    val backgroundColor: Color,
    val mainTextColor: Color,
    val captionTextColor: Color,
    val buttonColor: Color = robloxTitle
)