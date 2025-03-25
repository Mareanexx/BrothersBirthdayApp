package ru.mareanexx.brothersbirthdayapp.ui.view.components.games

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.model.GameCard
import ru.mareanexx.brothersbirthdayapp.data.repo.GamesDB
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.rewardBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.rewardColor


@Composable
fun GameCard(
    gameCard: GameCard,
    onPlayClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .height(254.dp)
            .fillMaxWidth()
            .background(
                color = gameCard.backgroundColor, shape = Shapes.medium
            ).padding(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image( // Main Pic of Game
                modifier = Modifier.size(58.dp),
                painter = painterResource(gameCard.imageRes),
                contentDescription = gameCard.name
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) { // Reward
                Text(
                    text = "Reward",
                    fontFamily = MontserratFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    color = rewardColor
                )
                RewardOfCoinsBlock(gameCard)
            }
        }
        Text(
            modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
            text = gameCard.name,
            fontFamily = MontserratFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 18.sp,
            lineHeight = 18.sp,
            letterSpacing = 0.5.sp,
            color = gameCard.mainTextColor
        )
        Text(
            text = gameCard.caption,
            fontFamily = MontserratFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 18.sp,
            maxLines = 4,
            color = gameCard.captionTextColor
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
            onClick = onPlayClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = gameCard.buttonColor
            ),
            contentPadding = PaddingValues(end = 8.dp, start = 12.dp)
        ) {
            Text(
                text = "PLAY",
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                lineHeight = 20.sp
            )
            Icon(
                imageVector = Icons.Default.PlayArrow,
                tint = Color.White,
                contentDescription = "Play the Game Button"
            )
        }
    }
}

@Composable
fun RewardOfCoinsBlock(gameCard: GameCard) {
    Row(
        modifier = Modifier.background(
            color = rewardBackground, shape = RoundedCornerShape(10.dp)
        ).padding(horizontal = 7.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Image(
            modifier = Modifier.size(25.dp),
            painter = painterResource(R.drawable.item_coin),
            contentDescription = "Reward"
        )
        Text(
            text = "${gameCard.reward}",
            fontFamily = MontserratFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = rewardColor
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewGameCard() {
    GameCard(GamesDB.gameRepository[1]) {}
}
