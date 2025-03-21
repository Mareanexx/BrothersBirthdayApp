package ru.mareanexx.brothersbirthdayapp.data.model

import ru.mareanexx.brothersbirthdayapp.data.repo.MuseumExhibitDB

data class MuseumExhibit(
    val id: Int,
    val doorImagePath: String,
    val pictureImagePath: String,
    val caption: String,
    val year: String,
    val blackCaption: String
)

fun MuseumExhibitDB.findById(itemId: Int) : MuseumExhibit = this.repository[itemId - 1]