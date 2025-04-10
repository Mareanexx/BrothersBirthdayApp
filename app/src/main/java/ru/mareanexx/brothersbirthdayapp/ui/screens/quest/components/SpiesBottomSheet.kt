package ru.mareanexx.brothersbirthdayapp.ui.screens.quest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.greenAnswerText


@Composable
fun BottomAnswerSheet(
    openCorrectDialog: MutableIntState,
    chosenVariant: Int,
    alignBottomModifier: Modifier,
    onChooseVariant: (Int) -> Unit
) {
    Column(
        modifier = alignBottomModifier
            .fillMaxWidth()
            .systemBarsPadding()
            .padding(horizontal = 15.dp)
            .background(color = Color.White.copy(alpha = 0.9f), shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 20.dp, vertical = 15.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(bottom = 7.dp),
            text = "WHO IS LYING?",
            fontFamily = MontserratFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center
        )
        NumberOfGuyAnswer(chosenVariant, 1, "FIRST GUY", onChooseVariant)
        NumberOfGuyAnswer(chosenVariant, 2, "SECOND GUY", onChooseVariant)
        NumberOfGuyAnswer(chosenVariant, 3, "THIRD GUY", onChooseVariant)
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            SmallGrayButton("Check") {
                if (chosenVariant != -1) {
                    if (chosenVariant == 1) {
                        openCorrectDialog.intValue = 1
                    } else openCorrectDialog.intValue = 2
                }
            }
        }
    }
}

@Composable
fun NumberOfGuyAnswer(
    chosenVariant: Int,
    number: Int,
    textGuy: String,
    onChooseVariant: (Int) -> Unit
) {
    Button(
        modifier = Modifier
            .border(
                width = if (chosenVariant == number) 7.dp else 0.dp,
                color = if (chosenVariant == number) greenAnswerText else Color.Transparent,
                shape = Shapes.small
            ),
        onClick = { onChooseVariant(number) },
        contentPadding = PaddingValues(horizontal = 15.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD4D4D4))
    ) {
        Box(modifier = Modifier
            .size(29.dp)
            .background(color = Color.Black, shape = Shapes.extraLarge),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "$number", style = TextStyle(fontSize = 20.sp))
        }
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = textGuy,
            fontFamily = MontserratFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF575757)
        )
    }
}