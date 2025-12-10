package org.crow.data

import org.koin.core.module.Module
import org.koin.dsl.module

actual val dataStoreModule: Module = module {
    single { createDataStore() }
}