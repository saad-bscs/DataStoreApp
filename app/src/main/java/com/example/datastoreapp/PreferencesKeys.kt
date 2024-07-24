package com.example.datastoreapp

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {

    val USER_NAME = stringPreferencesKey("user_name")
    val USER_AGE = intPreferencesKey("user_age")
}