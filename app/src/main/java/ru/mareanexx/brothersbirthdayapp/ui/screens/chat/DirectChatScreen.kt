package ru.mareanexx.brothersbirthdayapp.ui.screens.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.model.findById
import ru.mareanexx.brothersbirthdayapp.data.repo.DialogDB
import ru.mareanexx.brothersbirthdayapp.ui.screens.chat.components.BottomMessageBlock
import ru.mareanexx.brothersbirthdayapp.ui.screens.chat.components.ChatMainContent
import ru.mareanexx.brothersbirthdayapp.ui.screens.chat.components.TopBarDirectChat

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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDirectChatScreen() {
    DirectChatScreen(null, 1)
}