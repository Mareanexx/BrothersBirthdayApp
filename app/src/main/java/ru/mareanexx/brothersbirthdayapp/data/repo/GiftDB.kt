package ru.mareanexx.brothersbirthdayapp.data.repo

import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.data.model.Gift

object GiftDB {
    val giftRepository: List<Gift> = listOf(
        Gift(
            giftNumber = 1,
            priceInCoins = 69,
            level = "Easy",
            giftIcon = R.drawable.gift_1,
            isReceived = false,
            secretCode = "ДИХЛОРМЕТАН"
        ),
        Gift(
            giftNumber = 2,
            priceInCoins = 150,
            level = "Medium",
            giftIcon = R.drawable.gift_2,
            isReceived = false,
            secretCode = "ИДИОКРАТИЯ"
        ),
        Gift(
            giftNumber = 3,
            priceInCoins = 367,
            level = "Hard",
            giftIcon = R.drawable.gift_3,
            isReceived = false,
            secretCode = "КОНТРРЕВОЛЮЦИЯ"
        )
    )
}