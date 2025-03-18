package ru.mareanexx.brothersbirthdayapp.ui.view.components.imageDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.model.GalleryItem
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.hashTagBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.imageDetailCaption
import ru.mareanexx.brothersbirthdayapp.ui.theme.imageDetailHashtagTextColor
import ru.mareanexx.brothersbirthdayapp.ui.theme.imageDetailIcons
import ru.mareanexx.brothersbirthdayapp.ui.theme.imageDetailUsernameText
import ru.mareanexx.brothersbirthdayapp.ui.theme.likedBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.likedContent
import ru.mareanexx.brothersbirthdayapp.ui.theme.serGalleryMainColor


@Composable
fun GetBackToGalleryButton(
    navController: NavController?
) {
    IconButton(
        modifier = Modifier
            .padding(top = 10.dp, start = 10.dp)
            .background(color = Color.White, shape = Shapes.small),
        onClick = { navController?.popBackStack() }
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = null
        )
    }
}

@Composable
fun RowWithLikeRepostAndHashtag(
    imageItem: GalleryItem
) {
    val isClicked = remember { mutableStateOf(false) }
    val (containerColor, contentColor, numberOfLikes) = when(isClicked.value) {
        false -> Triple(serGalleryMainColor, imageDetailIcons, imageItem.numberOfLikes)
        true -> Triple(likedBackground, likedContent, imageItem.numberOfLikes + 1)
    }

    Row(
        modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = containerColor,
                    contentColor = contentColor
                ),
                contentPadding = PaddingValues(start = 12.dp, end = 15.dp, top = 7.dp, bottom = 7.dp),
                onClick = { isClicked.value = !isClicked.value }
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Like Button",
                )
                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    text = "$numberOfLikes",
                    fontFamily = MontserratFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 16.sp
                )
            }

            Button(
                colors = ButtonDefaults.buttonColors(containerColor = serGalleryMainColor),
                onClick = { TODO("Реализовать отправку изображения") }
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.prompt_suggestion),
                    contentDescription = "Button Share Image",
                    tint = imageDetailIcons
                )
            }
        }
        Text(
            modifier = Modifier
                .background(color = hashTagBackground, shape = Shapes.large)
                .padding(horizontal = 13.dp, vertical = 10.dp),
            text = imageItem.hashTag,
            color = imageDetailHashtagTextColor,
            fontFamily = MontserratFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
    }
}

@Composable
fun CommentBlock(
    imageItem: GalleryItem
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(
            modifier = Modifier.size(28.dp),
            painter = painterResource(R.drawable.mareanexx_avatar),
            contentDescription = "Avatar Round Icon"
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "Mareanexx",
                fontFamily = MontserratFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp,
                color = imageDetailUsernameText
            )
            Text(
                text = imageItem.caption,
                fontFamily = MontserratFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = imageDetailCaption,
                lineHeight = 16.sp,
                letterSpacing = 0.1.sp
            )
        }
    }
}