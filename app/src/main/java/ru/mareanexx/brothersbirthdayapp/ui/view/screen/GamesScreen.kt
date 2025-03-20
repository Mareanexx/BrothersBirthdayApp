package ru.mareanexx.brothersbirthdayapp.ui.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.model.GameCard
import ru.mareanexx.brothersbirthdayapp.data.repo.GamesDB
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.coinCounter
import ru.mareanexx.brothersbirthdayapp.ui.theme.gamesScreenTopPart
import ru.mareanexx.brothersbirthdayapp.ui.theme.giftText
import ru.mareanexx.brothersbirthdayapp.ui.theme.rewardBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.rewardColor
import ru.mareanexx.brothersbirthdayapp.ui.view.components.BottomNavBar

@Composable
fun GamesScreen(navController: NavController?) {
    Scaffold(
        bottomBar = { BottomNavBar(navController, 1) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = gamesScreenTopPart)
                .padding(innerPadding)
                .padding(top = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.games),
                    fontFamily = MontserratFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 32.sp,
                    color = Color.White,
                    lineHeight = 32.sp
                )
                Row(
                    modifier = Modifier
                        .background(color = coinCounter, shape = RoundedCornerShape(10.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        modifier = Modifier.size(27.dp),
                        painter = painterResource(R.drawable.item_coin),
                        contentDescription = null
                    )
                    Text(
                        text = "125", // TODO()
                        color = giftText,
                        fontFamily = MontserratFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        lineHeight = 20.sp
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(top = 120.dp)
                .fillMaxSize()
                .background(color = Color.White, shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
        ) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
                textAlign = TextAlign.Center,
                text = "List",
                fontFamily = MontserratFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(start = 15.dp, end = 15.dp, bottom = 130.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(GamesDB.gameRepository) {
                    item: GameCard -> GameCard(gameCard = item) {
                        navController?.navigate(route = item.routePath)
                    }
                }
            }
        }
    }
}

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
                RewardOfCoinsBlock()
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
fun RewardOfCoinsBlock() {
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
            text = "125",
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


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewGamesScreen() {
    GamesScreen(null)
}