package ru.mareanexx.brothersbirthdayapp.ui.screens.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.repo.GamesDB
import ru.mareanexx.brothersbirthdayapp.ui.components.SuccessInFinishGameDialog
import ru.mareanexx.brothersbirthdayapp.ui.components.TopAppBarInGames
import ru.mareanexx.brothersbirthdayapp.ui.screens.chat.components.DialogsLazyColumn
import ru.mareanexx.brothersbirthdayapp.ui.theme.videoChatMainViolet
import ru.mareanexx.brothersbirthdayapp.ui.theme.videoChatTopBarBackground
import ru.mareanexx.brothersbirthdayapp.utils.DataStore
import ru.mareanexx.brothersbirthdayapp.utils.GameTypeSP

@Composable
fun VideoChatScreen(navController: NavController?, dataStore: DataStore) {
    val coroutineScope = rememberCoroutineScope()
    val isGameCompleted by dataStore.isChatCompleted.collectAsState(false)
    val numberOfCoins by dataStore.numberOfCoins.collectAsState(0)
    val checkCompleteAndShowDialog = remember { mutableIntStateOf(0) }

    if (checkCompleteAndShowDialog.intValue == 1) {
        if (!isGameCompleted) {
            val reward = GamesDB.gameRepository[1].reward
            SuccessInFinishGameDialog(
                rewardSize = reward,
                watchedOrCompleted = "посмотрел",
            ) {
                coroutineScope.launch {
                    dataStore.saveNumberOfCoins(numberOfCoins + reward)
                    dataStore.setGameCompleted(GameTypeSP.CHAT)
                }
                navController?.popBackStack()
            }
        } else {
            navController?.popBackStack()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBarInGames(
            mainColor = videoChatMainViolet,
            backgroundColor = videoChatTopBarBackground,
            titleRes = R.string.video_chat,
            onPreviousButtonClick = { checkCompleteAndShowDialog.intValue = 1 }
        )
        DialogsLazyColumn(navController)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewVideoChatScreen() {
    VideoChatScreen(null, DataStore(LocalContext.current))
}