package ru.mareanexx.brothersbirthdayapp.ui.screens.crossword.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mareanexx.brothersbirthdayapp.data.model.crossword.Crossword
import ru.mareanexx.brothersbirthdayapp.data.model.crossword.Word
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes


@Composable
fun TasksLazyColumn(
    selectedWord: MutableState<Word?>,
    onChooseTask: (Word) -> Unit
) {

    val crossword = Crossword
    LazyColumn(
        modifier = Modifier.height(130.dp),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        items(crossword.words) {
                word -> TaskItem(
            word = word,
            isSelected = word == selectedWord.value,
            onChooseTask = onChooseTask
        )
        }
    }
}

@Composable
fun TaskItem(
    word: Word,
    isSelected: Boolean,
    onChooseTask: (Word) -> Unit
) {
    val backgroundColor = if (isSelected) Color(0xFFAFFFB0) else Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = Shapes.small)
            .padding(vertical = 5.dp, horizontal = 10.dp)
            .clickable {
                onChooseTask(word)
            }
    ) {
        Text(
            text = "${word.number}.   ${word.task}",
            fontSize = 16.sp,
            lineHeight = 17.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = MontserratFamily,
        )
    }
}