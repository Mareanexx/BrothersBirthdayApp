package ru.mareanexx.brothersbirthdayapp.ui.screens.museum

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.repo.GamesDB
import ru.mareanexx.brothersbirthdayapp.ui.components.SuccessInFinishGameDialog
import ru.mareanexx.brothersbirthdayapp.ui.screens.museum.components.DoorPager
import ru.mareanexx.brothersbirthdayapp.ui.screens.museum.components.TopBarMuseum
import ru.mareanexx.brothersbirthdayapp.ui.theme.museumMainBackground
import ru.mareanexx.brothersbirthdayapp.utils.DataStore
import ru.mareanexx.brothersbirthdayapp.utils.GameTypeSP
import ru.mareanexx.brothersbirthdayapp.utils.helperNavBack

@Composable
fun MuseumScreen(navController: NavController?, dataStore: DataStore) {
    val doorNumber = remember { mutableIntStateOf(0) }

    val coroutineScope = rememberCoroutineScope()
    val isGameCompleted by dataStore.isMuseumCompleted.collectAsState(false)
    val numberOfCoins by dataStore.numberOfCoins.collectAsState(0)
    val checkCompleteAndShowDialog = remember { mutableIntStateOf(0) }

    if (checkCompleteAndShowDialog.intValue == 1) {
        if (!isGameCompleted) {
            val reward = GamesDB.gameRepository[4].reward
            SuccessInFinishGameDialog(
                rewardSize = reward,
                watchedOrCompleted = "посмотрел",
            ) {
                coroutineScope.launch {
                    dataStore.saveNumberOfCoins(numberOfCoins + reward)
                    dataStore.setGameCompleted(GameTypeSP.MUSEUM)
                }
                navController.helperNavBack()
            }
        } else {
            navController.helperNavBack()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(color = museumMainBackground)
    ) {
        TopBarMuseum(doorNumber, onPreviousButtonClick = {
            checkCompleteAndShowDialog.intValue = 1
        })
        Image(
            modifier = Modifier.width(220.dp).align(Alignment.CenterHorizontally),
            painter = painterResource(R.drawable.table_open_the_door),
            contentDescription = "Table open the door",
            contentScale = ContentScale.Crop
        )
        DoorPager(navController, doorNumber)
        Image(
            modifier = Modifier.weight(1f),
            painter = painterResource(R.drawable.wooden_floor),
            contentDescription = "Wooden Floor",
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMuseumScreen() {
    MuseumScreen(null, dataStore = DataStore(LocalContext.current))
}