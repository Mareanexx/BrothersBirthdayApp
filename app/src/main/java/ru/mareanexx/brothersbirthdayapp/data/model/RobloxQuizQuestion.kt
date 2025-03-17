package ru.mareanexx.brothersbirthdayapp.data.model

import androidx.annotation.DrawableRes

data class RobloxQuizQuestion(
    val question: String,
    val answers: List<String>,
    val rightAnswer: Int
)

data class RobloxImage(
    @DrawableRes val imageRes: Int
)