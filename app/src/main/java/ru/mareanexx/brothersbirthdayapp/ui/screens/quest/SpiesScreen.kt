package ru.mareanexx.brothersbirthdayapp.ui.screens.quest

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.ui.screens.games.components.CorrectAnswerDialog
import ru.mareanexx.brothersbirthdayapp.ui.screens.games.components.IncorrectAnswerDialog
import ru.mareanexx.brothersbirthdayapp.ui.screens.quest.components.BottomAnswerSheet
import ru.mareanexx.brothersbirthdayapp.ui.theme.questMainBackground

@Composable
fun SpiesScreen(navController: NavController?) {
    var chosenVariant by remember { mutableIntStateOf(-1) }
    val openCorrectDialog = remember { mutableIntStateOf(0) }

    when(openCorrectDialog.intValue) {
        1 -> CorrectAnswerDialog {
            openCorrectDialog.intValue = 0
            navController?.popBackStack()
        }
        2 -> IncorrectAnswerDialog {
            openCorrectDialog.intValue = 0
            chosenVariant = -1
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = questMainBackground)
    ) {
        SpiesBackgroundImage("quest/spies.png")
        BottomAnswerSheet(
            openCorrectDialog,
            chosenVariant = chosenVariant,
            Modifier.align(Alignment.BottomCenter), onChooseVariant = {
                newVariant -> chosenVariant = newVariant
            }
        )
    }
}

@Composable
fun SpiesBackgroundImage(imagePath: String) {
    val context = LocalContext.current
    val questTaskImageBitmap = BitmapFactory.decodeStream(context.assets.open(imagePath)).asImageBitmap()

    Image(
        modifier = Modifier.fillMaxWidth(),
        bitmap = questTaskImageBitmap,
        contentDescription = "Spies Background Image",
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSpiesScreen() {
    SpiesScreen(null)
}

