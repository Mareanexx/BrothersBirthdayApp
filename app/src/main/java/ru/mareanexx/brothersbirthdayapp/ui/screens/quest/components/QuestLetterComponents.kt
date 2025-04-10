package ru.mareanexx.brothersbirthdayapp.ui.screens.quest.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily


@Composable
fun SimpleTextField(
    inputText: TextFieldState,
) {
    BasicTextField(
        modifier = Modifier
            .width(70.dp)
            .padding(horizontal = 5.dp)
            .background(color = Color(0xFFD9D9D9), shape = RoundedCornerShape(5.dp))
            .padding(5.dp),
        state = inputText,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
        textStyle = TextStyle(color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    )
}

@Composable
fun QuestLetterImageBackground() {
    val context = LocalContext.current
    val questTaskImageBitmap = BitmapFactory.decodeStream(context.assets.open("quest/letter_background.png")).asImageBitmap()

    Image(
        modifier = Modifier.fillMaxWidth(),
        bitmap = questTaskImageBitmap,
        contentDescription = "Quest Letter background Image",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun CheckLetterButton(
    alignModifier: Modifier,
    onClickCheckLetter: () -> Unit
) {
    Button(
        modifier = alignModifier
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .systemBarsPadding()
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFC2C2C2)
        ),
        onClick = onClickCheckLetter
    ) {
        Text(
            text = "ПРОВЕРИТЬ",
            fontFamily = MontserratFamily,
            fontSize = 22.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

