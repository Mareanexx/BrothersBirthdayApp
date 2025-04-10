package ru.mareanexx.brothersbirthdayapp.utils

import androidx.navigation.NavController

fun NavController?.helperNavBack() {
    this?.navigate("games") {
        popUpTo(0) { inclusive = true }
        launchSingleTop = true
    }
}