package ru.mareanexx.brothersbirthdayapp.ui.view.screen

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.data.model.findById
import ru.mareanexx.brothersbirthdayapp.data.repo.MuseumExhibitDB
import ru.mareanexx.brothersbirthdayapp.ui.theme.museumMainBackground
import ru.mareanexx.brothersbirthdayapp.ui.view.components.exhibit.BackToMuseumScreenButton
import ru.mareanexx.brothersbirthdayapp.ui.view.components.exhibit.PictureAnnotationBlock

@Composable
fun ExhibitScreen(navController: NavController?, itemId: Int) {
    val museumExhibit = MuseumExhibitDB.findById(itemId)

    val context = LocalContext.current
    val exhibitImageBitmap = remember {
        BitmapFactory.decodeStream(context.assets.open(museumExhibit.pictureImagePath)).asImageBitmap()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = museumMainBackground),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Image(
                modifier = Modifier.fillMaxWidth().padding(end = 15.dp),
                bitmap = exhibitImageBitmap,
                contentDescription = "Exhibit Image",
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(end = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BackToMuseumScreenButton {
                    navController?.popBackStack()
                }
                Spacer(modifier = Modifier.width(20.dp))
                PictureAnnotationBlock(museumExhibit)
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewExhibitScreen() {
    ExhibitScreen(null, 3)
}