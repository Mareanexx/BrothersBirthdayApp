package ru.mareanexx.brothersbirthdayapp.ui.view.components.myCoins

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.model.Gift
import ru.mareanexx.brothersbirthdayapp.data.repo.giftRepository
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.coinBlockBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.faqButton
import ru.mareanexx.brothersbirthdayapp.ui.theme.giftText
import ru.mareanexx.brothersbirthdayapp.ui.theme.greenBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.levelText
import ru.mareanexx.brothersbirthdayapp.ui.theme.unlockedButtonBackground

@Composable
fun GiftItem(
    gift: Gift
) {
    Row(
        modifier = Modifier
            .height(94.dp)
            .fillMaxWidth()
            .background(color = unlockedButtonBackground, shape = Shapes.small)
            .padding(top = 15.dp, start = 10.dp, end = 10.dp)
    ) {
        Image(
            modifier = Modifier
                .size(64.dp),
            painter = painterResource(gift.giftIcon),
            contentDescription = "Gift"
        )
        Column(
            modifier = Modifier.padding(bottom = 15.dp, start = 14.dp).fillMaxHeight().weight(1.2f),
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = "${stringResource(R.string.gift)}${gift.giftNumber}",
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = giftText
            )
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = "Lvl: ${gift.level}",
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = levelText
            )
        }
        Column(
            modifier = Modifier.weight(1f).fillMaxWidth().fillMaxHeight()
                .background(color = greenBackground, shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier,
                text = "GET FOR",
                fontFamily = MontserratFamily,
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 0.3.sp,
                color = faqButton
            )
            NumberOfCoinsBlock("${gift.priceInCoins}")
        }
    }
}

@Composable
fun NumberOfCoinsBlock(
    priceInCoins: String
) {
    Row(
        modifier = Modifier.width(86.dp).background(
            color = coinBlockBackground, shape = RoundedCornerShape(10.dp)
        ).padding(horizontal = 6.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Image(
            modifier = Modifier.size(31.dp),
            painter = painterResource(R.drawable.item_coin),
            contentDescription = stringResource(R.string.coins)
        )
        Column(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = priceInCoins,
                fontFamily = MontserratFamily,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = faqButton,
                lineHeight = 12.sp
            )
            Text(
                text = "coins",
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.Medium,
                color = faqButton,
                fontSize = 12.sp,
                lineHeight = 10.sp
            )
        }
    }
}

@Preview()
@Composable
fun PreviewGiftItem() {
    GiftItem(giftRepository[0])
}