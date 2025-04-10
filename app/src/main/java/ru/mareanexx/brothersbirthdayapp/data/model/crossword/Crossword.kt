package ru.mareanexx.brothersbirthdayapp.data.model.crossword

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import ru.mareanexx.brothersbirthdayapp.data.repo.WordDB
import ru.mareanexx.brothersbirthdayapp.ui.screens.crossword.components.CrosswordDialogStates
import ru.mareanexx.brothersbirthdayapp.ui.screens.crossword.components.CrosswordDialogStates.OPENED_CORRECT
import ru.mareanexx.brothersbirthdayapp.ui.screens.crossword.components.CrosswordDialogStates.OPENED_INCORRECT

object Crossword {
    var selectedWord: MutableState<Word?> = mutableStateOf(null)

    val grid: Array<Array<Cell>> = Array(18) { row -> Array(17) { col -> Cell(col, row) } }

    val words: List<Word> = WordDB.repository

    init {
        for (word in words) {
            addCellsToGrid(word)
        }
    }

    private fun addCellsToGrid(word: Word) {
        word.actualText.forEachIndexed { index, letter ->
            val rowNum = if (word.direction == Direction.HORIZONTALLY) word.startRow else word.startRow + index
            val colNum = if (word.direction == Direction.HORIZONTALLY) word.startCol + index else word.startCol
            val tempGridElement = grid[rowNum][colNum]

            if (tempGridElement.wordPair == null) {
                grid[rowNum][colNum] = Cell(
                    colNum = colNum,
                    rowNum = rowNum,
                    isAvailable = true,
                    letter = letter,
                    wordPair = Pair(word, null)
                )
            } else {
                val prevPair = grid[rowNum][colNum].wordPair
                grid[rowNum][colNum].apply {
                    wordPair = Pair(prevPair!!.first, word)
                }
            }
        }
    }

    fun checkCrosswordAnswers(
        textFieldStates: List<MutableState<String>>
    ): CrosswordDialogStates {
        val listOfRightAnswers = mutableListOf<Word>()
        for (word in words) {
            val enteredWord: StringBuilder = StringBuilder()
            word.actualText.forEachIndexed { index, letter ->
                if (word.direction == Direction.HORIZONTALLY) {
                    enteredWord.append(textFieldStates[word.startRow * 17 + word.startCol + index].value)
                }
                else {
                    enteredWord.append(textFieldStates[word.startRow * 17 + index * 17 + word.startCol].value)
                }
            }
            Log.d("CHECK_ANSWERS", "enteredWord = $enteredWord, realWord = ${word.actualText}")
            if (enteredWord.toString() == word.actualText) {
                listOfRightAnswers.add(word)
            }
        }

        Log.d("LIST_ANSWERS", "rightAnswersSize = ${listOfRightAnswers.size}")
        Log.d("LIST_ANSWERS", "rightAnswerList = ${listOfRightAnswers.joinToString { word -> word.actualText }}")

        return if (listOfRightAnswers.size == words.size) OPENED_CORRECT else OPENED_INCORRECT
    }
}