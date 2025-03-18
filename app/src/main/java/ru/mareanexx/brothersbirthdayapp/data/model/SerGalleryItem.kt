package ru.mareanexx.brothersbirthdayapp.data.model

interface GalleryItem {
    val id: Int
    val imagePath: String
    val numberOfLikes: Int
    val hashTag: String
    val caption: String
}

data class SerGalleryItem(
    override val id: Int,
    override val imagePath: String,
    override val numberOfLikes: Int,
    override val hashTag: String,
    override val caption: String
) : GalleryItem


data class FavouritePictureItem(
    override val id: Int,
    override val imagePath: String,
    override val numberOfLikes: Int,
    override val hashTag: String,
    override val caption: String
) : GalleryItem