package org.crow

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.crow.data.getKoinModules
import org.koin.core.context.GlobalContext.startKoin

fun main() {
    startKoin {
        modules(getKoinModules())
    }
    application {
        Window(onCloseRequest = ::exitApplication, title = "Crow") {
            App()
        }
    }
}