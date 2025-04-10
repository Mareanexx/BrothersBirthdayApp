package ru.mareanexx.brothersbirthdayapp.ui.screens.sergallery

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.repo.GamesDB
import ru.mareanexx.brothersbirthdayapp.ui.components.SuccessInFinishGameDialog
import ru.mareanexx.brothersbirthdayapp.ui.components.TopAppBarInGames
import ru.mareanexx.brothersbirthdayapp.ui.screens.sergallery.components.AllPicturesGrid
import ru.mareanexx.brothersbirthdayapp.ui.theme.serGalleryMainColor
import ru.mareanexx.brothersbirthdayapp.ui.theme.serGalleryTopMenuBackground
import ru.mareanexx.brothersbirthdayapp.utils.DataStore
import ru.mareanexx.brothersbirthdayapp.utils.GameTypeSP


@Composable
fun SerGalleryScreen(navController: NavController?, dataStore: DataStore) {
    val coroutineScope = rememberCoroutineScope()
    val isGameCompleted by dataStore.isGalleryCompleted.collectAsState(false)
    val numberOfCoins by dataStore.numberOfCoins.collectAsState(0)
    val checkCompleteAndShowDialog = remember { mutableIntStateOf(0) }

    if (checkCompleteAndShowDialog.intValue == 1) {
        if (!isGameCompleted) {
            val reward = GamesDB.gameRepository[5].reward
            SuccessInFinishGameDialog(
                rewardSize = reward,
                watchedOrCompleted = "посмотрел",
            ) {
                coroutineScope.launch {
                    dataStore.saveNumberOfCoins(numberOfCoins + reward)
                    dataStore.setGameCompleted(GameTypeSP.SERGALLERY)
                }
                navController?.popBackStack()
            }
        } else {
            navController?.popBackStack()
        }
    }

    Box {
        AllPicturesGrid(navController)

        TopAppBarInGames(
            mainColor = serGalleryMainColor,
            backgroundColor = serGalleryTopMenuBackground,
            arrowColor = Color.Black,
            titleRes = R.string.ser_gallery,
            onPreviousButtonClick = {
                checkCompleteAndShowDialog.intValue = 1
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSerGalleryScreen() {
    SerGalleryScreen(null, DataStore(context = LocalContext.current))
}