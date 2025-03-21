package ru.mareanexx.brothersbirthdayapp.ui.view.screen

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.ui.view.components.BottomNavBar
import ru.mareanexx.brothersbirthdayapp.ui.view.components.dialogs.WhatToDoDialog
import ru.mareanexx.brothersbirthdayapp.ui.view.components.home.FAQButton
import ru.mareanexx.brothersbirthdayapp.ui.view.components.home.MusicButton

@Composable
fun HomeScreen(navController: NavController?) {
    val isFAQdialogOpened = remember { mutableStateOf(false) }

    if (isFAQdialogOpened.value) {
        WhatToDoDialog { isFAQdialogOpened.value = false }
    }

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
            Row(  // Music Button and FAQ Button Container
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                MusicButton { TODO("Реализовать включение конфетти") }
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