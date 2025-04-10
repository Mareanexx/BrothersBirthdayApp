package ru.mareanexx.brothersbirthdayapp.ui.screens.chat.components

import android.content.Context
import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import ru.mareanexx.brothersbirthdayapp.ui.theme.videoChatTopBarBackground


private fun createExoPlayer(context: Context, videoFile: String): ExoPlayer {
    val uri = Uri.parse("asset:///$videoFile")

    val mediaItem = MediaItem.fromUri(uri)
    return ExoPlayer.Builder(context).build().apply {
        setMediaItem(mediaItem)
        prepare()
        playWhenReady = true
        volume = 0f
        repeatMode = ExoPlayer.REPEAT_MODE_ONE
    }
}


@OptIn(UnstableApi::class)
@Composable
fun VideoBubble(
    videoFileName: String,
    timeAgoInMinutes: Int
) {
    val context = LocalContext.current
    val exoPlayer = remember { createExoPlayer(context, videoFileName) }

    var isScaled by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(true) }

    val size by animateDpAsState(targetValue = if (isScaled) 380.dp else 240.dp, label = "")

    Box(
        modifier = Modifier
            .size(size)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) {
                isScaled = !isScaled
                exoPlayer.volume = if (isScaled) 1f else 0f
            }
    ) {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                    useController = false
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .border(2.dp, videoChatTopBarBackground, CircleShape)
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.3f))
                .clickable {
                    isPlaying = !isPlaying
                    exoPlayer.playWhenReady = isPlaying
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Play/Pause",
                tint = Color.White
            )
        }
        TextMessageSendTime(timeAgoInMinutes, modifier = Modifier.align(Alignment.BottomEnd))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewVideoPlayer() {
    VideoBubble(
        "videos/mother.mp4",
        timeAgoInMinutes = 5
    )
}