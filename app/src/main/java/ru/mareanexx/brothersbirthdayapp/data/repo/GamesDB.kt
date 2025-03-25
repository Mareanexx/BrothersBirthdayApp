package ru.mareanexx.brothersbirthdayapp.data.repo

import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.model.GameCard
import ru.mareanexx.brothersbirthdayapp.ui.theme.crosswordBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.crosswordButton
import ru.mareanexx.brothersbirthdayapp.ui.theme.crosswordCaption
import ru.mareanexx.brothersbirthdayapp.ui.theme.crosswordTitle
import ru.mareanexx.brothersbirthdayapp.ui.theme.galleryBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.galleryButton
import ru.mareanexx.brothersbirthdayapp.ui.theme.galleryCaption
import ru.mareanexx.brothersbirthdayapp.ui.theme.galleryTitle
import ru.mareanexx.brothersbirthdayapp.ui.theme.greetingBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.greetingButton
import ru.mareanexx.brothersbirthdayapp.ui.theme.greetingCaption
import ru.mareanexx.brothersbirthdayapp.ui.theme.greetingTitle
import ru.mareanexx.brothersbirthdayapp.ui.theme.questBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.questButton
import ru.mareanexx.brothersbirthdayapp.ui.theme.questCaption
import ru.mareanexx.brothersbirthdayapp.ui.theme.questTitle
import ru.mareanexx.brothersbirthdayapp.ui.theme.robloxCaption
import ru.mareanexx.brothersbirthdayapp.ui.theme.robloxCardBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.robloxTitle
import ru.mareanexx.brothersbirthdayapp.ui.theme.videoChatBackground
import ru.mareanexx.brothersbirthdayapp.ui.theme.videoChatButtonPlay
import ru.mareanexx.brothersbirthdayapp.ui.theme.videoChatCaption
import ru.mareanexx.brothersbirthdayapp.ui.theme.videoChatTitle

/**
 * Условная база данных всех карточек игр (GameCard).
 */
object GamesDB {
    val gameRepository = listOf(
        GameCard(
            imageRes = R.drawable.roblox,
            routePath = "roblox_quiz",
            reward = 195,
            name = "Roblox Quiz",
            caption = "Answer some questions about Roblox",
            backgroundColor = robloxCardBackground,
            mainTextColor = robloxTitle,
            captionTextColor = robloxCaption,
        ),
        GameCard(
            imageRes = R.drawable.videochat,
            routePath = "video_chat",
            reward = 63,
            name = "Video Chat",
            caption = "Watch video greetings from your relatives",
            backgroundColor = videoChatBackground,
            mainTextColor = videoChatTitle,
            captionTextColor = videoChatCaption,
            buttonColor = videoChatButtonPlay
        ),

        GameCard(
            imageRes = R.drawable.quest,
            routePath = "quest",
            reward = 301,
            name = "Quest",
            caption = "Complete the quest in English and defeat the villain",
            backgroundColor = questBackground,
            mainTextColor = questTitle,
            captionTextColor = questCaption,
            buttonColor = questButton
        ),

        GameCard(
            imageRes = R.drawable.crossword,
            routePath = "crossword",
            reward = 354,
            name = "Crossword",
            caption = "Solve the crossword with funny questions about our family",
            backgroundColor = crosswordBackground,
            mainTextColor = crosswordTitle,
            captionTextColor = crosswordCaption,
            buttonColor = crosswordButton
        ),

        GameCard(
            imageRes = R.drawable.museum,
            routePath = "museum",
            reward = 49,
            name = "Museum",
            caption = "Choose a door and explore the museum exhibits",
            backgroundColor = greetingBackground,
            mainTextColor = greetingTitle,
            captionTextColor = greetingCaption,
            buttonColor = greetingButton
        ),
        GameCard(
            imageRes = R.drawable.sergallery,
            routePath = "sergallery",
            reward = 38,
            name = "SerGallery",
            caption = "See your photos of all time with stupid captions",
            backgroundColor = galleryBackground,
            mainTextColor = galleryTitle,
            captionTextColor = galleryCaption,
            buttonColor = galleryButton
        )
    )
}
