package ru.mareanexx.brothersbirthdayapp.ui.screens.roblox.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mareanexx.brothersbirthdayapp.data.model.RobloxQuizQuestion
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.blueAnswerButtonBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.blueAnswerText
import ru.mareanexx.brothersbirthdayapp.ui.theme.greenAnswerButtonBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.greenAnswerText
import ru.mareanexx.brothersbirthdayapp.ui.theme.redAnswerButtonBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.redAnswerText
import ru.mareanexx.brothersbirthdayapp.ui.theme.yellowAnswerButtonBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.yellowAnswerText


@Composable
fun GridListOfAnswers(
    questionItem: RobloxQuizQuestion,
    chosenVariant: MutableIntState,
    onChooseVariant: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        itemsIndexed(questionItem.answers) { index, textAnswer ->
            OneBlockOfAnswer(
                numberOfAnswer = index,
                textAnswer = textAnswer,
                isChosen = chosenVariant.intValue == index,
                textAndBackgroundColors = when (index) {
                    0 -> redAnswerButtonBackground to redAnswerText
                    1 -> blueAnswerButtonBackground to blueAnswerText
                    2 -> yellowAnswerButtonBackground to yellowAnswerText
                    else -> greenAnswerButtonBackground to greenAnswerText
                },
                onChooseVariant = {
                    onChooseVariant(it)
                    chosenVariant.intValue = it
                }
            )
        }
    }
}


@Composable
fun OneBlockOfAnswer(
    numberOfAnswer: Int,
    textAnswer: String,
    isChosen: Boolean,
    textAndBackgroundColors: Pair<Color, Color>,
    onChooseVariant: (Int) -> Unit
) {
    Button(
        modifier = Modifier
            .height(87.dp)
            .border(
                width = if (isChosen) 7.dp else 0.dp,
                color = if (isChosen) greenAnswerText else Color.Transparent,
                shape = Shapes.small
            ),
        shape = Shapes.small,
        colors = ButtonDefaults.buttonColors(textAndBackgroundColors.first),
        contentPadding = PaddingValues(),
        onClick = { onChooseVariant(numberOfAnswer) }
    ) {
        Text(
            text = textAnswer,
            color = textAndBackgroundColors.second,
            fontFamily = MontserratFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}