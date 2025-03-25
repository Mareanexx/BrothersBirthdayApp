package ru.mareanexx.brothersbirthdayapp.ui.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import ru.mareanexx.brothersbirthdayapp.data.model.GameCard
import ru.mareanexx.brothersbirthdayapp.data.repo.GamesDB
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.coinCounter
import ru.mareanexx.brothersbirthdayapp.ui.theme.gamesScreenTopPart
import ru.mareanexx.brothersbirthdayapp.ui.theme.giftText
import ru.mareanexx.brothersbirthdayapp.ui.view.components.BottomNavBar
import ru.mareanexx.brothersbirthdayapp.ui.view.components.games.GameCard
import ru.mareanexx.brothersbirthdayapp.utils.DataStore

@Composable
fun GamesScreen(navController: NavController?, dataStore: DataStore) {
    val numberOfCoins by dataStore.numberOfCoins.collectAsState(0)
    
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
                        text = "$numberOfCoins",
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


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewGamesScreen() {
    GamesScreen(null, DataStore(LocalContext.current))
}