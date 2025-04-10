package ru.mareanexx.brothersbirthdayapp.ui.screens.quest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.ui.screens.games.components.CorrectAnswerDialog
import ru.mareanexx.brothersbirthdayapp.ui.screens.games.components.IncorrectAnswerDialog
import ru.mareanexx.brothersbirthdayapp.ui.screens.quest.components.CheckLetterButton
import ru.mareanexx.brothersbirthdayapp.ui.screens.quest.components.LetterBlock
import ru.mareanexx.brothersbirthdayapp.ui.screens.quest.components.QuestLetterImageBackground
import ru.mareanexx.brothersbirthdayapp.ui.screens.quest.components.RebusBlock

private fun checkWords(
    mustBeWrong: String,
    mustBeStop: String,
    mustBeSave: String,
    mustBeSmart: String,
    mustBeLying: String
): Int {
    return if (mustBeWrong.lowercase() == "wrong" && mustBeSave.lowercase() == "save" &&
        mustBeLying.lowercase() == "lying" && mustBeSmart.lowercase() == "smart" &&
        mustBeStop.lowercase() == "stop") {
        1
    } else 2
}


@Composable
fun QuestLetterScreen(navController: NavController?) {
    val isRollButtonClicked = remember { mutableStateOf(true) }
    val wrongTextField = rememberTextFieldState()
    val stopTextField = rememberTextFieldState()
    val saveTextField = rememberTextFieldState()
    val smartTextField = rememberTextFieldState()
    val lyingTextField = rememberTextFieldState()

    val openDialog = remember { mutableIntStateOf(0) }

    when(openDialog.intValue) {
        1 -> CorrectAnswerDialog { navController?.popBackStack() }
        2 -> IncorrectAnswerDialog {
            openDialog.intValue = 0
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        QuestLetterImageBackground()
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(color = Color.White)
                .padding(15.dp)
        ) {
            if (isRollButtonClicked.value) {
                LetterBlock(
                    wrongTextField = wrongTextField,
                    stopTextField = stopTextField,
                    smartTextField = smartTextField,
                    saveTextField = saveTextField,
                    lyingTextField = lyingTextField,
                    alignEndModifier = Modifier.align(Alignment.End),
                    alignStartModifier = Modifier.align(Alignment.Start),
                    onClickRollButton = {
                        isRollButtonClicked.value = !isRollButtonClicked.value
                })
            } else {
                RebusBlock(alignEndModifier = Modifier.align(Alignment.End), onClickRollButton = {
                    isRollButtonClicked.value = !isRollButtonClicked.value
                })
            }
        }
        CheckLetterButton(Modifier.align(Alignment.BottomCenter), onClickCheckLetter = {
            openDialog.intValue = checkWords(
                mustBeWrong = wrongTextField.text.toString(),
                mustBeStop = stopTextField.text.toString(),
                mustBeSave = saveTextField.text.toString(),
                mustBeSmart = smartTextField.text.toString(),
                mustBeLying = lyingTextField.text.toString()
            )
        })
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewQuestLetterScreen() {
    QuestLetterScreen(null)
}

