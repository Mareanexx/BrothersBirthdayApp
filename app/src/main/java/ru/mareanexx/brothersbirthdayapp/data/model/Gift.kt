package ru.mareanexx.brothersbirthdayapp.data.model

import androidx.annotation.DrawableRes

data class Gift(
    val giftNumber: Int,
    val priceInCoins: Int,
    val level: String,
    @DrawableRes val giftIcon: Int,
    var isReceived: Boolean
)
