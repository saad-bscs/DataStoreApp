package com.example.datastoreapp

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.datastoreapp.MyApp.Companion.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserManager {

    suspend fun saveUserPreferences(context: Context, userName: String, userAge: Int) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.USER_NAME] = userName
            preferences[PreferencesKeys.USER_AGE] = userAge
        }
    }

    fun getUserPreferences(context: Context): Flow<UserPreferences> {
        return context.dataStore.data.map { preferences ->
            val userName = preferences[PreferencesKeys.USER_NAME] ?: ""
            val userAge = preferences[PreferencesKeys.USER_AGE] ?: 0
            UserPreferences(userName, userAge)
        }
    }
}