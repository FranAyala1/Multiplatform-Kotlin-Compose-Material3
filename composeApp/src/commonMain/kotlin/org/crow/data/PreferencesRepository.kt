package org.crow.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppPreferencesRepository(private val dataStore: DataStore<Preferences>) {

    private val userTokenKey = stringPreferencesKey("user_token")
//    private val isLoggedKey = stringPreferencesKey("is_logged")

    val userToken: Flow<String?> = dataStore.data
        .map { preferences -> preferences[userTokenKey] }
//    val isLogged: Flow<String?> = dataStore.data
//        .map { preferences -> preferences[isLoggedKey] }

    suspend fun saveUserToken(token: String) {
        dataStore.edit { preferences ->
            preferences[userTokenKey] = token
        }
    }
//    suspend fun saveIsLogged(isLogged: Boolean) {
//        dataStore.edit { preferences ->
//            preferences[isLoggedKey] = isLogged.toString()
//        }
//    }
//    suspend fun clearLogin() {
//        dataStore.edit { preferences ->
//            preferences.remove(isLoggedKey)
//        }
//    }

    suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(userTokenKey)
        }
    }
}