package ru.mareanexx.brothersbirthdayapp.ui.screens.sergallery

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.data.model.GalleryItem
import ru.mareanexx.brothersbirthdayapp.data.repo.SerGalleryDB
import ru.mareanexx.brothersbirthdayapp.data.repo.findImageByID
import ru.mareanexx.brothersbirthdayapp.ui.screens.sergallery.components.CommentBlock
import ru.mareanexx.brothersbirthdayapp.ui.screens.sergallery.components.GetBackToGalleryButton
import ru.mareanexx.brothersbirthdayapp.ui.screens.sergallery.components.RowWithLikeRepostAndHashtag
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes

@Composable
fun ImageDetailScreen(
    navController: NavController?,
    imageId: Int
) {
    val image: GalleryItem = SerGalleryDB.findImageByID(imageId)

    val context = LocalContext.current
    val imageBitmap = remember {
        BitmapFactory.decodeStream(context.assets.open(image.imagePath)).asImageBitmap()
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp)
            .systemBarsPadding()
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.TopStart
        ) {
            Image(
                bitmap = imageBitmap,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(Shapes.small),
                contentScale = ContentScale.FillWidth
            )
            GetBackToGalleryButton(navController)
        }
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp).fillMaxWidth()
        ) {
            RowWithLikeRepostAndHashtag(image)
            CommentBlock(image)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewImageDetailScreen() {
    ImageDetailScreen(null, 1)
}