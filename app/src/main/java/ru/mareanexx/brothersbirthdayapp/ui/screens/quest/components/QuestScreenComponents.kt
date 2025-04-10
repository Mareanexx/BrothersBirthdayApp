package ru.mareanexx.brothersbirthdayapp.ui.screens.quest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mareanexx.brothersbirthdayapp.data.model.QuestTaskInfo
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily


@Composable
fun StoryAnnotationBlock(
    tempTask: QuestTaskInfo,
    alignModifier: Modifier,
    onExitButtonClick: () -> Unit,
    onRightButtonClick: () -> Unit
) {
    Column(
        modifier = alignModifier
            .systemBarsPadding()
            .padding(horizontal = 15.dp)
            .background(color = Color.White.copy(alpha = 0.9f), shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 20.dp, vertical = 15.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = tempTask.title,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = MontserratFamily,
            fontSize = 24.sp,
            lineHeight = 20.sp,
        )
        LazyColumn(
            modifier = Modifier.heightIn(max = 137.dp, min = 80.dp)
        ) {
            item {
                Text(
                    text = tempTask.text,
                    fontFamily = MontserratFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color(0xFF575757),
                    lineHeight = 20.sp
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SmallGrayButton("Exit", onClick = onExitButtonClick)
            SmallGrayButton(tempTask.rightButtonText, onClick = onRightButtonClick)
        }
    }
}

@Composable
fun SmallGrayButton(
    buttonText: String,
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF8D8D8D),
            contentColor = Color.White
        ),
        onClick = onClick
    ) {
        Text(
            text = buttonText,
            fontFamily = MontserratFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}