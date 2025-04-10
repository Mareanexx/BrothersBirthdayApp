package ru.mareanexx.brothersbirthdayapp.ui.screens.museum.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mareanexx.brothersbirthdayapp.data.model.MuseumExhibit
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.directChatTimeText
import ru.mareanexx.brothersbirthdayapp.ui.theme.exhibitAnnotaionCircle
import ru.mareanexx.brothersbirthdayapp.ui.theme.exhibitAnnotationBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.exhibitYearColor


@Composable
fun PictureAnnotationBlock(
    museumExhibit: MuseumExhibit
) {
    Box(
        modifier = Modifier
            .shadow(elevation = 10.dp)
            .background(color = exhibitAnnotationBackground, shape = RoundedCornerShape(2.dp))
            .padding(5.dp)
    ) {
        val alignModifiers = listOf(
            Modifier.align(Alignment.TopStart),
            Modifier.align(Alignment.TopEnd), Modifier.align(Alignment.BottomEnd),
            Modifier.align(Alignment.BottomStart)
        )

        for (i in 0..3) {
            SimpleGrayCircle(modifier = alignModifiers[i])
        }
        Box(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 6.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = museumExhibit.caption,
                    fontFamily = MontserratFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 17.sp,
                    color = directChatTimeText
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = museumExhibit.blackCaption,
                        fontWeight = FontWeight.Bold,
                        fontFamily = MontserratFamily,
                        lineHeight = 12.sp,
                        fontSize = 12.sp
                    )
                    Text(
                        text = museumExhibit.year,
                        fontSize = 12.sp,
                        fontFamily = MontserratFamily,
                        fontWeight = FontWeight.Medium,
                        color = exhibitYearColor
                    )
                }
            }
        }
    }
}

@Composable
fun SimpleGrayCircle(
    modifier: Modifier
) {
    Box(modifier = modifier
        .size(10.dp)
        .background(color = exhibitAnnotaionCircle, shape = CircleShape)
    )
}


@Composable
fun BackToMuseumScreenButton(
    navigateTo: () -> Unit
) {
    Icon(
        modifier = Modifier
            .size(95.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                navigateTo()
            },
        imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
        contentDescription = "Back to Museum Doors Screen",
        tint = Color.Black.copy(alpha = 0.10f)
    )
}
