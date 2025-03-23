package ru.mareanexx.brothersbirthdayapp.ui.view.screen.quest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.questMainBackground
import ru.mareanexx.brothersbirthdayapp.ui.view.components.games.CorrectAnswerDialog
import ru.mareanexx.brothersbirthdayapp.ui.view.components.games.IncorrectAnswerDialog

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

@Composable
fun GuessCodeBottomSheet(
    firstNumber: TextFieldState,
    secondNumber: TextFieldState,
    thirdNumber: TextFieldState,
    fourthNumber: TextFieldState,
    alignBottomModifier: Modifier,
    openCorrectDialog: MutableIntState
) {
    Column(
        modifier = alignBottomModifier
            .fillMaxWidth()
            .systemBarsPadding()
            .padding(horizontal = 15.dp)
            .background(color = Color.White.copy(alpha = 0.9f), shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 20.dp, vertical = 15.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "GUESS THE CODE",
            fontFamily = MontserratFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 15.dp, bottom = 15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OneNumberTextField(firstNumber)
            OneNumberTextField(secondNumber)
            OneNumberTextField(thirdNumber)
            OneNumberTextField(fourthNumber)
        }
        Box(modifier = Modifier.align(Alignment.End)) {
            SmallGrayButton(buttonText = "Check") {
                openCorrectDialog.intValue = checkInputCode(
                    firstNumber.text.toString().toIntOrNull() ?: 0,
                    secondNumber.text.toString().toIntOrNull() ?: 0,
                    thirdNumber.text.toString().toIntOrNull() ?: 0,
                    fourthNumber.text.toString().toIntOrNull() ?: 0,
                )
            }
        }
    }
}

fun checkInputCode(first: Int, second: Int, third: Int, fourth: Int): Int {
    return if (first == 1 && second == 4 && third == 2 && fourth == 3) {
        1
    } else 2
}

@Composable
fun OneNumberTextField(
    inputText: TextFieldState
) {
    BasicTextField(
        modifier = Modifier
            .size(width = 69.dp, height = 82.dp)
            .background(color = Color(0xFFCBCBCB), shape = RoundedCornerShape(10.dp))
            .padding(top = 10.dp),
        state = inputText,
        textStyle = TextStyle(fontSize = 48.sp, fontWeight = FontWeight.SemiBold, textDecoration = TextDecoration.Underline, textAlign = TextAlign.Center),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewGuessTheCodeScreen() {
    GuessTheCodeScreen(null)
}