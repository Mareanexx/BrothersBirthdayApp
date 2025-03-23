package ru.mareanexx.brothersbirthdayapp.data.model

data class QuestTaskInfo(
    val id: Int,
    val title: String,
    val text: String,
    val rightButtonText: String,
    val imagePath: String,
    val navigateTo: String = ""
)
