package com.example.datastoreapp

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import timber.log.Timber

class MyApp: Application() {

    companion object {
        val Context.dataStore by preferencesDataStore(name = "settings")
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}