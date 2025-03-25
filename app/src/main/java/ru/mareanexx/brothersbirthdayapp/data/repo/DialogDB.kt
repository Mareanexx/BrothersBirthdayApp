package ru.mareanexx.brothersbirthdayapp.data.repo

import ru.mareanexx.brothersbirthdayapp.data.model.Dialog
import ru.mareanexx.brothersbirthdayapp.data.model.TextMessage
import ru.mareanexx.brothersbirthdayapp.data.model.VideoMessage

// TODO() пофиксить путь к видео, тексты сообщений, а также количество сообщений

object DialogDB {
    val repository = listOf(
        Dialog(
            id = 1,
            personName = "Сеструха",
            avatarImagePath = "avatars/marianna.png",
            messages = listOf(
                TextMessage("С др, БОРАТ!"),
                VideoMessage("videos/mother.mp4"),
                TextMessage("Мой любимый сынуля, поздравляю с днем варенья!")),
            numberOfUnreadMessages = 2,
            minutesAgoSent = 5
        ),
        Dialog(
            id = 2,
            personName = "Матушка",
            avatarImagePath = "avatars/mother.png",
            messages = listOf(
                VideoMessage("videos/mother.mp4"),
                TextMessage("TODO()")),
            numberOfUnreadMessages = 2,
            minutesAgoSent = 7
        ),
        Dialog(
            id = 3,
            personName = "Бабуся Маринуся",
            avatarImagePath = "avatars/granny2.png",
            messages = listOf(
                VideoMessage("videos/granny.mp4"),
                TextMessage("TODO()")),
            numberOfUnreadMessages = 2,
            minutesAgoSent = 8
        ),
        Dialog(
            id = 4,
            personName = "Деда Андрей",
            avatarImagePath = "avatars/granddad.png",
            messages = listOf(
                VideoMessage("videos/grandad.mp4"),
                TextMessage("TODO()")),
            numberOfUnreadMessages = 2,
            minutesAgoSent = 15
        ),
        Dialog(
            id = 5,
            personName = "ПуТАНЬЧИК",
            avatarImagePath = "avatars/tanya.png",
            messages = listOf(
                VideoMessage("videos/tatyana.mp4"),
                TextMessage("Андрей не даст тебе виар пока")),
            numberOfUnreadMessages = 2,
            minutesAgoSent = 16
        ),
        Dialog(
            id = 6,
            personName = "Сударь всея Руси",
            avatarImagePath = "avatars/andrey.png",
            messages = listOf(
                VideoMessage("videos/andrey.mp4"),
                TextMessage("Я дам тебе виар..")),
            numberOfUnreadMessages = 2,
            minutesAgoSent = 17
        ),
        Dialog(
            id = 7,
            personName = "Батя",
            avatarImagePath = "avatars/father.png",
            messages = listOf(
                VideoMessage("videos/father.mp4"),
                TextMessage("Это мой Сын! Это мой мальчик!")),
            numberOfUnreadMessages = 2,
            minutesAgoSent = 18
        ),
        Dialog(
            id = 8,
            personName = "Катенок",
            avatarImagePath = "avatars/kate.png",
            messages = listOf(
                VideoMessage("videos/kate.mp4"),
                TextMessage("Это мой Сын! Это мой мальчик!")),
            numberOfUnreadMessages = 2,
            minutesAgoSent = 21
        ),
        Dialog(
            id = 9,
            personName = "Игорюша<3",
            avatarImagePath = "avatars/igor.png",
            messages = listOf(
                VideoMessage("videos/igor.mp4"),
                TextMessage("Дядя игорь теперь в SeregaGram")),
            numberOfUnreadMessages = 2,
            minutesAgoSent = 22
        ),
    )
}