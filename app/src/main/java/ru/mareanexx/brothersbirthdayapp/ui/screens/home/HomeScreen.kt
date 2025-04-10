package ru.mareanexx.brothersbirthdayapp.ui.screens.home

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.ui.components.BottomNavBar
import ru.mareanexx.brothersbirthdayapp.ui.components.WhatToDoDialog
import ru.mareanexx.brothersbirthdayapp.ui.screens.home.components.FAQButton
import ru.mareanexx.brothersbirthdayapp.ui.screens.home.components.MusicButton

@Composable
fun HomeScreen(navController: NavController?) {
    val isFAQdialogOpened = remember { mutableStateOf(false) }
    val isMusicPlaying = remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (isFAQdialogOpened.value) {
        WhatToDoDialog { isFAQdialogOpened.value = false }
    }

    val mediaPlayer by remember { mutableStateOf(MediaPlayer.create(context, R.raw.music).apply {
        isLooping = true
    })}

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.main_page),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                MusicButton(isMusicPlaying) {
                    if (!isMusicPlaying.value) {
                        mediaPlayer.start()
                        isMusicPlaying.value = true
                    } else {
                        mediaPlayer.pause()
                        isMusicPlaying.value = false
                    }
                }
                Spacer(modifier = Modifier.width(15.dp))
                FAQButton {
                    isFAQdialogOpened.value = true
                }
            }
            BottomNavBar(navController, 2)
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(null)
}