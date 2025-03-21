package ru.mareanexx.brothersbirthdayapp.ui.view.components.museum

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.museumTopBarBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.museumTopBarDigitColor
import ru.mareanexx.brothersbirthdayapp.ui.theme.museumTopBarPrimary
import ru.mareanexx.brothersbirthdayapp.ui.view.components.games.PreviousScreenButton


@Composable
fun TopBarMuseum(
    navController: NavController?,
    numberOfDoor: MutableIntState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = museumTopBarBackground,
                shape = RoundedCornerShape(bottomEnd = 25.dp, bottomStart = 25.dp)
            )
            .padding(top = 50.dp, bottom = 15.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(70.dp)
        ) {
            PreviousScreenButton(museumTopBarPrimary, Color.White, navigateTo = {
                navController?.popBackStack()
            })
            Text(
                modifier = Modifier.padding(start = 35.dp),
                text = stringResource(R.string.museum),
                color = museumTopBarPrimary,
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                lineHeight = 24.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 38.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (i in 0..9) {
                if(i == numberOfDoor.intValue)
                    NumberRowElement(digit = i + 1, Modifier
                        .size(24.dp)
                        .background(color = museumTopBarPrimary, shape = Shapes.extraLarge),
                        textColor = Color.White
                    )
                else
                    NumberRowElement(digit = i + 1, textColor = museumTopBarDigitColor)
            }
        }
    }
}



@Composable
fun NumberRowElement(
    digit: Int,
    modifier: Modifier = Modifier.size(24.dp),
    textColor: Color
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
            color = textColor,
        )
    }
}
