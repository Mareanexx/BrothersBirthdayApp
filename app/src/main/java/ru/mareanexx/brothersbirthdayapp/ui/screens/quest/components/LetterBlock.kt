package ru.mareanexx.brothersbirthdayapp.ui.screens.quest.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily


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
        textAfter = " enough, you WILL find them.",
        inputText = smartTextField
    )

    RowWithTextField(
        paddingModifier = Modifier.padding(bottom = 10.dp),
        textBefore = "Check who is ",
        textAfter = ", and who is telling the truth.",
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