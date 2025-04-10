package ru.mareanexx.brothersbirthdayapp.ui.screens.quest.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.mareanexx.brothersbirthdayapp.R

@Composable
fun RebusBlock(
    alignEndModifier: Modifier,
    onClickRollButton: () -> Unit
) {
    Image(
        painter = painterResource(R.drawable.rebus),
        contentDescription = "Rebuses"
    )
    Spacer(modifier = Modifier.height(44.dp))
    RollButton(alignEndModifier, onClickRollButton = onClickRollButton)
}