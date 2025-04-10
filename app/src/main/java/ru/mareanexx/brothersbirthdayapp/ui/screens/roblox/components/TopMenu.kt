package ru.mareanexx.brothersbirthdayapp.ui.screens.roblox.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.ui.screens.games.components.OneHeartItem
import ru.mareanexx.brothersbirthdayapp.ui.screens.games.components.PreviousScreenButton
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.aroundDigitColor
import ru.mareanexx.brothersbirthdayapp.ui.theme.heart
import ru.mareanexx.brothersbirthdayapp.ui.theme.robloxQuizMainText
import ru.mareanexx.brothersbirthdayapp.ui.theme.robloxTopMenuBackground


@Composable
fun TopMenuRobloxQuizScreen(
    navController: NavController?,
    numberOfQuestion: Int,
    numberOfMistakes: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = robloxTopMenuBackground,
                shape = RoundedCornerShape(bottomEnd = 25.dp, bottomStart = 25.dp)
            )
            .systemBarsPadding()
            .padding(top = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PreviousScreenButton(robloxQuizMainText, Color.White, navigateTo = {
                navController?.navigate("games") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    launchSingleTop = true
                }
            })
            Text(
                modifier = Modifier.padding(start = 40.dp),
                text = stringResource(R.string.roblox_quiz),
                color = robloxQuizMainText,
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                lineHeight = 24.sp
            )
            Row {
                OneHeartItem( if (numberOfMistakes >= 1) Color.White else heart )
                OneHeartItem( if (numberOfMistakes >= 2) Color.White else heart )
                OneHeartItem( if (numberOfMistakes >= 3) Color.White else heart )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 38.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (i in 1..10) {
                if(i - 1 == numberOfQuestion)
                    NumberRowElement(i, Modifier
                        .size(24.dp)
                        .background(color = aroundDigitColor, shape = Shapes.extraLarge)
                    )
                else
                    NumberRowElement(i)
            }
        }
    }
}

@Composable
fun NumberRowElement(
    digit: Int,
    modifier: Modifier = Modifier.size(24.dp)
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$digit",
            fontFamily = MontserratFamily,
            fontSize = 15.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
        )
    }
}