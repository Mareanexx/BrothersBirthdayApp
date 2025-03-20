package ru.mareanexx.brothersbirthdayapp.ui.view.components.videoChat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.directChatTimeText
import ru.mareanexx.brothersbirthdayapp.ui.theme.videoChatMainViolet
import ru.mareanexx.brothersbirthdayapp.ui.theme.videoChatTopBarBackground
import ru.mareanexx.brothersbirthdayapp.ui.view.components.games.PreviousScreenButton
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Composable
fun TopBarVideoChat(
    navController: NavController?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = videoChatTopBarBackground,
                shape = RoundedCornerShape(bottomEnd = 25.dp, bottomStart = 25.dp)
            )
            .padding(top = 15.dp)
            .systemBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PreviousScreenButton(videoChatMainViolet, Color.White, navigateTo = {
                navController?.navigate("games") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    launchSingleTop = true
                }
            })
            Text(
                modifier = Modifier.padding(start = 80.dp),
                text = stringResource(R.string.video_chat),
                color = videoChatMainViolet,
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                lineHeight = 24.sp
            )
        }
    }
}

// Для подсчета в какое время назад было отправлено сообщение
// Возвращает форматированную строку-дату
private fun calculateTimeByTimeAgo(timeAgoInMinutes: Int): String {
    val currentTime = LocalTime.now()
    val messageSentTime = currentTime.minusMinutes(timeAgoInMinutes.toLong())
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return messageSentTime.format(formatter)
}

@Composable
fun TextMessageSendTime(
    timeAgo: Int,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(end = 15.dp),
        text = calculateTimeByTimeAgo(timeAgo),
        fontFamily = MontserratFamily,
        fontSize = 11.sp,
        lineHeight = 11.sp,
        fontWeight = FontWeight.Medium,
        color = directChatTimeText
    )
}