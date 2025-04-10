package ru.mareanexx.brothersbirthdayapp.ui.screens.mycoins.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.ui.screens.mycoins.CoinsAndGamesProgressBlock
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.hashTag
import ru.mareanexx.brothersbirthdayapp.ui.theme.hashTagTextColor
import ru.mareanexx.brothersbirthdayapp.ui.theme.numberOfCoinsText
import ru.mareanexx.brothersbirthdayapp.ui.theme.numberOfGamesBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.progressBarBack
import ru.mareanexx.brothersbirthdayapp.ui.theme.progressBarFill
import ru.mareanexx.brothersbirthdayapp.ui.theme.yellowCoinsBackground
import ru.mareanexx.brothersbirthdayapp.utils.DataStore

@Composable
fun HeaderMyCoinsWithHeroImage(dataStore: DataStore) {
    val numberOfCoins by dataStore.numberOfCoins.collectAsState(initial = 0)

    Row(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        Image(
            modifier = Modifier.size(height = 184.dp, width = 118.dp),
            painter = painterResource(R.drawable.character_hero),
            contentDescription = "Character Hero",
            contentScale = ContentScale.Fit
        )

        Column {
            Text(
                text = stringResource(R.string.hero_name),
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp
            )
            Text(
                modifier = Modifier
                    .background(color = hashTag, shape = Shapes.extraLarge)
                    .padding(horizontal = 9.dp, vertical = 3.dp),
                text = "#kvadrobandit",
                lineHeight = 12.sp,
                color = hashTagTextColor,
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                CoinsAndGamesProgressBlock(
                    iconResource = R.drawable.item_coin,
                    backgroundColor = yellowCoinsBackground,
                    caption = R.string.coins
                ) {
                    Text(
                        text = "$numberOfCoins",
                        fontFamily = MontserratFamily,
                        fontWeight = FontWeight.ExtraBold,
                        color = numberOfCoinsText,
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        letterSpacing = 1.2.sp,
                    )
                }

                CoinsAndGamesProgressBlock(
                    iconResource = R.drawable.gamepad,
                    backgroundColor = numberOfGamesBackground,
                    caption = R.string.games
                ) {
                    Box(
                        modifier = Modifier
                            .size(width = 81.dp, height = 12.dp)
                            .background(color = progressBarBack, shape = Shapes.extraLarge)
                    ) {
                        Spacer(modifier = Modifier
                            .size(width = 42.dp, height = 12.dp)
                            .background(color = progressBarFill, shape = Shapes.extraLarge)
                        )
                    }
                }
            }
        }
    }
}