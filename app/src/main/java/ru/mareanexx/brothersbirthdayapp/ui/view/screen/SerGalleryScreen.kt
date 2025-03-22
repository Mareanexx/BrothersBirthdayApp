package ru.mareanexx.brothersbirthdayapp.ui.view.screen

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.model.SerGalleryItem
import ru.mareanexx.brothersbirthdayapp.data.repo.SerGalleryDB
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.serGalleryMainColor
import ru.mareanexx.brothersbirthdayapp.ui.theme.serGalleryTopMenuBackground
import ru.mareanexx.brothersbirthdayapp.ui.view.components.games.PreviousScreenButton


@Composable
fun SerGalleryScreen(navController: NavController?) {
    Box {
        AllPicturesGrid(navController)
        TopBarSerGalleryScreen(navController)
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
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp, bottom = 40.dp,)
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


@Composable
fun TopBarSerGalleryScreen(
    navController: NavController?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = serGalleryTopMenuBackground,
                shape = RoundedCornerShape(bottomEnd = 25.dp, bottomStart = 25.dp)
            )
            .padding(top = 15.dp)
            .systemBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PreviousScreenButton(serGalleryMainColor, Color.Black, navigateTo = {
                navController?.navigate("games") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    launchSingleTop = true
                }
            })
            Text(
                modifier = Modifier.padding(start = 90.dp),
                text = stringResource(R.string.ser_gallery),
                color = serGalleryMainColor,
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                lineHeight = 24.sp
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSerGalleryScreen() {
    SerGalleryScreen(null)
}
