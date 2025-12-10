package org.crow.data

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val dataStoreModule: Module = module {
    // Provides DataStore using the Android Context
    single { createDataStore(androidContext()) }
}