package ru.mareanexx.brothersbirthdayapp.ui.screens.crossword

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.model.crossword.Crossword
import ru.mareanexx.brothersbirthdayapp.data.model.crossword.Direction
import ru.mareanexx.brothersbirthdayapp.data.model.crossword.Word
import ru.mareanexx.brothersbirthdayapp.data.repo.GamesDB
import ru.mareanexx.brothersbirthdayapp.ui.components.SuccessInFinishGameDialog
import ru.mareanexx.brothersbirthdayapp.ui.components.TopAppBarInGames
import ru.mareanexx.brothersbirthdayapp.ui.screens.crossword.components.AllQuestionsAndCheckButton
import ru.mareanexx.brothersbirthdayapp.ui.screens.crossword.components.AreYouSureDialog
import ru.mareanexx.brothersbirthdayapp.ui.screens.crossword.components.CrosswordDialogStates.ALL_CLOSED
import ru.mareanexx.brothersbirthdayapp.ui.screens.crossword.components.CrosswordDialogStates.OPENED_CORRECT
import ru.mareanexx.brothersbirthdayapp.ui.screens.crossword.components.CrosswordDialogStates.OPENED_INCORRECT
import ru.mareanexx.brothersbirthdayapp.ui.screens.crossword.components.CrosswordDialogStates.OPENED_SUCCESS
import ru.mareanexx.brothersbirthdayapp.ui.screens.crossword.components.CrosswordDialogStates.OPENED_SURE
import ru.mareanexx.brothersbirthdayapp.ui.screens.crossword.components.CrosswordInputCell
import ru.mareanexx.brothersbirthdayapp.ui.screens.crossword.components.CrosswordKeyboard
import ru.mareanexx.brothersbirthdayapp.ui.screens.crossword.components.CrosswordNotAvailableCell
import ru.mareanexx.brothersbirthdayapp.ui.screens.crossword.components.TasksLazyColumn
import ru.mareanexx.brothersbirthdayapp.ui.screens.games.components.CorrectAnswerDialog
import ru.mareanexx.brothersbirthdayapp.ui.screens.games.components.IncorrectAnswerDialog
import ru.mareanexx.brothersbirthdayapp.ui.theme.crosswordTopBarBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.crosswordTopBarMain
import ru.mareanexx.brothersbirthdayapp.utils.DataStore
import ru.mareanexx.brothersbirthdayapp.utils.GameTypeSP
import ru.mareanexx.brothersbirthdayapp.utils.helperNavBack


@Composable
fun CrosswordScreen(navController: NavController?, dataStore: DataStore) {
    val coroutineScope = rememberCoroutineScope()
    val isGameCompleted by dataStore.isCrosswordCompleted.collectAsState(false)
    val numberOfCoins by dataStore.numberOfCoins.collectAsState(initial = 0)

    val crosswordObject = Crossword
    val horizontalScrollState = rememberScrollState(200)
    val verticalScrollState = rememberScrollState(200)

    val focusRequesters = List(17 * 18) { remember { FocusRequester() } }
    val textFieldStates = List(17 * 18) { remember { mutableStateOf("") } }

    var currentFocusIndex by remember { mutableStateOf<Int?>(null) }
    val openDialog = remember { mutableStateOf(ALL_CLOSED) }

    when(openDialog.value) {
        OPENED_SURE -> {
            AreYouSureDialog(
                onYesButtonClick = {
                    openDialog.value = crosswordObject.checkCrosswordAnswers(textFieldStates)
                },
                onNoButtonClick = {
                    openDialog.value = ALL_CLOSED
                }
            )
        }
        OPENED_CORRECT -> {
            CorrectAnswerDialog {
                if (isGameCompleted) {
                    navController?.popBackStack()
                } else {
                    openDialog.value = OPENED_SUCCESS
                }
            }
        }
        OPENED_SUCCESS -> {
            val reward = GamesDB.gameRepository[3].reward
            SuccessInFinishGameDialog(
                rewardSize = reward,
                watchedOrCompleted = "прошел",
                onGetCoinsClick = {
                    openDialog.value = ALL_CLOSED
                    coroutineScope.launch {
                        dataStore.saveNumberOfCoins(numberOfCoins + reward)
                        dataStore.setGameCompleted(GameTypeSP.CROSSWORD)
                    }
                    navController.helperNavBack()
                }
            )
        }
        OPENED_INCORRECT -> {
            IncorrectAnswerDialog {
                openDialog.value = ALL_CLOSED
            }
        }
        ALL_CLOSED -> {}
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TopAppBarInGames(
            mainColor = crosswordTopBarMain,
            backgroundColor = crosswordTopBarBackground,
            titleRes = R.string.crossword,
            onPreviousButtonClick = { navController?.popBackStack() }
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(15.dp)
                .horizontalScroll(horizontalScrollState)
                .verticalScroll(verticalScrollState)
        ) {
            (0..17).forEach { row ->
                Row {
                    (0..16).forEach { col ->
                        val index = row * 17 + col
                        val cell = crosswordObject.grid[row][col]
                        if (cell.isAvailable) {
                            CrosswordInputCell(
                                cell = cell,
                                textFieldState = textFieldStates[index],
                                focusRequester = focusRequesters[index]
                            )
                        }
                        else {
                            CrosswordNotAvailableCell()
                        }
                    }
                }
            }
        }
        AllQuestionsAndCheckButton { openDialog.value = OPENED_SURE }

        TasksLazyColumn(selectedWord = crosswordObject.selectedWord, onChooseTask = {
            word: Word -> crosswordObject.selectedWord.value = word
            val focusToIndex = word.startRow * 17 + word.startCol
            currentFocusIndex = focusToIndex
            focusRequesters[focusToIndex].requestFocus()
        })

        CrosswordKeyboard {clickedLetter ->
            val selectedWord = crosswordObject.selectedWord.value
            if (selectedWord != null && currentFocusIndex != null) {
                textFieldStates[currentFocusIndex!!].value = clickedLetter.toString()

                val nextIndex = if (selectedWord.direction == Direction.HORIZONTALLY) {
                    val nextColumn = (currentFocusIndex!! % 17) + 1
                    if (nextColumn < 17 && nextColumn < selectedWord.startCol + selectedWord.actualText.length) {
                        (currentFocusIndex!! / 17) * 17 + nextColumn
                    } else null
                } else {
                    val nextRow = (currentFocusIndex!! / 17) + 1
                    if (nextRow < 18 && nextRow < selectedWord.startRow + selectedWord.actualText.length) {
                        nextRow * 17 + (currentFocusIndex!! % 17)
                    } else null
                }

                nextIndex?.let {
                    currentFocusIndex = it
                    focusRequesters[it].requestFocus()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCrosswordScreen() {
    CrosswordScreen(null, DataStore(LocalContext.current))
}