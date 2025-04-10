package ru.mareanexx.brothersbirthdayapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.directChatTimeText
import ru.mareanexx.brothersbirthdayapp.ui.theme.faqButton

const val BODY_TEXT_1: String = "Интересный вопрос... Это мой подарок тебе на День Рождения!\nЯ хотела тебя порадовать и придумала вот такой способ.\nВ приложении собрано всё, что тебе нравится. Давай выясним, что же нужно делать!"

const val BODY_TEXT_2: String = "На вкладке “Games” ты сможешь найти 6 развлечений. За каждое пройденное/просмотренное развлечение ты получишь так называемые коины (coins - монетки).\n" +
        "Эти монетки ты сможешь обменять на подарки на вкладке “My Coins”.\n" +
        "\n" +
        "Гарантирую, что по прохождению всех развлечений, ты заработаешь достаточно монеток, чтобы получить все подарки.\n" +
        "\n" +
        "При открытии подарка ты получишь кодовое слово, которое нужно сказать лично МНЕ.\n" +
        "\n" +
        "Вперед и с песней"


@Composable
fun WhatToDoDialog(
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(12.dp)
                .background(color = Color.White, shape = RoundedCornerShape(size = 10.dp))
                .padding(15.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                PreTextImage(R.drawable.question_mark)
                LargeLabelText(labelText = "Что происходит?")
            }
            MediumBodyText(BODY_TEXT_1)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                PreTextImage(R.drawable.rules)
                LargeLabelText(labelText = "Правила")
            }
            MediumBodyText(BODY_TEXT_2)
            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = onDismissRequest,
                colors = ButtonDefaults.buttonColors(containerColor = faqButton)
            ) {
                Text(
                    text = "Я готов",
                    fontFamily = MontserratFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
            }
        }
    }
}

@Composable
fun PreTextImage(
    @DrawableRes imageRes: Int
) {
    Box(
        modifier = Modifier
            .size(45.dp)
            .background(
                color = Color.Black,
                shape = Shapes.extraLarge
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(30.dp),
            painter = painterResource(imageRes),
            contentDescription = "Question Image"
        )
    }
}

@Composable
fun LargeLabelText(
    labelText: String
) {
    Text(
        text = labelText,
        fontFamily = MontserratFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp,
        lineHeight = 20.sp,
    )
}

@Composable
fun MediumBodyText(
    bodyText: String
) {
    Text(
        modifier = Modifier.padding(top = 6.dp, bottom = 12.dp, start = 6.dp, end = 6.dp),
        text = bodyText,
        fontFamily = MontserratFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        color = directChatTimeText,
        lineHeight = 18.sp
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewWhatToDoDialog() {
    WhatToDoDialog {  }
}