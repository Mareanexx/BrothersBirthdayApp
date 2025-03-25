package ru.mareanexx.brothersbirthdayapp.ui.view.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.model.crossword.Cell
import ru.mareanexx.brothersbirthdayapp.data.model.crossword.Crossword
import ru.mareanexx.brothersbirthdayapp.data.model.crossword.Direction
import ru.mareanexx.brothersbirthdayapp.data.model.crossword.Word
import ru.mareanexx.brothersbirthdayapp.ui.theme.crosswordTopBarBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.crosswordTopBarMain
import ru.mareanexx.brothersbirthdayapp.ui.view.components.TopAppBarInGames
import ru.mareanexx.brothersbirthdayapp.ui.view.components.crossword.AllQuestionsAndCheckButton
import ru.mareanexx.brothersbirthdayapp.ui.view.components.crossword.AreYouSureDialog
import ru.mareanexx.brothersbirthdayapp.ui.view.components.crossword.CrosswordDialogStates.ALL_CLOSED
import ru.mareanexx.brothersbirthdayapp.ui.view.components.crossword.CrosswordDialogStates.OPENED_CORRECT
import ru.mareanexx.brothersbirthdayapp.ui.view.components.crossword.CrosswordDialogStates.OPENED_INCORRECT
import ru.mareanexx.brothersbirthdayapp.ui.view.components.crossword.CrosswordDialogStates.OPENED_SURE
import ru.mareanexx.brothersbirthdayapp.ui.view.components.crossword.CrosswordKeyboard
import ru.mareanexx.brothersbirthdayapp.ui.view.components.crossword.SmallTextNumberInCorner
import ru.mareanexx.brothersbirthdayapp.ui.view.components.crossword.TasksLazyColumn
import ru.mareanexx.brothersbirthdayapp.ui.view.components.games.CorrectAnswerDialog
import ru.mareanexx.brothersbirthdayapp.ui.view.components.games.IncorrectAnswerDialog


@Composable
fun CrosswordScreen(navController: NavController?) {
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
                openDialog.value = ALL_CLOSED
                // TODO() - здесь реализовать проверку на дать деньги или не дать
                navController?.popBackStack()
            }
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
            navController = navController,
            mainColor = crosswordTopBarMain,
            backgroundColor = crosswordTopBarBackground,
            titleRes = R.string.crossword
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CrosswordInputCell(
    cell: Cell,
    textFieldState: MutableState<String>,
    focusRequester: FocusRequester
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    var borderColor by remember { mutableStateOf(Black) }

    Box(
        modifier = Modifier
            .size(32.dp)
            .border(width = 1.dp, color = borderColor)
            .background(color = Color(0xFFf0f0f0))
            .bringIntoViewRequester(bringIntoViewRequester),
    ) {
        if (cell.colNum == cell.wordPair?.first?.startCol &&
            cell.rowNum == cell.wordPair?.first?.startRow) {
            SmallTextNumberInCorner(cell.wordPair?.first?.number, Modifier.align(Alignment.TopStart))
        }
        if (cell.wordPair?.second != null && cell.colNum == cell.wordPair!!.second!!.startCol &&
            cell.rowNum == cell.wordPair!!.second!!.startRow) {
            SmallTextNumberInCorner(cell.wordPair?.second?.number, Modifier.align(Alignment.TopStart))
        }

        Text(
            text = textFieldState.value,
            modifier = Modifier
                .align(Alignment.Center)
                .focusRequester(focusRequester)
                .onFocusChanged {
                    borderColor = if (it.isFocused) Green else Black
                    if (it.isFocused) {
                        // Прокрутка, если элемент в фокусе
                        CoroutineScope(Dispatchers.Main).launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                }
                .focusable(enabled = true),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
        )
    }
}


@Composable
fun CrosswordNotAvailableCell() {
    Box(
        modifier = Modifier
            .size(32.dp)
            .border(width = 1.dp, color = Color.White)
            .background(color = Color.White)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCrosswordScreen() {
    CrosswordScreen(null)
}