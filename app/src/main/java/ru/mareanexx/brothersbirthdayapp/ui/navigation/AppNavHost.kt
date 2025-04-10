package ru.mareanexx.brothersbirthdayapp.ui.navigation

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
import ru.mareanexx.brothersbirthdayapp.ui.screens.crossword.CrosswordScreen
import ru.mareanexx.brothersbirthdayapp.ui.screens.chat.DirectChatScreen
import ru.mareanexx.brothersbirthdayapp.ui.screens.museum.ExhibitScreen
import ru.mareanexx.brothersbirthdayapp.ui.screens.games.GamesScreen
import ru.mareanexx.brothersbirthdayapp.ui.screens.home.HomeScreen
import ru.mareanexx.brothersbirthdayapp.ui.screens.sergallery.ImageDetailScreen
import ru.mareanexx.brothersbirthdayapp.ui.screens.museum.MuseumScreen
import ru.mareanexx.brothersbirthdayapp.ui.screens.mycoins.MyCoinsScreen
import ru.mareanexx.brothersbirthdayapp.ui.screens.roblox.RobloxQuizScreen
import ru.mareanexx.brothersbirthdayapp.ui.screens.sergallery.SerGalleryScreen
import ru.mareanexx.brothersbirthdayapp.ui.screens.chat.VideoChatScreen
import ru.mareanexx.brothersbirthdayapp.ui.screens.quest.GuessTheCodeScreen
import ru.mareanexx.brothersbirthdayapp.ui.screens.quest.QuestLetterScreen
import ru.mareanexx.brothersbirthdayapp.ui.screens.quest.QuestScreen
import ru.mareanexx.brothersbirthdayapp.ui.screens.quest.SpiesScreen
import ru.mareanexx.brothersbirthdayapp.utils.DataStore


@Composable
fun AppNavHost(navController: NavHostController, dataStore: DataStore) {
    NavHost(
        navController = navController,
        startDestination = "home",
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition = { fadeOut(animationSpec = tween(0)) }
    ) {
        composable("home") { HomeScreen(navController) }

        composable("games") { GamesScreen(navController, dataStore) }

        composable("my_coins") { MyCoinsScreen(navController, dataStore) }

        composable("roblox_quiz") { RobloxQuizScreen(navController, dataStore) }

        composable("sergallery") { SerGalleryScreen(navController, dataStore) }

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

        composable("video_chat") { VideoChatScreen(navController, dataStore) }

        composable("direct_chat/{chatId}",
            arguments = listOf(navArgument("chatId") { type = NavType.IntType} ),
            enterTransition = { slideInHorizontally(tween(300)) }
        ) {
            backStackEntry ->
            val chatId = backStackEntry.arguments?.getInt("chatId") ?: return@composable
            DirectChatScreen(navController, chatId)
        }

        composable("museum") { MuseumScreen(navController, dataStore) }

        composable("exhibit/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType } ),
            enterTransition = { scaleIn() + expandVertically(expandFrom = Alignment.CenterVertically) }
        ) {
            backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: return@composable
            ExhibitScreen(navController, itemId)
        }

        composable("quest") { QuestScreen(navController, dataStore) }

        composable("questLetter") { QuestLetterScreen(navController) }

        composable("spies") { SpiesScreen(navController) }

        composable("guessTheCode") { GuessTheCodeScreen(navController) }

        composable("crossword") { CrosswordScreen(navController, dataStore) }
    }
}