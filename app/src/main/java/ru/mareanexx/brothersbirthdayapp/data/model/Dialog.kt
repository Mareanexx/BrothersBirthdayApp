package ru.mareanexx.brothersbirthdayapp.data.model

import ru.mareanexx.brothersbirthdayapp.data.repo.DialogDB

interface Message


data class VideoMessage(
    val videoPath: String
) : Message


data class TextMessage(
    val text: String
) : Message


data class Dialog(
    val id: Int,
    val personName: String,
    val avatarImagePath: String,
    val messages: List<Message>,
    val numberOfUnreadMessages: Int,
    val minutesAgoSent: Int
)

fun DialogDB.findById(chatId: Int): Dialog? = this.repository.find { it.id == chatId }