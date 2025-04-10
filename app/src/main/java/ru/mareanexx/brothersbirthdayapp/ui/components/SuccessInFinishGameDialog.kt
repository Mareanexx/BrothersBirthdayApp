package ru.mareanexx.brothersbirthdayapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.ambientColor
import ru.mareanexx.brothersbirthdayapp.ui.theme.directChatTimeText
import ru.mareanexx.brothersbirthdayapp.ui.theme.rewardColor
import ru.mareanexx.brothersbirthdayapp.ui.screens.mycoins.components.NumberOfCoinsBlock

@Composable
fun SuccessInFinishGameDialog(
    rewardSize: Int,
    watchedOrCompleted: String, // "прошел" или "посмотрел"
    onGetCoinsClick: () -> Unit
) {
    Dialog(
        // properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = onGetCoinsClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Image(
                modifier = Modifier.size(45.dp),
                painter = painterResource(R.drawable.congratulations),
                contentDescription = "Congratulations Icon",
            )
            Text(
                text = "ПОЗДРАВЛЯЮ",
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 26.sp,
                color = rewardColor
            )
            Text(
                text = "Ты успешно ${watchedOrCompleted} развлечение и получаешь награду",
                textAlign = TextAlign.Center,
                fontFamily = MontserratFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = directChatTimeText
            )
            GetCoinsButton(rewardSize, onGetCoinsClick)
        }
    }
}

@Composable
fun GetCoinsButton(
    rewardSize: Int,
    onGetCoinsClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = 10.dp)
            .background(
                color = ambientColor,
                shape = RoundedCornerShape(size = 10.dp)
            )
            .padding(vertical = 6.dp, horizontal = 10.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onGetCoinsClick() },
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "ПОЛУЧИТЬ",
            fontFamily = MontserratFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
            lineHeight = 16.sp,
            color = directChatTimeText,
            letterSpacing = 0.7.sp
        )
        NumberOfCoinsBlock("$rewardSize")
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewSuccessInFinishGameDialog() {
    SuccessInFinishGameDialog(125, "посмотрел") { }
}