package ru.mareanexx.brothersbirthdayapp.data.model.crossword

data class Letter(
    val letter: Char = ' ',
    val linkedWord: Word? = null
) {
    val isDrawn: Boolean
        get() = letter != ' '
}