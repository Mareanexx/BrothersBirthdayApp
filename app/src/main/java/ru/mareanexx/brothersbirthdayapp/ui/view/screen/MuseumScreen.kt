package ru.mareanexx.brothersbirthdayapp.ui.view.screen

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.model.MuseumExhibit
import ru.mareanexx.brothersbirthdayapp.data.repo.GamesDB
import ru.mareanexx.brothersbirthdayapp.data.repo.MuseumExhibitDB
import ru.mareanexx.brothersbirthdayapp.ui.theme.museumMainBackground
import ru.mareanexx.brothersbirthdayapp.ui.view.components.dialogs.SuccessInFinishGameDialog
import ru.mareanexx.brothersbirthdayapp.ui.view.components.museum.TopBarMuseum
import ru.mareanexx.brothersbirthdayapp.utils.DataStore
import ru.mareanexx.brothersbirthdayapp.utils.GameTypeSP

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
                navController?.popBackStack()
            }
        } else {
            navController?.popBackStack()
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

@Composable
fun DoorPager(
    navController: NavController?,
    doorNumber: MutableIntState
) {
    val museumRepo = MuseumExhibitDB.repository

    val pagerState = rememberPagerState(
        0,
        pageCount = { museumRepo.size }
    )

    HorizontalPager(
        contentPadding = PaddingValues(top = 50.dp),
        state = pagerState
    ) {
        pageNumber: Int ->
        val museumExhibit = museumRepo[pageNumber]
        DoorPagerItem(museumExhibit, doorNumber, goToConcreteExhibit = {
            navController?.navigate("exhibit/${museumExhibit.id}")
        })
    }
}

@Composable
fun DoorPagerItem(
    museumExhibit: MuseumExhibit,
    doorNumber: MutableIntState,
    goToConcreteExhibit: () -> Unit
) {
    val context = LocalContext.current
    val doorImageBitmap = remember {
        BitmapFactory.decodeStream(context.assets.open(museumExhibit.doorImagePath)).asImageBitmap()
    }

    doorNumber.intValue = museumExhibit.id - 1

    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Image(
            modifier = Modifier.height(500.dp).align(Alignment.BottomCenter)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    goToConcreteExhibit()
                },
            bitmap = doorImageBitmap,
            contentDescription = "Door Image",
            contentScale = ContentScale.FillHeight
        )
        Icon(
            modifier = Modifier.size(50.dp).align(Alignment.CenterEnd),
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
            contentDescription = "Points where to scroll",
            tint = Color.Black.copy(alpha = 0.2f)
        )
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMuseumScreen() {
    MuseumScreen(null, dataStore = DataStore(LocalContext.current))
}