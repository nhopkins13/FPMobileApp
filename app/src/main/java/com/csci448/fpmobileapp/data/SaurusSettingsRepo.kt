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

    // Helper function to map Preferences to our data class
    private fun mapSaurusPreferences(preferences: Preferences): SaurusPreferences {
        val hatId = preferences[SaurusPreferenceKeys.HAT_ID] ?: SaurusPreferenceKeys.DEFAULT_EQUIPPED_ID
        val neckwearId = preferences[SaurusPreferenceKeys.NECKWEAR_ID] ?: SaurusPreferenceKeys.DEFAULT_EQUIPPED_ID
        val beltId = preferences[SaurusPreferenceKeys.BELT_ID] ?: SaurusPreferenceKeys.DEFAULT_EQUIPPED_ID
        Log.d(TAG, "Mapped Preferences: Hat=$hatId, Neckwear=$neckwearId, Belt=$beltId")
        return SaurusPreferences(hatId, neckwearId, beltId)
    }
}