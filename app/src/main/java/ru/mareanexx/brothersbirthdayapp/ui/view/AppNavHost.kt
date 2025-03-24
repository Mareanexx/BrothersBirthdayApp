package ru.mareanexx.brothersbirthdayapp.ui.view

import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.CrosswordScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.DirectChatScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.ExhibitScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.GamesScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.HomeScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.ImageDetailScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.MuseumScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.MyCoinsScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.RobloxQuizScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.SerGalleryScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.VideoChatScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.quest.GuessTheCodeScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.quest.QuestLetterScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.quest.QuestScreen
import ru.mareanexx.brothersbirthdayapp.ui.view.screen.quest.SpiesScreen


@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "crossword",
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

        composable("imageDetail/{imageId}",
                arguments = listOf(
                    navArgument("imageId") { type = NavType.IntType }
                ),
            enterTransition = {
                fadeIn() + scaleIn(initialScale = 0.1f)
            }
        ) {
            navBackStackEntry: NavBackStackEntry ->
            val imageId = navBackStackEntry.arguments?.getInt("imageId") ?: return@composable
            ImageDetailScreen(navController, imageId)
        }

        composable("video_chat") { VideoChatScreen(navController) }

        composable("direct_chat/{chatId}",
            arguments = listOf(navArgument("chatId") { type = NavType.IntType} ),
            enterTransition = { slideInHorizontally(tween(300)) }
        ) {
            backStackEntry ->
            val chatId = backStackEntry.arguments?.getInt("chatId") ?: return@composable
            DirectChatScreen(navController, chatId)
        }

        composable("museum") { MuseumScreen(navController) }

        composable("exhibit/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType } ),
            enterTransition = { scaleIn() + expandVertically(expandFrom = Alignment.CenterVertically) }
        ) {
            backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: return@composable
            ExhibitScreen(navController, itemId)
        }

        composable("quest") { QuestScreen(navController) }

        composable("questLetter") { QuestLetterScreen(navController) }

        composable("spies") { SpiesScreen(navController) }

        composable("guessTheCode") { GuessTheCodeScreen(navController) }

        composable("crossword") { CrosswordScreen(navController) }
    }
}