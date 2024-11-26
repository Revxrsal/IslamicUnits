package net.kasasbeh.fiqh.quantities.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.kasasbeh.fiqh.quantities.data.School
import net.kasasbeh.fiqh.quantities.data.schoolOf
import net.kasasbeh.fiqh.quantities.ui.theme.isSystemUsingDarkTheme

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "fiqh-quantities-preferences"
)

private val DARK_THEME = booleanPreferencesKey("dark-theme")
private val SCHOOL = stringPreferencesKey("school")

class DataStoreRepository(private val context: Context) {

    val darkTheme: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[DARK_THEME] ?: context.isSystemUsingDarkTheme()
    }

    val school: Flow<School> = context.dataStore.data.map { preferences ->
        preferences[SCHOOL]?.let { schoolOf(it) } ?: School.HANBALI
    }

    suspend fun toggleTheme() {
        context.dataStore.edit { settings ->
            val isDarkTheme = settings[DARK_THEME] ?: context.isSystemUsingDarkTheme()
            settings[DARK_THEME] = !isDarkTheme
        }
    }

    suspend fun setSchool(school: School) {
        context.dataStore.edit { settings ->
            settings[SCHOOL] = school.name
        }
    }
}