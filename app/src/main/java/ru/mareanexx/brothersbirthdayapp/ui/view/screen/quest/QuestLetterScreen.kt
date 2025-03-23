package ru.mareanexx.brothersbirthdayapp.ui.view.screen.quest

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.view.components.games.CorrectAnswerDialog
import ru.mareanexx.brothersbirthdayapp.ui.view.components.games.IncorrectAnswerDialog

fun checkWords(
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

@Composable
fun RebusBlock(
    alignEndModifier: Modifier,
    onClickRollButton: () -> Unit
) {
    Image(
        painter = painterResource(R.drawable.rebus),
        contentDescription = "Rebuses"
    )
    Spacer(modifier = Modifier.height(44.dp))
    RollButton(alignEndModifier, onClickRollButton = onClickRollButton)
}

@Composable
fun LetterBlock(
    wrongTextField: TextFieldState,
    stopTextField: TextFieldState,
    saveTextField: TextFieldState,
    smartTextField: TextFieldState,
    lyingTextField: TextFieldState,
    alignEndModifier: Modifier,
    alignStartModifier: Modifier,
    onClickRollButton: () -> Unit
) {

    Text(
        modifier = alignEndModifier,
        text = "The Bad Street\nMarch 23, 2025",
        textAlign = TextAlign.Right,
        style = TextStyle(fontSize = 13.sp, lineHeight = 15.sp)
    )
    Text(
        modifier = alignStartModifier,
        text = "Agent Sergey\nThe Good Street\nApril 14, 2025",
        style = TextStyle(fontSize = 13.sp, lineHeight = 15.sp)
    )

    RowWithTextField(
        paddingModifier = Modifier.padding(top = 10.dp),
        textBefore = "You think your team is strong, but you are",
        textAfter = ".",
        inputText = wrongTextField
    )

    Text(
        modifier = Modifier.padding(top = 3.dp),
        text = "One of your teammates is actually working for me.",
        style = TextStyle(fontSize = 13.sp, lineHeight = 14.sp)
    )

    RowWithTextField(
        textBefore = "They are here to ",
        textAfter = " your mission!",
        inputText = stopTextField
    )

    RowWithTextField(
        textBefore = "If you want to ",
        textAfter = "the birthday, you must find the RAT before it’s too late.",
        inputText = saveTextField
    )

    RowWithTextField(
        textBefore = "If you are ",
        textAfter = ", and who is telling the truth.",
        inputText = smartTextField
    )

    RowWithTextField(
        paddingModifier = Modifier.padding(bottom = 10.dp),
        textBefore = "Check who is ",
        textAfter = " enough, you WILL find them.",
        inputText = lyingTextField
    )

    Text(text = "Good Luck, Agent.\nYou will need it.\nBugaga", style = TextStyle(fontSize = 13.sp, lineHeight = 15.sp))
    RollButton(alignEndModifier, onClickRollButton = onClickRollButton)
}

@Composable
fun RollButton(
    alignModifier: Modifier,
    onClickRollButton: () -> Unit
) {
    Button(
        modifier = alignModifier.padding(top = 20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF575757)
        ),
        contentPadding = PaddingValues(horizontal = 20.dp),
        onClick = onClickRollButton
    ) {
        Text(
            text = "ПЕРЕВЕРНУТЬ",
            fontFamily = MontserratFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            lineHeight = 13.sp
        )
    }
}

@Composable
fun RowWithTextField(
    paddingModifier: Modifier = Modifier,
    textBefore: String,
    textAfter: String,
    inputText: TextFieldState
) {
    Row(
        modifier = paddingModifier.padding(top = 3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = textBefore, style = TextStyle(fontSize = 13.sp, lineHeight = 13.sp))
        SimpleTextField(inputText)
        Text(text = textAfter, style = TextStyle(fontSize = 13.sp, lineHeight = 13.sp))
    }
}

@Composable
fun SimpleTextField(
    inputText: TextFieldState,
) {
    BasicTextField(
        modifier = Modifier
            .width(70.dp)
            .padding(horizontal = 5.dp)
            .background(color = Color(0xFFD9D9D9), shape = RoundedCornerShape(5.dp))
            .padding(5.dp),
        state = inputText,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
        textStyle = TextStyle(color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    )
}

@Composable
fun QuestLetterImageBackground() {
    val context = LocalContext.current
    val questTaskImageBitmap = BitmapFactory.decodeStream(context.assets.open("quest/letter_background.png")).asImageBitmap()

    Image(
        modifier = Modifier.fillMaxWidth(),
        bitmap = questTaskImageBitmap,
        contentDescription = "Quest Letter background Image",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun CheckLetterButton(
    alignModifier: Modifier,
    onClickCheckLetter: () -> Unit
) {
    Button(
        modifier = alignModifier
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .systemBarsPadding()
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFC2C2C2)
        ),
        onClick = onClickCheckLetter
    ) {
        Text(
            text = "ПРОВЕРИТЬ",
            fontFamily = MontserratFamily,
            fontSize = 22.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewQuestLetterScreen() {
    QuestLetterScreen(null)
}

