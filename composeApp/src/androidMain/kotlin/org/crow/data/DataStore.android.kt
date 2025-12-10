package org.crow.data

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.withContext
import okio.Path.Companion.toPath
import android.content.Context

fun createDataStore(context: Context): DataStore<Preferences> =
    createDataStore {
        context.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
    }