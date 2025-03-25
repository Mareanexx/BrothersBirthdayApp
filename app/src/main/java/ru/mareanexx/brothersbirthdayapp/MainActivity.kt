package ru.mareanexx.brothersbirthdayapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import ru.mareanexx.brothersbirthdayapp.ui.theme.BrothersBirthdayAppTheme
import ru.mareanexx.brothersbirthdayapp.ui.view.AppNavHost
import ru.mareanexx.brothersbirthdayapp.utils.DataStore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val dataStore = DataStore(applicationContext)

        setContent {
            BrothersBirthdayAppTheme {
                val navController = rememberNavController()
                AppNavHost(navController, dataStore)
            }
        }
    }
}