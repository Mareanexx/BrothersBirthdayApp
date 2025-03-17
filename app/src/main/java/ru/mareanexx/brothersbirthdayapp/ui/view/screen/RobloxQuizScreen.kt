package ru.mareanexx.brothersbirthdayapp.ui.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.model.RobloxImage
import ru.mareanexx.brothersbirthdayapp.data.model.RobloxQuizQuestion
import ru.mareanexx.brothersbirthdayapp.data.repo.RobloxQuizDatabase
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.aroundDigitColor
import ru.mareanexx.brothersbirthdayapp.ui.theme.blueAnswerButtonBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.blueAnswerText
import ru.mareanexx.brothersbirthdayapp.ui.theme.disabledCheckButton
import ru.mareanexx.brothersbirthdayapp.ui.theme.greenAnswerButtonBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.greenAnswerText
import ru.mareanexx.brothersbirthdayapp.ui.theme.heart
import ru.mareanexx.brothersbirthdayapp.ui.theme.redAnswerButtonBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.redAnswerText
import ru.mareanexx.brothersbirthdayapp.ui.theme.robloxQuizMainText
import ru.mareanexx.brothersbirthdayapp.ui.theme.robloxTopMenuBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.yellowAnswerButtonBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.yellowAnswerText
import ru.mareanexx.brothersbirthdayapp.ui.view.components.PreviousScreenButton
import ru.mareanexx.brothersbirthdayapp.ui.view.components.games.CorrectAnswerDialog
import ru.mareanexx.brothersbirthdayapp.ui.view.components.games.EndOfGameDialog
import ru.mareanexx.brothersbirthdayapp.ui.view.components.games.IncorrectAnswerDialog
import ru.mareanexx.brothersbirthdayapp.ui.view.components.games.OneHeartItem

@Composable
fun RobloxQuizScreen(navController: NavController?) {
    var numberOfQuestion by remember { mutableIntStateOf(0) }
    var tempImage by remember { mutableStateOf<RobloxImage>(RobloxQuizDatabase.imageRepository[numberOfQuestion]) }
    var tempQuestion by remember { mutableStateOf<RobloxQuizQuestion>(RobloxQuizDatabase.questionRepository[numberOfQuestion]) }
    val chosenVariant = remember { mutableIntStateOf(-1) }
    var isCheckButtonEnabled by remember { mutableStateOf(false) }
    var numberOfMistakes by remember { mutableIntStateOf(0) } // Кол-во ошибок

    val openCorrectAnswerDialog = remember { mutableIntStateOf(0) } // 0 - закрыты, 1 - верный ответ, 2 - неверный, 3 - конец игры

    if (numberOfMistakes == 3) {
        openCorrectAnswerDialog.intValue = 3
    }

    // Логика открытия Dialog окна
    when(openCorrectAnswerDialog.intValue) {
        1 -> {
            CorrectAnswerDialog( onDismissRequest = {
                openCorrectAnswerDialog.intValue = 0
                numberOfQuestion++
                tempImage = RobloxQuizDatabase.imageRepository[numberOfQuestion]
                tempQuestion = RobloxQuizDatabase.questionRepository[numberOfQuestion]
                chosenVariant.intValue = -1
            } )
        }
        2 -> {
            IncorrectAnswerDialog( onDismissRequest = {
                openCorrectAnswerDialog.intValue = 0
                numberOfMistakes++
                chosenVariant.intValue = -1
            } )
        }
        3 -> { EndOfGameDialog( onDismissRequest = {
            openCorrectAnswerDialog.intValue = 0
            navController?.navigate(route = "games") {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
                launchSingleTop = true
            }
        } ) }
    }

    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        TopMenuRobloxQuizScreen(navController, numberOfQuestion, numberOfMistakes) // Меню с кнопкой назад и полоской вопросов
        QuestionCard(tempImage, tempQuestion, chosenVariant) { // TODO() реализовать смену карточек
            newVariant -> chosenVariant.intValue = newVariant
            isCheckButtonEnabled = true
        }

        Spacer(modifier = Modifier.weight(1f))
        CheckButton(
            isCheckButtonEnabled = isCheckButtonEnabled,
            rightAnswer = tempQuestion.rightAnswer,
            chosenVariant = chosenVariant,
            onChooseCorrectAnswer = {
                openCorrectAnswerDialog.intValue = 1
                isCheckButtonEnabled = false
            },
            onChooseIncorrectAnswer = {
                openCorrectAnswerDialog.intValue = 2
                isCheckButtonEnabled = false
            }
        )
    }
}

@Composable
fun CheckButton(
    isCheckButtonEnabled: Boolean,
    rightAnswer: Int,
    chosenVariant: MutableIntState,
    onChooseCorrectAnswer: () -> Unit,
    onChooseIncorrectAnswer: () -> Unit
) {
    Row( // Row с кнопкой проверить
        modifier = Modifier
            .systemBarsPadding()
            .padding(horizontal = 20.dp)
            .height(52.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxSize(),
            shape = Shapes.small,
            enabled = isCheckButtonEnabled,
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = disabledCheckButton,
                contentColor = Color.White,
                containerColor = greenAnswerText,
                disabledContentColor = Color.White
            ),
            onClick = {
                if (chosenVariant.intValue == rightAnswer) onChooseCorrectAnswer() else onChooseIncorrectAnswer()
            }
        ) {
            Text(
                text = "ПРОВЕРИТЬ",
                fontFamily = MontserratFamily,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun QuestionCard(
    robloxQuizImage: RobloxImage,
    robloxQuizQuestion: RobloxQuizQuestion,
    chosenVariant: MutableIntState,
    onChooseVariant: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(robloxQuizImage.imageRes),
            contentDescription = "Roblox Funny Picture",
            contentScale = ContentScale.Crop
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                modifier = Modifier.size(48.dp),
                painter = painterResource(R.drawable.question_icon),
                contentDescription = "Question Icon"
            )
            Text(
                text = robloxQuizQuestion.question,
                fontSize = 22.sp,
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.Bold,
                lineHeight = 22.sp
            )
        }
        GridListOfAnswers(robloxQuizQuestion, chosenVariant, onChooseVariant)
    }
}

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
            fontSize = 20.sp,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}



@Composable
fun TopMenuRobloxQuizScreen(
    navController: NavController?,
    numberOfQuestion: Int,
    numberOfMistakes: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = robloxTopMenuBackground,
                shape = RoundedCornerShape(bottomEnd = 25.dp, bottomStart = 25.dp)
            )
            .systemBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PreviousScreenButton(navController, robloxQuizMainText, Color.White)
            Text(
                modifier = Modifier.padding(start = 40.dp),
                text = stringResource(R.string.roblox_quiz),
                color = robloxQuizMainText,
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                lineHeight = 24.sp
            )
            Row(
            ) {
                OneHeartItem( if (numberOfMistakes >= 1) Color.White else heart )
                OneHeartItem( if (numberOfMistakes >= 2) Color.White else heart )
                OneHeartItem( if (numberOfMistakes >= 3) Color.White else heart )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 38.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (i in 1..10) {
                if(i - 1 == numberOfQuestion)
                    NumberRowElement(i, Modifier
                        .size(24.dp)
                        .background(color = aroundDigitColor, shape = Shapes.extraLarge)
                    )
                else
                    NumberRowElement(i)
            }
        }
    }
}



@Composable
fun NumberRowElement(
    digit: Int,
    modifier: Modifier = Modifier.size(24.dp)
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$digit",
            fontFamily = MontserratFamily,
            fontSize = 15.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
        )
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRobloxQuizScreen() {
    RobloxQuizScreen(null)
}