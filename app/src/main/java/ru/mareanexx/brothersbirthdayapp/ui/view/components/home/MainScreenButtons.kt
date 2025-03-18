package ru.mareanexx.brothersbirthdayapp.ui.view.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.disabledMusicButton
import ru.mareanexx.brothersbirthdayapp.ui.theme.enabledMusicButton
import ru.mareanexx.brothersbirthdayapp.ui.theme.faqButton

@Composable
fun MusicButton(
    onStartConfetti: () -> Unit
) {
    Button(
        modifier = Modifier.border(
            width = 4.dp, color = Color.White,
            shape = Shapes.extraLarge
        ).height(62.dp),
        onClick = onStartConfetti,
        contentPadding = PaddingValues(start = 30.dp, end = 18.dp, top = 10.dp, bottom = 10.dp),
        colors = ButtonColors(
            containerColor = disabledMusicButton,
            contentColor = Color.White,
            disabledContainerColor = enabledMusicButton,
            disabledContentColor = Color.White
        )
    ) {
        Text(
            text = stringResource(R.string.music),
            fontFamily = MontserratFamily,
            fontWeight = FontWeight.Black,
            color = Color.White,
            lineHeight = 20.sp,
            fontSize = 32.sp,
            letterSpacing = 1.5.sp
        )
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "Play Button"
        )
    }
}

@Composable
fun FAQButton(
    onOpenFAQpanel: () -> Unit
) {
    IconButton(
        onClick = onOpenFAQpanel,
        modifier = Modifier.background(
            color = faqButton,
            shape = Shapes.extraLarge
        ).border(width = 4.dp, color = Color.White, shape = Shapes.extraLarge)
            .size(62.dp)
    ) {
        Icon(
            modifier = Modifier.size(36.dp),
            painter = painterResource(R.drawable.question_mark),
            contentDescription = "FAQ Button",
            tint = Color.White
        )
    }
}

@Composable
@Preview
fun PreviewMusicButton() {
    MusicButton(onStartConfetti = {  })
}

@Composable
@Preview
fun PreviewQuestionButton() {
    FAQButton(onOpenFAQpanel = {})
}