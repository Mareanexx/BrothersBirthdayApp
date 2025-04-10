package ru.mareanexx.brothersbirthdayapp.ui.screens.roblox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.mareanexx.brothersbirthdayapp.data.model.RobloxImage
import ru.mareanexx.brothersbirthdayapp.data.model.RobloxQuizQuestion
import ru.mareanexx.brothersbirthdayapp.data.repo.GamesDB
import ru.mareanexx.brothersbirthdayapp.data.repo.RobloxQuizDB
import ru.mareanexx.brothersbirthdayapp.ui.components.SuccessInFinishGameDialog
import ru.mareanexx.brothersbirthdayapp.ui.screens.games.components.CorrectAnswerDialog
import ru.mareanexx.brothersbirthdayapp.ui.screens.games.components.EndOfGameDialog
import ru.mareanexx.brothersbirthdayapp.ui.screens.games.components.IncorrectAnswerDialog
import ru.mareanexx.brothersbirthdayapp.ui.screens.roblox.components.QuestionCard
import ru.mareanexx.brothersbirthdayapp.ui.screens.roblox.components.TopMenuRobloxQuizScreen
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.disabledCheckButton
import ru.mareanexx.brothersbirthdayapp.ui.theme.greenAnswerText
import ru.mareanexx.brothersbirthdayapp.utils.DataStore
import ru.mareanexx.brothersbirthdayapp.utils.GameTypeSP

@Composable
fun RobloxQuizScreen(navController: NavController?, dataStore: DataStore) {
    RobloxQuizDB.questionRepository.shuffle()
    RobloxQuizDB.imageRepository.shuffle()

    var numberOfQuestion by remember { mutableIntStateOf(0) }
    var tempImage by remember { mutableStateOf<RobloxImage>(RobloxQuizDB.imageRepository[numberOfQuestion]) }
    var tempQuestion by remember { mutableStateOf<RobloxQuizQuestion>(RobloxQuizDB.questionRepository[numberOfQuestion]) }
    val chosenVariant = remember { mutableIntStateOf(-1) }
    var isCheckButtonEnabled by remember { mutableStateOf(false) }
    var numberOfMistakes by remember { mutableIntStateOf(0) } // Кол-во ошибок
    val numberOfRightAnswers = remember { mutableIntStateOf(0) }

    val openCorrectAnswerDialog = remember { mutableIntStateOf(0) } // 0 - закрыты, 1 - верный ответ, 2 - неверный, 3 - конец игры

    val coroutineScope = rememberCoroutineScope()
    val isGameCompleted by dataStore.isRobloxGameCompleted.collectAsState(false)
    val numberOfCoins by dataStore.numberOfCoins.collectAsState(0)

    if (numberOfRightAnswers.intValue == 10) {
        if (!isGameCompleted) {
            val reward = GamesDB.gameRepository[0].reward
            SuccessInFinishGameDialog(
                rewardSize = reward,
                watchedOrCompleted = "прошел",
            ) {
                coroutineScope.launch {
                    dataStore.saveNumberOfCoins(numberOfCoins + reward)
                    dataStore.setGameCompleted(GameTypeSP.ROBLOX)
                }
                navController?.navigate(route = "games") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    launchSingleTop = true
                }
            }
        } else {
            navController?.navigate(route = "games") {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    if (numberOfMistakes == 3) {
        openCorrectAnswerDialog.intValue = 3
    }

    // Логика открытия Dialog окна
    when(openCorrectAnswerDialog.intValue) {
        1 -> {
            CorrectAnswerDialog( onDismissRequest = {
                openCorrectAnswerDialog.intValue = 0
                numberOfRightAnswers.intValue += 1
                numberOfQuestion++
                tempImage = RobloxQuizDB.imageRepository[ if(numberOfQuestion < 10) numberOfQuestion else 0 ]
                tempQuestion = RobloxQuizDB.questionRepository[numberOfQuestion]
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRobloxQuizScreen() {
    RobloxQuizScreen(null, DataStore(LocalContext.current))
}