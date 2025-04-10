package ru.mareanexx.brothersbirthdayapp.ui.screens.quest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.ui.screens.games.components.CorrectAnswerDialog
import ru.mareanexx.brothersbirthdayapp.ui.screens.games.components.IncorrectAnswerDialog
import ru.mareanexx.brothersbirthdayapp.ui.screens.quest.components.GuessCodeBottomSheet
import ru.mareanexx.brothersbirthdayapp.ui.theme.questMainBackground

@Composable
fun GuessTheCodeScreen(navController: NavController?) {
    val firstNumber = rememberTextFieldState()
    val secondNumber = rememberTextFieldState()
    val thirdNumber = rememberTextFieldState()
    val fourthNumber = rememberTextFieldState()

    val openCorrectDialog = remember { mutableIntStateOf(0) }

    when(openCorrectDialog.intValue) {
        1 -> CorrectAnswerDialog {
            openCorrectDialog.intValue = 0
            navController?.popBackStack()
        }
        2 -> IncorrectAnswerDialog {
            openCorrectDialog.intValue = 0
            firstNumber.clearText()
            secondNumber.clearText()
            thirdNumber.clearText()
            fourthNumber.clearText()
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = questMainBackground)
    ) {
        SpiesBackgroundImage("quest/guess_code.png")
        GuessCodeBottomSheet(
            firstNumber, secondNumber, thirdNumber, fourthNumber,
            Modifier.align(Alignment.BottomCenter),
            openCorrectDialog
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewGuessTheCodeScreen() {
    GuessTheCodeScreen(null)
}