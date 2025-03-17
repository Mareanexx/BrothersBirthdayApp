package ru.mareanexx.brothersbirthdayapp.ui.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.serGalleryTopMenuBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.serGalleryTopMenuText
import ru.mareanexx.brothersbirthdayapp.ui.view.components.PreviousScreenButton

@Composable
fun SerGalleryScreen(navController: NavController?) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBarSerGalleryScreen(navController)
        MariannaFavouritePicsCarousel()
    }
}

@Composable
fun MariannaFavouritePicsCarousel() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 30.dp, bottom = 16.dp),
            text = "Marianna's Favourites",
            fontFamily = MontserratFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            lineHeight = 16.sp
        )
        LazyRow {

        }
    }
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
            .systemBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PreviousScreenButton(navController, serGalleryTopMenuText, Color.Black)
            Text(
                modifier = Modifier.padding(start = 90.dp),
                text = stringResource(R.string.ser_gallery),
                color = serGalleryTopMenuText,
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
