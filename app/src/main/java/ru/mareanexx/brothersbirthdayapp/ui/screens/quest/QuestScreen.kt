package ru.mareanexx.brothersbirthdayapp.ui.screens.quest

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.mareanexx.brothersbirthdayapp.data.model.QuestTaskInfo
import ru.mareanexx.brothersbirthdayapp.data.repo.GamesDB
import ru.mareanexx.brothersbirthdayapp.data.repo.QuestTasksDB
import ru.mareanexx.brothersbirthdayapp.ui.components.SuccessInFinishGameDialog
import ru.mareanexx.brothersbirthdayapp.ui.screens.quest.components.StoryAnnotationBlock
import ru.mareanexx.brothersbirthdayapp.ui.theme.questMainBackground
import ru.mareanexx.brothersbirthdayapp.utils.DataStore
import ru.mareanexx.brothersbirthdayapp.utils.GameTypeSP
import ru.mareanexx.brothersbirthdayapp.utils.helperNavBack

@Composable
fun QuestScreen(navController: NavController?, dataStore: DataStore) {
    val questTaskDB = QuestTasksDB
    val tempTask = remember { mutableStateOf(questTaskDB.tempTask) }

    val coroutineScope = rememberCoroutineScope()
    val isGameCompleted by dataStore.isQuestCompleted.collectAsState(false)
    val numberOfCoins by dataStore.numberOfCoins.collectAsState(0)
    val checkCompleteAndShowDialog = remember { mutableIntStateOf(0) }

    val onRightButtonClick: () -> Unit = when(questTaskDB.tempTaskNumber) {
        0, 3 -> {{
            questTaskDB.setNextTaskNumber()
            tempTask.value = questTaskDB.tempTask
        }}
        1, 2, 4 -> {{
            questTaskDB.setNextTaskNumber()
            navController?.navigate(tempTask.value.navigateTo)
        }}
        else -> {{ // для tempTaskNumber = 5
            questTaskDB.setNextTaskNumber()
            checkCompleteAndShowDialog.intValue = 1
        }}
    }

    if (checkCompleteAndShowDialog.intValue == 1) {
        if (!isGameCompleted) {
            val reward = GamesDB.gameRepository[2].reward
            SuccessInFinishGameDialog(
                rewardSize = reward,
                watchedOrCompleted = "прошел",
            ) {
                coroutineScope.launch {
                    dataStore.saveNumberOfCoins(numberOfCoins + reward)
                    dataStore.setGameCompleted(GameTypeSP.QUEST)
                }
                questTaskDB.tempTaskNumber = 0
                navController.helperNavBack()
            }
        } else {
            questTaskDB.tempTaskNumber = 0
            navController.helperNavBack()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = questMainBackground)
    ) {
        QuestTaskBackgroundImage(tempTask)

        StoryAnnotationBlock(tempTask.value, Modifier.align(Alignment.BottomCenter),
            onExitButtonClick = {
                navController?.popBackStack()
            },
            onRightButtonClick = onRightButtonClick
        )
    }
}

@Composable
fun QuestTaskBackgroundImage(
    tempTask: MutableState<QuestTaskInfo>
) {
    val context = LocalContext.current
    val questTaskImageBitmap = BitmapFactory.decodeStream(context.assets.open(tempTask.value.imagePath)).asImageBitmap()

    Image(
        modifier = Modifier.fillMaxWidth(),
        bitmap = questTaskImageBitmap,
        contentDescription = "Quest Task Image",
        contentScale = ContentScale.Crop
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewQuestScreen() {
    QuestScreen(null, dataStore = DataStore(LocalContext.current))
}