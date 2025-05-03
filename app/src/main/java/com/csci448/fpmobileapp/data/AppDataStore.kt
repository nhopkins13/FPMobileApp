package com.csci448.fpmobileapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// 1. Define the DataStore instance using a property delegate
//    'saurus_settings' will be the name of the preferences file.
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "saurus_settings")

// 2. Define Keys for the values we want to store
object SaurusPreferenceKeys {
    val HAT_ID = intPreferencesKey("saurus_hat_id")
    val NECKWEAR_ID = intPreferencesKey("saurus_neckwear_id")
    val BELT_ID = intPreferencesKey("saurus_belt_id")

    val TOTAL_COINS_EARNED = longPreferencesKey("total_coins_earned")
    val TOTAL_COINS_SPENT = longPreferencesKey("total_coins_spent")
    val NAV_BAR_COLOR_KEY = stringPreferencesKey("nav_bar_color_key") // Store color identifier string
    val APP_THEME_KEY = stringPreferencesKey("app_theme_key")

    // Define the default value if a key is not found (0 means "None")
    const val DEFAULT_EQUIPPED_ID = 0
    const val DEFAULT_COIN_VALUE = 0L
    const val DEFAULT_NAV_BAR_COLOR_KEY = "Default" // Default identifier for nav color
    const val DEFAULT_APP_THEME_KEY = "System"

}