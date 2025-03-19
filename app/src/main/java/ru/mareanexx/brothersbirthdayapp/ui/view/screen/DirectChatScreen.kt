package ru.mareanexx.brothersbirthdayapp.ui.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.model.Dialog
import ru.mareanexx.brothersbirthdayapp.data.model.Message
import ru.mareanexx.brothersbirthdayapp.data.model.TextMessage
import ru.mareanexx.brothersbirthdayapp.data.model.VideoMessage
import ru.mareanexx.brothersbirthdayapp.data.model.findById
import ru.mareanexx.brothersbirthdayapp.data.repo.DialogDB
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.messageBlockBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.messageTextColor
import ru.mareanexx.brothersbirthdayapp.ui.theme.videoChatMainViolet
import ru.mareanexx.brothersbirthdayapp.ui.theme.writeMessageBackground
import ru.mareanexx.brothersbirthdayapp.ui.view.components.directChat.TopBarDirectChat
import ru.mareanexx.brothersbirthdayapp.ui.view.components.videoChat.TextMessageSendTime
import ru.mareanexx.brothersbirthdayapp.ui.view.components.videoChat.VideoBubble

@Composable
fun DirectChatScreen(
    navController: NavController?,
    chatId: Int
) {
    val dialog = DialogDB.findById(chatId) ?: DialogDB.repository[0]

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.dialog_back),
            contentDescription = "null",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopBarDirectChat(
                navController = navController,
                dialogInfo = dialog
            )
            ChatMainContent(dialog, modifier = Modifier.weight(1f))
            BottomMessageBlock(modifier = Modifier)
        }
    }
}


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


@Composable
fun BottomMessageBlock(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(bottom = 25.dp, top = 15.dp, start = 10.dp, end = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = writeMessageBackground, shape = Shapes.medium)
                .padding(horizontal = 12.dp, vertical = 7.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .background(color = videoChatMainViolet, shape = RoundedCornerShape(18.dp))
                        .padding(8.dp),
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Content to Message",
                    tint = Color.White
                )
                Text(
                    text = "Message...",
                    fontFamily = MontserratFamily,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 20.sp,
                    letterSpacing = 0.1.sp,
                    color = videoChatMainViolet
                )
            }
            Image(
                modifier = Modifier.size(30.dp),
                painter = painterResource(R.drawable.mood),
                contentDescription = "Smile Icon"
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDirectChatScreen() {
    DirectChatScreen(null, 1)
}