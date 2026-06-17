package com.rital.warehouse.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.rital.warehouse.core.Constants

class UserPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        val USER_ID = stringPreferencesKey("user_id")
    }

    suspend fun saveUserId(userId: String) {
        dataStore.edit {
            it[USER_ID] = userId
        }
    }

    suspend fun getUserId(): String {
        return dataStore.data.first()[USER_ID]?:Constants.EMPTY_STRING
    }
}