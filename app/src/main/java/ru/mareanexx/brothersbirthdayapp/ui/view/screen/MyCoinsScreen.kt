package ru.mareanexx.brothersbirthdayapp.ui.view.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.repo.GiftDB
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.faqButton
import ru.mareanexx.brothersbirthdayapp.ui.theme.hashTag
import ru.mareanexx.brothersbirthdayapp.ui.theme.hashTagTextColor
import ru.mareanexx.brothersbirthdayapp.ui.theme.horizontalDivider
import ru.mareanexx.brothersbirthdayapp.ui.theme.numberOfCoinsText
import ru.mareanexx.brothersbirthdayapp.ui.theme.numberOfGamesBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.progressBarBack
import ru.mareanexx.brothersbirthdayapp.ui.theme.progressBarFill
import ru.mareanexx.brothersbirthdayapp.ui.theme.unlockedButtonBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.yellowCoinsBackground
import ru.mareanexx.brothersbirthdayapp.ui.view.components.BottomNavBar
import ru.mareanexx.brothersbirthdayapp.ui.view.components.myCoins.GiftItem
import ru.mareanexx.brothersbirthdayapp.utils.DataStore
import ru.mareanexx.brothersbirthdayapp.utils.GiftTypeSP

@Composable
fun MyCoinsScreen(navController: NavController?, dataStore: DataStore) {
    val isLeftButtonActive = remember { mutableStateOf(true) }
    val isGift1Received by dataStore.isGift1Received.collectAsState(false)
    val isGift2Received by dataStore.isGift2Received.collectAsState(false)
    val isGift3Received by dataStore.isGift3Received.collectAsState(false)

    fun getState(giftTypeSP: GiftTypeSP): Boolean {
        return when(giftTypeSP) {
            GiftTypeSP.GIFT1 -> isGift1Received
            GiftTypeSP.GIFT2 -> isGift2Received
            GiftTypeSP.GIFT3 -> isGift3Received
        }
    }
    val giftStatuses = listOf(getState(GiftTypeSP.GIFT1), getState(GiftTypeSP.GIFT2), getState(GiftTypeSP.GIFT3))


    Box(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) {
            HeaderMyCoinsWithHeroImage(dataStore)

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 17.dp),
                thickness = 1.dp, color = horizontalDivider
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 17.dp),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.coins_for_rewards),
                fontFamily = MontserratFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold
            )

            SwitcherAllGiftsUnlocked(
                isLeftButtonActive,
                onOpenAllGifts = {
                    isLeftButtonActive.value = true
                }, onOpenUnlocked = {
                    isLeftButtonActive.value = false
                }
            )

            LazyColumn(
                modifier = Modifier.padding(top = 15.dp, bottom = 100.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                val filteredGifts = if (isLeftButtonActive.value) {
                    GiftDB.giftRepository
                } else {
                    GiftDB.giftRepository.filterIndexed { index, _ -> giftStatuses[index] }
                }

                items(filteredGifts) { gift ->
                    GiftItem(gift, dataStore, isReceived = getState(gift.giftTypeSP))
                }
            }
        }
        BottomNavBar(navController, 3, modifier = Modifier.align(Alignment.BottomCenter))
    }
}


@Composable
fun SwitcherAllGiftsUnlocked(
    isLeftButtonActive: MutableState<Boolean>,
    onOpenAllGifts: () -> Unit,
    onOpenUnlocked: () -> Unit
) {
    val switchedColors = object {
        var leftButtonColor : Color = faqButton
        var leftTextColor: Color = Color.White
        var rightButtonColor: Color = unlockedButtonBackground
        var rightTextColor: Color = faqButton
    }
    if (!isLeftButtonActive.value) { switchedColors.apply {
        leftButtonColor = unlockedButtonBackground
        leftTextColor = faqButton
        rightButtonColor = faqButton
        rightTextColor = Color.White
    }}

    Row (
        modifier = Modifier
            .height(42.dp)
            .fillMaxWidth()
            .background(color = unlockedButtonBackground, shape = Shapes.extraLarge),
        horizontalArrangement = Arrangement.Center
    ) {
        Button( // Button for tab All Gifts
            modifier = Modifier.weight(1f),
            onClick = onOpenAllGifts,
            colors = ButtonDefaults.buttonColors(
                containerColor = switchedColors.leftButtonColor
            )
        ) {
            Text(
                text = "All gifts",
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = switchedColors.leftTextColor
            )
        }
        Button( // Button for tab Unlocked Gifts
            modifier = Modifier.weight(1f),
            onClick = onOpenUnlocked,
            colors = ButtonDefaults.buttonColors(
                containerColor = switchedColors.rightButtonColor
            )
        ) {
            Text(
                text = "Unlocked",
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = switchedColors.rightTextColor
            )
        }

    }
}


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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewMyCoinsScreen() {
    MyCoinsScreen(null, DataStore(LocalContext.current))
}