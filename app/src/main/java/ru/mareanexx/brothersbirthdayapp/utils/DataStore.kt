package ru.mareanexx.brothersbirthdayapp.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("settings")

class DataStore(private val context: Context) {
    companion object {
        private val COINS_KEY = intPreferencesKey("coins")
        private val ROBLOX_COMPLETED = booleanPreferencesKey("roblox_game")
        private val CROSSWORD_COMPLETED = booleanPreferencesKey("crossword")
        private val SERGALLERY_COMPLETED = booleanPreferencesKey("sergallery")
        private val CHAT_COMPLETED = booleanPreferencesKey("video_chat")
        private val QUEST_COMPLETED = booleanPreferencesKey("quest")
        private val MUSEUM_COMPLETED = booleanPreferencesKey("museum")

        private val GIFT1_RECEIVED = booleanPreferencesKey("gift1")
        private val GIFT2_RECEIVED = booleanPreferencesKey("gift2")
        private val GIFT3_RECEIVED = booleanPreferencesKey("gift3")
    }

    // Если первый раз производится получение значения, то вернется 0
    val numberOfCoins: Flow<Int> = context.dataStore.data.map {
            preferences -> preferences[COINS_KEY] ?: 0
    }
    suspend fun saveNumberOfCoins(coins: Int) {
        context.dataStore.edit { preferences ->
            preferences[COINS_KEY] = coins
        }
    }

    val isRobloxGameCompleted: Flow<Boolean> = context.dataStore.data.map {
            preferences -> preferences[ROBLOX_COMPLETED] ?: false
    }

    val isCrosswordCompleted: Flow<Boolean> = context.dataStore.data.map {
            preferences -> preferences[CROSSWORD_COMPLETED] ?: false
    }

    val isMuseumCompleted: Flow<Boolean> = context.dataStore.data.map {
            preferences -> preferences[MUSEUM_COMPLETED] ?: false
    }
    val isChatCompleted: Flow<Boolean> = context.dataStore.data.map {
            preferences -> preferences[CHAT_COMPLETED] ?: false
    }

    val isQuestCompleted: Flow<Boolean> = context.dataStore.data.map {
            preferences -> preferences[QUEST_COMPLETED] ?: false
    }

    val isGalleryCompleted: Flow<Boolean> = context.dataStore.data.map {
            preferences -> preferences[SERGALLERY_COMPLETED] ?: false
    }

    suspend fun setGameCompleted(gameTypeSP: GameTypeSP) {
        context.dataStore.edit { prefs ->
            when(gameTypeSP) {
                GameTypeSP.CHAT -> prefs[CHAT_COMPLETED] = true
                GameTypeSP.MUSEUM -> prefs[MUSEUM_COMPLETED] = true
                GameTypeSP.ROBLOX -> prefs[ROBLOX_COMPLETED] = true
                GameTypeSP.QUEST -> prefs[QUEST_COMPLETED] = true
                GameTypeSP.CROSSWORD -> prefs[CROSSWORD_COMPLETED] = true
                GameTypeSP.SERGALLERY -> prefs[SERGALLERY_COMPLETED] = true
            }
        }
    }

    suspend fun setGiftReceived(giftTypeSP: GiftTypeSP) {
        context.dataStore.edit { prefs ->
            when(giftTypeSP) {
                GiftTypeSP.GIFT1 -> prefs[GIFT1_RECEIVED] = true
                GiftTypeSP.GIFT2 -> prefs[GIFT2_RECEIVED] = true
                GiftTypeSP.GIFT3 -> prefs[GIFT3_RECEIVED] = true
            }
        }
    }

    val isGift1Received: Flow<Boolean> = context.dataStore.data.map {
            preferences -> preferences[GIFT1_RECEIVED] ?: false
    }

    val isGift2Received: Flow<Boolean> = context.dataStore.data.map {
            preferences -> preferences[GIFT2_RECEIVED] ?: false
    }

    val isGift3Received: Flow<Boolean> = context.dataStore.data.map {
            preferences -> preferences[GIFT3_RECEIVED] ?: false
    }
}