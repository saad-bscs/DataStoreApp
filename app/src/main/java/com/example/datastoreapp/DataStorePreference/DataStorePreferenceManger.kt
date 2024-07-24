package com.example.datastoreapp.DataStorePreference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalCoroutinesApi::class)
class DataStorePreferenceManger(context: Context) : ViewModel() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(context.packageName)
    private val dataStore = context.dataStore

    private val keyName = stringPreferencesKey("name")
    private val keyAge = intPreferencesKey("age")

//    val fieldKeyName = doublePreferencesKey("key_name")
//    val fieldKeyName = longPreferencesKey("key_name")
//    val fieldKeyName = booleanPreferencesKey("key_name")
//    val fieldKeyName = floatPreferencesKey("key_name")

    var name
        get() = getString(keyName)
        set(value) = setString(keyName, value)

    var age
        get() = getInt(keyAge)
        set(value) = setInt(keyAge, value)


    // String
    private fun setString(key: Preferences.Key<String>, value: String) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[key] = value
            }
        }
    }

    private fun getString(key: Preferences.Key<String>): String {
        return viewModelScope.async {
            runBlocking {
                return@runBlocking dataStore.data.first().toPreferences()[key] ?: ""
            }
        }.getCompleted()
    }


    //    Int
    private fun setInt(key: Preferences.Key<Int>, value: Int) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[key] = value
            }
        }
    }

    private fun getInt(key: Preferences.Key<Int>): Int {
        return viewModelScope.async {
            runBlocking {
                return@runBlocking dataStore.data.first().toPreferences()[key] ?: 0
            }
        }.getCompleted()
    }


    //    Float
    private fun setFloat(key: Preferences.Key<Float>, value: Float) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[key] = value
            }
        }
    }

    private fun getFloat(key: Preferences.Key<Float>): Float {
        return viewModelScope.async {
            runBlocking {
                return@runBlocking dataStore.data.first().toPreferences()[key] ?: 0.toFloat()
            }
        }.getCompleted()
    }


    //    Long
    private fun setLong(key: Preferences.Key<Long>, value: Long) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[key] = value
            }
        }
    }

    private fun getLong(key: Preferences.Key<Long>): Long {
        return viewModelScope.async {
            runBlocking {
                return@runBlocking dataStore.data.first().toPreferences()[key] ?: 0
            }
        }.getCompleted()
    }


    //    Double
    private fun setDouble(key: Preferences.Key<Double>, value: Double) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[key] = value
            }
        }
    }

    private fun getDouble(key: Preferences.Key<Double>): Double {
        return viewModelScope.async {
            runBlocking {
                return@runBlocking dataStore.data.first().toPreferences()[key] ?: 0.0
            }
        }.getCompleted()
    }


    //    Boolean
    private fun setBoolean(key: Preferences.Key<Boolean>, value: Boolean) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[key] = value
            }
        }
    }

    private fun getBoolean(key: Preferences.Key<Boolean>): Boolean {
        return viewModelScope.async {
            runBlocking {
                return@runBlocking dataStore.data.first().toPreferences()[key] ?: false
            }
        }.getCompleted()
    }
}