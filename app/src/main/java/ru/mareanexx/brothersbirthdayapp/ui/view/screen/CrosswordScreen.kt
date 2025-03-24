package ru.mareanexx.brothersbirthdayapp.ui.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.selectAll
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.model.crossword.CrosswordField
import ru.mareanexx.brothersbirthdayapp.data.model.crossword.Letter
import ru.mareanexx.brothersbirthdayapp.data.model.crossword.Word
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.crosswordTopBarBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.crosswordTopBarMain
import ru.mareanexx.brothersbirthdayapp.ui.view.components.TopAppBarInGames


@Composable
fun CrosswordScreen(navController: NavController?) {
    val CF = CrosswordField
    val horizontalScrollState = rememberScrollState(200)
    val verticalScrollState = rememberScrollState(200)

    val focusRequesters = List(17 * 18) { FocusRequester() }
    val textFieldStates = List(17 * 18) { rememberTextFieldState() }

    val selectedWord = remember { mutableStateOf<Word?>(null) }

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
                        val letter = CF.grid[row][col]

                        CrosswordCell(
                            rowNumber = row,
                            colNumber = col,
                            letter = letter,
                            textFieldState = if (letter.linkedWord == null) null else textFieldStates[index],
                            focusRequester = if (letter.linkedWord == null) null else focusRequesters[index],
                            selectedWord = selectedWord.value,
                            onWordSelected = { word ->
                                selectedWord.value = word
                            },
                            focusRequesters = focusRequesters
                        )
                    }
                }
            }
        }

        // Блок с текстом задания
        selectedWord.value?.let { word ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = crosswordTopBarBackground)
                    .padding(16.dp)
                    .imePadding(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "ВОПРОС",
                        fontSize = 20.sp,
                        color = crosswordTopBarMain,
                        fontFamily = MontserratFamily,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = word.task,
                        fontFamily = MontserratFamily,
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun CrosswordCell(
    rowNumber: Int,
    colNumber: Int,
    letter: Letter,
    textFieldState: TextFieldState?,
    focusRequester: FocusRequester?,
    selectedWord: Word?,
    onWordSelected: (Word) -> Unit,
    focusRequesters: List<FocusRequester>
) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .border(width = 1.dp, color = if (letter.isDrawn) Color.Black else Color.White)
            .background(color = if (letter.isDrawn) Color(0xFFf0f0f0) else Color.White),
        contentAlignment = Alignment.Center
    ) {
        if (textFieldState != null && focusRequester != null && letter.isDrawn) {
            BasicTextField(
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        if (it.isFocused) {
                            textFieldState.edit { selectAll() }
                            letter.linkedWord?.let { word -> onWordSelected(word) }
                        }
                    },
                state = textFieldState,
                cursorBrush = SolidColor(Color.Unspecified),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                inputTransformation = InputTransformation.maxLength(1),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
            )

            LaunchedEffect(textFieldState.text.toString()) {
                if (letter.linkedWord != null && textFieldState.text.isNotEmpty()) {
                    val word = selectedWord ?: return@LaunchedEffect

                    val nextIndex = if (word.isHorizontal) {
                        val nextCol = colNumber + 1
                        if (nextCol < word.startCol + word.wordText.length) {
                            letter.linkedWord.startRow * 17 + nextCol
                        } else null
                    } else {
                        val nextRow = rowNumber + 1
                        if (nextRow < word.startRow + word.wordText.length) {
                            nextRow * 17 + letter.linkedWord.startCol
                        } else null
                    }

                    nextIndex?.let { focusRequesters[it].requestFocus() }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCrosswordScreen() {
    CrosswordScreen(null)
}