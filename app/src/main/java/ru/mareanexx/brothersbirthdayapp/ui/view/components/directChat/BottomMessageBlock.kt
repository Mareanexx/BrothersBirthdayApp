package ru.mareanexx.brothersbirthdayapp.ui.view.components.directChat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.videoChatMainViolet
import ru.mareanexx.brothersbirthdayapp.ui.theme.writeMessageBackground


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

