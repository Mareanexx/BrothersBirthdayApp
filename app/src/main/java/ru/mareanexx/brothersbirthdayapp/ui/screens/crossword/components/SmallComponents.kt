package ru.mareanexx.brothersbirthdayapp.ui.screens.crossword.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes

@Composable
fun SmallTextNumberInCorner(
    number: Int?,
    alignModifier: Modifier
) {
    Text(
        modifier = alignModifier.padding(top = 3.dp, start = 3.dp),
        text = "$number",
        fontSize = 10.sp,
        lineHeight = 10.sp,
        color = Color.Gray,
        letterSpacing = 0.05.sp
    )
}

@Composable
fun AllQuestionsAndCheckButton(
    onCheckAnswers: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .background(color = Color(0xFFf0f0f0), shape = Shapes.small)
            .padding(horizontal = 10.dp, vertical = 7.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Все вопросы",
            fontSize = 20.sp,
            color = Black,
            fontWeight = FontWeight.SemiBold,
            fontFamily = MontserratFamily
        )
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Black, contentColor = White),
            modifier = Modifier.height(30.dp),
            onClick = onCheckAnswers,
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) {
            Text(
                text = "Проверить",
                lineHeight = 16.sp,
                fontSize = 16.sp
            )
        }
    }
}