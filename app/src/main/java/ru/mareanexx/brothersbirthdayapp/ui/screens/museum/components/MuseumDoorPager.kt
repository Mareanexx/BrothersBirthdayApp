package ru.mareanexx.brothersbirthdayapp.ui.screens.museum.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.data.model.MuseumExhibit
import ru.mareanexx.brothersbirthdayapp.data.repo.MuseumExhibitDB


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
    doorNumber.intValue = pagerState.currentPage

    HorizontalPager(
        contentPadding = PaddingValues(top = 50.dp),
        state = pagerState
    ) {
            pageNumber: Int ->
        val museumExhibit = museumRepo[pageNumber]
        DoorPagerItem(museumExhibit, goToConcreteExhibit = {
            navController?.navigate("exhibit/${museumExhibit.id}")
        })
    }
}

@Composable
fun DoorPagerItem(
    museumExhibit: MuseumExhibit,
    goToConcreteExhibit: () -> Unit
) {
    val context = LocalContext.current
    val doorImageBitmap = remember {
        BitmapFactory.decodeStream(context.assets.open(museumExhibit.doorImagePath)).asImageBitmap()
    }

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

