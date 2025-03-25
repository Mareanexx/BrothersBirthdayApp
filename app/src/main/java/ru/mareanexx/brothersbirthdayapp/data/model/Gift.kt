package ru.mareanexx.brothersbirthdayapp.data.model

import androidx.annotation.DrawableRes
import ru.mareanexx.brothersbirthdayapp.utils.GiftTypeSP

data class Gift(
    val giftNumber: Int,
    val priceInCoins: Int,
    val level: String,
    @DrawableRes val giftIcon: Int,
    var isReceived: Boolean,
    val secretCode: String,
    val giftTypeSP: GiftTypeSP
)
