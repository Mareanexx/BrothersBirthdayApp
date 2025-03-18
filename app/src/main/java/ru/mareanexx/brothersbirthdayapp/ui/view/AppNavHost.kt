package ru.mareanexx.brothersbirthdayapp.ui.view

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.GamesScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.HomeScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.ImageDetailScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.MyCoinsScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.RobloxQuizScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.SerGalleryScreen


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
        composable(
            route = "roblox_quiz"
        ) { RobloxQuizScreen(navController) }
        composable("sergallery") { SerGalleryScreen(navController) }

        composable("imageDetail/{favouriteOrAll}/{imageId}",
                arguments = listOf(
                    navArgument("imageId") { type = NavType.IntType },
                    navArgument("favouriteOrAll") { type = NavType.StringType }
                ),
            enterTransition = {
                fadeIn() + scaleIn(initialScale = 0.1f)
            }
        ) {
            navBackStackEntry: NavBackStackEntry ->
            val imageId = navBackStackEntry.arguments?.getInt("imageId") ?: return@composable
            val favouriteOrAll = navBackStackEntry.arguments?.getString("favouriteOrAll") ?: return@composable
            ImageDetailScreen(navController, imageId, favouriteOrAll)
        }
    }
}