package ru.mareanexx.brothersbirthdayapp.ui.screens.sergallery.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.data.model.SerGalleryItem
import ru.mareanexx.brothersbirthdayapp.data.repo.SerGalleryDB
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes


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