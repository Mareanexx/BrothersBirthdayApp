package ru.mareanexx.brothersbirthdayapp.ui.view.components.videoChat

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.directChatTimeText
import java.time.LocalTime
import java.time.format.DateTimeFormatter

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