package com.csci448.fpmobileapp.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

// Data class to hold the loaded preferences
data class SaurusPreferences(val hatId: Int, val neckwearId: Int, val beltId: Int)

class SaurusSettingsRepo(private val dataStore: DataStore<Preferences>) {

    private val TAG: String = "SaurusSettingsRepo"

    // Flow to read the preferences
    val saurusPreferencesFlow: Flow<SaurusPreferences> = dataStore.data
        .catch { exception ->
            // Handle potential errors when reading data
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences.", exception)
                emit(emptyPreferences()) // Emit empty preferences on error
            } else {
                throw exception
            }
        }.map { preferences ->
            // Map Preferences object to our SaurusPreferences data class
            mapSaurusPreferences(preferences)
        }

    val totalCoinsEarnedFlow: Flow<Long> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading total earned coins.", exception)
                emit(emptyPreferences())
            } else { throw exception }
        }.map { preferences ->
            // Explicitly declare the type of the value being returned
            val earnedValue: Long = preferences[SaurusPreferenceKeys.TOTAL_COINS_EARNED] ?: SaurusPreferenceKeys.DEFAULT_COIN_VALUE
            earnedValue // Return the Long
        }

    val totalCoinsSpentFlow: Flow<Long> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading total spent coins.", exception)
                emit(emptyPreferences())
            } else { throw exception }
        }.map { preferences ->
            // Explicitly declare the type of the value being returned
            val spentValue: Long = preferences[SaurusPreferenceKeys.TOTAL_COINS_SPENT] ?: SaurusPreferenceKeys.DEFAULT_COIN_VALUE
            spentValue // Return the Long
        }

    val navBarColorKeyFlow: Flow<String> = dataStore.data
        .catch { /* ... error handling ... */ }
        .map { preferences ->
            preferences[SaurusPreferenceKeys.NAV_BAR_COLOR_KEY] ?: SaurusPreferenceKeys.DEFAULT_NAV_BAR_COLOR_KEY
        }

    val appThemeKeyFlow: Flow<String> = dataStore.data
        .catch { /* ... error handling ... */ }
        .map { preferences ->
            preferences[SaurusPreferenceKeys.APP_THEME_KEY] ?: SaurusPreferenceKeys.DEFAULT_APP_THEME_KEY
        }



    // Function to update the Hat ID
    suspend fun updateHatId(hatId: Int) {
        dataStore.edit { preferences ->
            preferences[SaurusPreferenceKeys.HAT_ID] = hatId
        }
        Log.d(TAG, "Updated Hat ID to: $hatId")
    }

    // Function to update the Neckwear ID
    suspend fun updateNeckwearId(neckwearId: Int) {
        dataStore.edit { preferences ->
            preferences[SaurusPreferenceKeys.NECKWEAR_ID] = neckwearId
        }
        Log.d(TAG, "Updated Neckwear ID to: $neckwearId")

    }

    // Function to update the Belt ID
    suspend fun updateBeltId(beltId: Int) {
        dataStore.edit { preferences ->
            preferences[SaurusPreferenceKeys.BELT_ID] = beltId
        }
        Log.d(TAG, "Updated Belt ID to: $beltId")
    }

    // --- Update functions for Coins ---
    suspend fun increaseCoinsEarned(amount: Int) {
        if (amount <= 0) return // Don't add zero or negative
        dataStore.edit { preferences ->
            val currentEarned = preferences[SaurusPreferenceKeys.TOTAL_COINS_EARNED] ?: SaurusPreferenceKeys.DEFAULT_COIN_VALUE
            preferences[SaurusPreferenceKeys.TOTAL_COINS_EARNED] = currentEarned + amount
            Log.d(TAG, "Increased Earned Coins by $amount. New total: ${currentEarned + amount}")
        }
    }

    suspend fun decreaseCoinsEarned(amount: Int) {
        if (amount <= 0) return // Don't subtract zero or negative
        dataStore.edit { preferences ->
            val currentEarned = preferences[SaurusPreferenceKeys.TOTAL_COINS_EARNED] ?: SaurusPreferenceKeys.DEFAULT_COIN_VALUE
            // Ensure earned doesn't go below zero, though logically it shouldn't if logic is right
            preferences[SaurusPreferenceKeys.TOTAL_COINS_EARNED] = maxOf(0L, currentEarned - amount)
            Log.d(TAG, "Decreased Earned Coins by $amount. New total: ${maxOf(0L, currentEarned - amount)}")
        }
    }

    suspend fun increaseCoinsSpent(amount: Int) {
        if (amount <= 0) return // Don't add zero or negative
        dataStore.edit { preferences ->
            val currentSpent = preferences[SaurusPreferenceKeys.TOTAL_COINS_SPENT] ?: SaurusPreferenceKeys.DEFAULT_COIN_VALUE
            preferences[SaurusPreferenceKeys.TOTAL_COINS_SPENT] = currentSpent + amount
            Log.d(TAG, "Increased Spent Coins by $amount. New total: ${currentSpent + amount}")
        }
    }

    // Helper function to map Preferences to our data class
    private fun mapSaurusPreferences(preferences: Preferences): SaurusPreferences {
        val hatId = preferences[SaurusPreferenceKeys.HAT_ID] ?: SaurusPreferenceKeys.DEFAULT_EQUIPPED_ID
        val neckwearId = preferences[SaurusPreferenceKeys.NECKWEAR_ID] ?: SaurusPreferenceKeys.DEFAULT_EQUIPPED_ID
        val beltId = preferences[SaurusPreferenceKeys.BELT_ID] ?: SaurusPreferenceKeys.DEFAULT_EQUIPPED_ID
        Log.d(TAG, "Mapped Preferences: Hat=$hatId, Neckwear=$neckwearId, Belt=$beltId")
        return SaurusPreferences(hatId, neckwearId, beltId)
    }

    suspend fun updateNavBarColorKey(colorKey: String) {
        dataStore.edit { preferences ->
            preferences[SaurusPreferenceKeys.NAV_BAR_COLOR_KEY] = colorKey
        }
        Log.d(TAG, "Updated Nav Bar Color Key to: $colorKey")
    }

    suspend fun updateAppThemeKey(themeKey: String) {
        dataStore.edit { preferences ->
            preferences[SaurusPreferenceKeys.APP_THEME_KEY] = themeKey
        }
        Log.d(TAG, "Updated App Theme Key to: $themeKey")
    }
}