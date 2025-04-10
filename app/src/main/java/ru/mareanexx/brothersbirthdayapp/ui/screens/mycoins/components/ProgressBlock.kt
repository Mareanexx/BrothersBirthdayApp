package ru.mareanexx.brothersbirthdayapp.ui.screens.mycoins.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes


@Composable
fun CoinsAndGamesProgressBlock(
    @DrawableRes iconResource: Int,
    backgroundColor: Color,
    @StringRes caption: Int,
    progressItem: @Composable () -> Unit
) {
    val text = stringResource(caption)
    Column(
        modifier = Modifier
            .size(104.dp)
            .background(
                color = backgroundColor,
                shape = Shapes.large
            )
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier.size(width = 63.dp, height = 51.dp),
            painter = painterResource(iconResource),
            contentDescription = text,
        )
        progressItem()
        Text(
            text = text,
            fontFamily = MontserratFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 12.sp
        )
    }
}