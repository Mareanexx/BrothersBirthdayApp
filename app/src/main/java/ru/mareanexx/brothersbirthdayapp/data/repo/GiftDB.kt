package ru.mareanexx.brothersbirthdayapp.data.repo

import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.model.Gift
import ru.mareanexx.brothersbirthdayapp.utils.GiftTypeSP

object GiftDB {
    val giftRepository: List<Gift> = listOf(
        Gift(
            giftNumber = 1,
            priceInCoins = 107,
            level = "Easy",
            giftIcon = R.drawable.gift_1,
            isReceived = false,
            secretCode = "ДИХЛОРМЕТАН",
            giftTypeSP = GiftTypeSP.GIFT1
        ),
        Gift(
            giftNumber = 2,
            priceInCoins = 341,
            level = "Medium",
            giftIcon = R.drawable.gift_2,
            isReceived = false,
            secretCode = "ИДИОКРАТИЯ",
            giftTypeSP = GiftTypeSP.GIFT2
        ),
        Gift(
            giftNumber = 3,
            priceInCoins = 552,
            level = "Hard",
            giftIcon = R.drawable.gift_3,
            isReceived = false,
            secretCode = "КОНТРРЕВОЛЮЦИЯ",
            giftTypeSP = GiftTypeSP.GIFT3
        )
    )
}