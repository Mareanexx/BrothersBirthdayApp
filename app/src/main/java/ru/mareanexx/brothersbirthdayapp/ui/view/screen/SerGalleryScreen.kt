package ru.mareanexx.brothersbirthdayapp.ui.view.screen

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.model.SerGalleryItem
import ru.mareanexx.brothersbirthdayapp.data.repo.GamesDB
import ru.mareanexx.brothersbirthdayapp.data.repo.SerGalleryDB
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.serGalleryMainColor
import ru.mareanexx.brothersbirthdayapp.ui.theme.serGalleryTopMenuBackground
import ru.mareanexx.brothersbirthdayapp.ui.view.components.TopAppBarInGames
import ru.mareanexx.brothersbirthdayapp.ui.view.components.dialogs.SuccessInFinishGameDialog
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

@Composable
fun AllPicturesGrid(
    navController: NavController?
) {
    val allPicturesList = SerGalleryDB.repository

    LazyVerticalStaggeredGrid(
        modifier = Modifier.padding(top = 100.dp),
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalItemSpacing = 10.dp,
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp, bottom = 40.dp)
    ) {
        items(allPicturesList) {
            item: SerGalleryItem -> SerGalleryGridCellItem(item, navController)
        }
    }
}


@Composable
fun SerGalleryGridCellItem(
    galleryItem: SerGalleryItem,
    navController: NavController?
) {
    val context = LocalContext.current
    val imageBitmap = remember {
        BitmapFactory.decodeStream(context.assets.open(galleryItem.imagePath)).asImageBitmap()
    }

    Image(
        bitmap = imageBitmap,
        contentDescription = null,
        modifier = Modifier
            .clip(Shapes.small)
            .clickable {
                navController?.navigate("imageDetail/${galleryItem.id}")
            },
        contentScale = ContentScale.FillWidth
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSerGalleryScreen() {
    SerGalleryScreen(null, DataStore(context = LocalContext.current))
}
