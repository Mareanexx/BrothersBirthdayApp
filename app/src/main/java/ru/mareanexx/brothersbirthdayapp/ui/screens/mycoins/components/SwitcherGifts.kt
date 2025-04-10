package ru.mareanexx.brothersbirthdayapp.ui.screens.mycoins.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.faqButton
import ru.mareanexx.brothersbirthdayapp.ui.theme.unlockedButtonBackground

@Composable
fun SwitcherAllGiftsUnlocked(
    isLeftButtonActive: MutableState<Boolean>,
    onOpenAllGifts: () -> Unit,
    onOpenUnlocked: () -> Unit
) {
    val switchedColors = object {
        var leftButtonColor : Color = faqButton
        var leftTextColor: Color = Color.White
        var rightButtonColor: Color = unlockedButtonBackground
        var rightTextColor: Color = faqButton
    }
    if (!isLeftButtonActive.value) { switchedColors.apply {
        leftButtonColor = unlockedButtonBackground
        leftTextColor = faqButton
        rightButtonColor = faqButton
        rightTextColor = Color.White
    }}

    Row (
        modifier = Modifier
            .height(42.dp)
            .fillMaxWidth()
            .background(color = unlockedButtonBackground, shape = Shapes.extraLarge),
        horizontalArrangement = Arrangement.Center
    ) {
        Button( // Button for tab All Gifts
            modifier = Modifier.weight(1f),
            onClick = onOpenAllGifts,
            colors = ButtonDefaults.buttonColors(
                containerColor = switchedColors.leftButtonColor
            )
        ) {
            Text(
                text = "All gifts",
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = switchedColors.leftTextColor
            )
        }
        Button( // Button for tab Unlocked Gifts
            modifier = Modifier.weight(1f),
            onClick = onOpenUnlocked,
            colors = ButtonDefaults.buttonColors(
                containerColor = switchedColors.rightButtonColor
            )
        ) {
            Text(
                text = "Unlocked",
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = switchedColors.rightTextColor
            )
        }

    }
}