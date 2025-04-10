package ru.mareanexx.brothersbirthdayapp.ui.screens.chat.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.data.model.Dialog
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.videoChatMainViolet
import ru.mareanexx.brothersbirthdayapp.ui.theme.videoChatTopBarBackground
import ru.mareanexx.brothersbirthdayapp.ui.screens.games.components.PreviousScreenButton


@Composable
fun TopBarDirectChat(
    navController: NavController?,
    dialogInfo: Dialog
) {
    val context = LocalContext.current
    val imageBitmap = remember {
        BitmapFactory.decodeStream(context.assets.open(dialogInfo.avatarImagePath)).asImageBitmap()
    }

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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PreviousScreenButton(videoChatMainViolet, Color.White, navigateTo = {
                navController?.popBackStack()
            })
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = dialogInfo.personName,
                    color = videoChatMainViolet,
                    fontFamily = MontserratFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    lineHeight = 24.sp
                )
                Text(
                    text = "${dialogInfo.minutesAgoSent} мин",
                    fontFamily = MontserratFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    lineHeight = 13.sp
                )
            }
            Image(
                modifier = Modifier.size(46.dp),
                bitmap = imageBitmap,
                contentDescription = "Avatar Image"
            )
        }
    }
}
