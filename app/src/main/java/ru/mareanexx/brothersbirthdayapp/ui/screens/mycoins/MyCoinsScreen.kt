package ru.mareanexx.brothersbirthdayapp.ui.screens.mycoins

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.repo.GiftDB
import ru.mareanexx.brothersbirthdayapp.ui.components.BottomNavBar
import ru.mareanexx.brothersbirthdayapp.ui.screens.mycoins.components.GiftItem
import ru.mareanexx.brothersbirthdayapp.ui.screens.mycoins.components.HeaderMyCoinsWithHeroImage
import ru.mareanexx.brothersbirthdayapp.ui.screens.mycoins.components.SwitcherAllGiftsUnlocked
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.horizontalDivider
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewMyCoinsScreen() {
    MyCoinsScreen(null, DataStore(LocalContext.current))
}