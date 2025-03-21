package ru.mareanexx.brothersbirthdayapp.ui.view.components.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import ru.mareanexx.brothersbirthdayapp.ui.theme.faqButton
import ru.mareanexx.brothersbirthdayapp.ui.theme.onPauseMusicButton
import ru.mareanexx.brothersbirthdayapp.ui.theme.onPlayMusicButton

@Composable
fun MusicButton(
    isMusicPlaying: MutableState<Boolean>,
    onMusicPlayingSwitch: () -> Unit
) {
    val color = animateColorAsState(
        targetValue = if (isMusicPlaying.value) onPauseMusicButton else onPlayMusicButton,
        label = "Animate Music Color Button"
    )

    Button(
        modifier = Modifier.border(
            width = 4.dp, color = Color.White,
            shape = Shapes.extraLarge
        ).height(62.dp),
        onClick = {
            onMusicPlayingSwitch()
        },
        contentPadding = PaddingValues(start = 30.dp, end = 30.dp, top = 10.dp, bottom = 10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color.value,
            contentColor = Color.White
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
            modifier = Modifier.size(35.dp).padding(start = 10.dp),
            painter = painterResource( if (!isMusicPlaying.value) R.drawable.play_icon else R.drawable.pause_icon),
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
    // MusicButton(onMusicPlayingSwitch = {}, isMusicPlaying = mutableStateOf(false))
}

@Composable
@Preview
fun PreviewQuestionButton() {
    FAQButton(onOpenFAQpanel = {})
}