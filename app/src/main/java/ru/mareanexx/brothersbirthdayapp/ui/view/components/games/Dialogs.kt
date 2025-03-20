package ru.mareanexx.brothersbirthdayapp.ui.view.components.games

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.faqButton
import ru.mareanexx.brothersbirthdayapp.ui.theme.greenAnswerText
import ru.mareanexx.brothersbirthdayapp.ui.theme.heart
import ru.mareanexx.brothersbirthdayapp.ui.theme.redAnswerText

@Composable
fun CorrectAnswerDialog(
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .width(width = 255.dp)
                .wrapContentHeight()
                .background(color = Color.White, shape = Shapes.large)
                .padding(horizontal = 40.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "ВЕРНО!",
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                lineHeight = 40.sp,
                color = greenAnswerText
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = Shapes.small,
                onClick = onDismissRequest,
                colors = ButtonDefaults.buttonColors(faqButton)
            ) {
                Text(
                    text = "Далее",
                    fontFamily = MontserratFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun IncorrectAnswerDialog(
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .background(color = Color.White, shape = Shapes.large)
                .padding(horizontal = 30.dp, vertical = 15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "НЕВЕРНО!",
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                lineHeight = 40.sp,
                maxLines = 1,
                textAlign = TextAlign.Center,
                color = redAnswerText
            )
            Row(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(end = 7.dp),
                    text = "-1",
                    fontFamily = MontserratFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    lineHeight = 32.sp,
                    color = Color.Black
                )
                Icon(
                    Icons.Default.Favorite,
                    modifier = Modifier.size(36.dp),
                    contentDescription = "Heart Icon",
                    tint = heart,
                )
            }
            Button(
                modifier = Modifier.padding(top = 8.dp),
                shape = Shapes.small,
                onClick = onDismissRequest,
                colors = ButtonDefaults.buttonColors(faqButton)
            ) {
                Text(
                    text = "Еще раз!",
                    fontFamily = MontserratFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun EndOfGameDialog(
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .width(350.dp)
                .wrapContentHeight()
                .background(color = Color.White, shape = Shapes.large)
                .padding(horizontal = 20.dp, vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                text = "GAME OVER",
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                maxLines = 1,
                lineHeight = 40.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
            Text(
                text = "Увы! Ты потратил все жизни... \nПопробуй еще раз позже",
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
                color = faqButton
            )
            Button(
                modifier = Modifier.padding(top = 8.dp),
                shape = Shapes.small,
                onClick = onDismissRequest,
                colors = ButtonDefaults.buttonColors(faqButton),
                contentPadding = PaddingValues(horizontal = 25.dp)
            ) {
                Text(
                    text = "Гудбай",
                    fontFamily = MontserratFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEndOfGameDialog() {
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .wrapContentHeight()
            .background(color = Color.White, shape = Shapes.large)
            .padding(horizontal = 20.dp, vertical = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "GAME OVER",
            fontFamily = MontserratFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            lineHeight = 40.sp,
            color = Color.Black
        )
        Text(
            text = "Увы! Ты потратил все жизни... \nПопробуй еще раз позже",
            fontFamily = MontserratFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            color = faqButton
        )
        Button(
            modifier = Modifier.padding(top = 8.dp),
            shape = Shapes.small,
            onClick = { },
            colors = ButtonDefaults.buttonColors(faqButton),
            contentPadding = PaddingValues(horizontal = 25.dp)
        ) {
            Text(
                text = "Гудбай",
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                lineHeight = 20.sp,
                color = Color.White
            )
        }
    }
}
