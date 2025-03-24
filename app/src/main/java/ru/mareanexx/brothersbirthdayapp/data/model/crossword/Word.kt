package ru.mareanexx.brothersbirthdayapp.data.model.crossword

data class Word(
    val number: Int,
    val wordText: String,
    val task: String,
    val startRow: Int,
    val startCol: Int,
    val isHorizontal: Boolean
)