package ru.mareanexx.brothersbirthdayapp.ui.view.screen

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.data.model.Dialog
import ru.mareanexx.brothersbirthdayapp.data.model.TextMessage
import ru.mareanexx.brothersbirthdayapp.data.repo.DialogDB
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.ambientColor
import ru.mareanexx.brothersbirthdayapp.ui.theme.imageDetailCaption
import ru.mareanexx.brothersbirthdayapp.ui.theme.imageDetailUsernameText
import ru.mareanexx.brothersbirthdayapp.ui.theme.numberOfUnreadMessagesBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.spotColor
import ru.mareanexx.brothersbirthdayapp.ui.view.components.videoChat.TopBarVideoChat

@Composable
fun VideoChatScreen(navController: NavController?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBarVideoChat(navController)
        DialogsLazyColumn(navController)
    }
}

@Composable
fun DialogsLazyColumn(
    navController: NavController?
) {
    val dialogsRepo = DialogDB.repository

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 50.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(dialogsRepo) {
            dialog: Dialog -> DialogItem(dialog, navController)
        }
    }
}

@Composable
fun DialogItem(
    dialog: Dialog,
    navController: NavController?
) {
    val context = LocalContext.current
    val imageBitmap = remember {
        BitmapFactory.decodeStream(context.assets.open(dialog.avatarImagePath)).asImageBitmap()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(83.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                navController?.navigate("direct_chat/${dialog.id}")
            }
            .shadow(elevation = 4.dp, shape = Shapes.medium, ambientColor = ambientColor, spotColor = spotColor)
            .background(color = Color.White, shape = Shapes.medium)
            .padding(horizontal = 15.dp, vertical = 11.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Image(
            modifier = Modifier.size(61.dp),
            bitmap = imageBitmap,
            contentDescription = "Avatar Image"
        )

        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = dialog.personName,
                    fontFamily = MontserratFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    letterSpacing = 0.2.sp,
                    color = imageDetailUsernameText
                )
                Text(
                    text = "${dialog.minutesAgoSent} мин",
                    fontFamily = MontserratFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    lineHeight = 13.sp,
                    color = imageDetailCaption
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val lastMessageText = (dialog.messages.last { it is TextMessage } as TextMessage).text

                Text(
                    color = imageDetailCaption,
                    fontFamily = MontserratFamily,
                    maxLines = 1,
                    letterSpacing = 0.1.sp,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.Medium,
                    text = lastMessageText,
                    overflow = TextOverflow.Ellipsis
                )
                Box(
                    modifier = Modifier
                        .background(color = numberOfUnreadMessagesBackground, shape = Shapes.extraLarge)
                        .size(25.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${dialog.numberOfUnreadMessages}",
                        fontFamily = MontserratFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewVideoChatScreen() {
    VideoChatScreen(null)
}