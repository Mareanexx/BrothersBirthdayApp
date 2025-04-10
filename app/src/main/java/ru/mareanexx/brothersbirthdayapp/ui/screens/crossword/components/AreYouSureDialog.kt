package ru.mareanexx.brothersbirthdayapp.ui.screens.crossword.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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

@Composable
fun AreYouSureDialog(
    onNoButtonClick: () -> Unit,
    onYesButtonClick: () -> Unit
) {
    Dialog(
        onDismissRequest = onNoButtonClick
    ) {
        Column(
            modifier = Modifier
                .width(350.dp)
                .wrapContentHeight()
                .background(color = Color.White, shape = RoundedCornerShape(size = 10.dp))
                .padding(horizontal = 20.dp, vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                text = "ТЫ УВЕРЕН?",
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                maxLines = 1,
                lineHeight = 40.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
            Text(
                text = "Ты уверен, что хочешь проверить свои ответы? Может мисклик",
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
                color = faqButton
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AreYouSureButtonAnswer("Да") { onYesButtonClick() }
                AreYouSureButtonAnswer("Нет") { onNoButtonClick() }
            }
        }
    }
}

@Composable
fun AreYouSureButtonAnswer(
    textAnswer: String,
    onDismissRequest: () -> Unit
) {
    Button(
        modifier = Modifier.padding(top = 8.dp),
        shape = Shapes.small,
        onClick = onDismissRequest,
        colors = ButtonDefaults.buttonColors(faqButton),
        contentPadding = PaddingValues(horizontal = 25.dp)
    ) {
        Text(
            text = textAnswer,
            fontFamily = MontserratFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = 20.sp,
            color = Color.White
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAreYouSureDialog() {
    AreYouSureDialog(onYesButtonClick = {}, onNoButtonClick = {})
}