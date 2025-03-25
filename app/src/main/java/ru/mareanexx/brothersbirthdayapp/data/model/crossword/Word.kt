package ru.mareanexx.brothersbirthdayapp.data.model.crossword

data class Word(
    val number: Int,
    val startCol: Int,
    val startRow: Int,
    val actualText: String,
    val task: String,
    val direction: Direction
)