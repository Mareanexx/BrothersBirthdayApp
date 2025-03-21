package ru.mareanexx.brothersbirthdayapp.ui.view.components.directChat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mareanexx.brothersbirthdayapp.data.model.Dialog
import ru.mareanexx.brothersbirthdayapp.data.model.Message
import ru.mareanexx.brothersbirthdayapp.data.model.TextMessage
import ru.mareanexx.brothersbirthdayapp.data.model.VideoMessage
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.messageBlockBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.messageTextColor
import ru.mareanexx.brothersbirthdayapp.ui.view.components.videoChat.TextMessageSendTime
import ru.mareanexx.brothersbirthdayapp.ui.view.components.videoChat.VideoBubble

@Composable
fun ChatMainContent(
    dialog: Dialog,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 15.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(dialog.messages) { message: Message ->
            when(message) {
                is TextMessage -> MessageBlock(message.text, dialog.minutesAgoSent)
                is VideoMessage -> VideoBubble(message.videoPath, dialog.minutesAgoSent)
            }
        }
    }
}

@Composable
fun MessageBlock(
    message: String,
    minutesAgoSent: Int
) {
    Column(
        modifier = Modifier
            .widthIn(max = 280.dp, min = 80.dp)
            .wrapContentWidth(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Box(
            modifier = Modifier.background(
                color = messageBlockBackground,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomEnd = 20.dp)
            ).padding(horizontal = 14.dp, vertical = 12.dp)
        ) {
            Text(
                modifier = Modifier,
                text = message,
                fontFamily = MontserratFamily,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Medium,
                color = messageTextColor,
            )
        }
        TextMessageSendTime(minutesAgoSent)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMessageBlock() {
    MessageBlock("Мой любимый сынуля, поздравляю с днем варенья!", 5)
}