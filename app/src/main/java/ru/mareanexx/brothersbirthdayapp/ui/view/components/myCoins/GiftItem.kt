package ru.mareanexx.brothersbirthdayapp.ui.view.components.myCoins

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.model.Gift
import ru.mareanexx.brothersbirthdayapp.data.repo.GiftDB.giftRepository
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.coinBlockBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.directChatTimeText
import ru.mareanexx.brothersbirthdayapp.ui.theme.faqButton
import ru.mareanexx.brothersbirthdayapp.ui.theme.giftBottomPanelBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.giftText
import ru.mareanexx.brothersbirthdayapp.ui.theme.greenBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.levelText
import ru.mareanexx.brothersbirthdayapp.ui.theme.secretCodeBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.unlockedButtonBackground

@Composable
fun GiftItem(
    gift: Gift
) {
    val isReceived = remember { mutableStateOf(gift.isReceived) }
    val isExpanded = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
    ) {
        Row(
            modifier = Modifier
                .height(94.dp)
                .fillMaxWidth()
                .background(color = unlockedButtonBackground, shape = Shapes.small)
                .padding(top = 15.dp, start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Image(
                    modifier = Modifier
                        .size(64.dp),
                    painter = painterResource(gift.giftIcon),
                    contentDescription = "Gift"
                )
                Column(
                    modifier = Modifier
                        .padding(bottom = 15.dp, start = 14.dp)
                        .fillMaxHeight(),
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
            }

            if (isReceived.value) { // Если подарок куплен, то будет блок с открытием секретного кода
                OpenBlock(Modifier.align(Alignment.Bottom), onOpenBottomPanel = {
                    isExpanded.value = !isExpanded.value
                })
            } else { // Если подарок не куплен, то будет блок с получением подарка
                GetForBlock(gift, Modifier.align(Alignment.Bottom), onBuyGift = {
                    isReceived.value = true
                })
            }
        }

        if (isExpanded.value) {
            BottomAnimatedPanelWithCode(gift.secretCode)
        }
    }
}

@Composable
fun BottomAnimatedPanelWithCode(
    secretCode: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 11.dp)
            .background(
                color = giftBottomPanelBackground,
                shape = RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp)
            )
            .padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Кодовое\nслово:",
            fontFamily = MontserratFamily,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 13.sp,
            color = levelText,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .background(color = secretCodeBackground, shape = RoundedCornerShape(2.dp))
                .padding(vertical = 5.dp, horizontal = 10.dp),
            text = secretCode,
            fontFamily = MontserratFamily,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 18.sp,
            color = directChatTimeText
        )
    }
}

@Composable
fun GetForBlock(
    gift: Gift,
    modifierWithAlignment: Modifier,
    onBuyGift: () -> Unit
) {
    Column(
        modifier = modifierWithAlignment
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onBuyGift()
            }
            .wrapContentSize()
            .background(color = greenBackground, shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .padding(top = 10.dp, start = 18.dp, end = 18.dp, bottom = 5.dp),
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

@Composable
fun OpenBlock(
    modifierWithAlignment: Modifier,
    onOpenBottomPanel: () -> Unit
) {
    Row(
        modifier = modifierWithAlignment
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onOpenBottomPanel()
            }
            .wrapContentSize()
            .background(color = greenBackground, shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .padding(top = 8.dp, start = 18.dp, end = 11.dp, bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            modifier = Modifier,
            text = "OPEN",
            fontFamily = MontserratFamily,
            fontSize = 16.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 0.3.sp,
            color = faqButton
        )
        Icon(
            modifier = Modifier.size(37.dp),
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            tint = faqButton
        )
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