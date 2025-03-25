package ru.mareanexx.brothersbirthdayapp.data.model.crossword

data class Cell(
    val colNum: Int,
    val rowNum: Int,
    var isAvailable: Boolean = false,
    var letter: Char = ' ',
    var wordPair: Pair<Word?, Word?>? = null
)

fun Cell.getFirstWord() = this.wordPair?.first
fun Cell.getSecondWord() = this.wordPair?.second