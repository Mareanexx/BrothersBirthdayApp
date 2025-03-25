package ru.mareanexx.brothersbirthdayapp.ui.view.components.crossword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val topKeys = listOf('Й', 'Ц', 'У', 'К', 'Е', 'Н', 'Г', 'Ш', 'Щ', 'З', 'Х', 'Ъ')
val middleKeys = listOf('Ф', 'Ы', 'В', 'А', 'П', 'Р', 'О', 'Л', 'Д', 'Ж', 'Э')
val bottomKeys = listOf('Я', 'Ч', 'С', 'М', 'И', 'Т', 'Ь', 'Б', 'Ю')

@Composable
fun CrosswordKeyboard(
    onKeyClicked: (Char) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, bottom = 25.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        KeyboardRow(topKeys, onKeyClicked)
        KeyboardRow(middleKeys, onKeyClicked)
        KeyboardRow(bottomKeys, onKeyClicked)
    }
}

@Composable
fun KeyboardRow(
    listKeys: List<Char>,
    onKeyClicked: (Char) -> Unit
) {
    LazyRow(
        modifier = Modifier.height(45.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(listKeys) {
                key: Char -> KeyboardLetter(key, onKeyClicked)
        }
    }
}

@Composable
fun KeyboardLetter(
    letter: Char,
    onClickKey: (Char) -> Unit
) {
    TextButton (
        modifier = Modifier
            .width(28.dp)
            .fillMaxHeight(),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFf0f0f0),
            contentColor = Black
        ),
        contentPadding = PaddingValues(horizontal = 3.dp),
        onClick = { onClickKey(letter) }
    ) {
        Text(
            text = "$letter",
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium
        )
    }
}