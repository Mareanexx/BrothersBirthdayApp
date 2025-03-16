package ru.mareanexx.brothersbirthdayapp.ui.view

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.GamesScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.HomeScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.MyCoinsScreen


@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home",
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition = { fadeOut(animationSpec = tween(0)) }
    ) {
        composable("home") { HomeScreen(navController) }
        composable("games") { GamesScreen(navController) }
        composable("my_coins") { MyCoinsScreen(navController) }
    }
}