package ru.mareanexx.brothersbirthdayapp.ui.screens.roblox.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.model.RobloxImage
import ru.mareanexx.brothersbirthdayapp.data.model.RobloxQuizQuestion
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily

@Composable
fun QuestionCard(
    robloxQuizImage: RobloxImage,
    robloxQuizQuestion: RobloxQuizQuestion,
    chosenVariant: MutableIntState,
    onChooseVariant: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(robloxQuizImage.imageRes),
            contentDescription = "Roblox Funny Picture",
            contentScale = ContentScale.Crop
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                modifier = Modifier.size(48.dp),
                painter = painterResource(R.drawable.question_icon),
                contentDescription = "Question Icon"
            )
            Text(
                text = robloxQuizQuestion.question,
                fontSize = 20.sp,
                fontFamily = MontserratFamily,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp
            )
        }
        GridListOfAnswers(robloxQuizQuestion, chosenVariant, onChooseVariant)
    }
}