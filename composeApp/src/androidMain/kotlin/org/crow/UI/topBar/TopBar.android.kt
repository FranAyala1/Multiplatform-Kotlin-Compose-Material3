package org.crow.ui.topBar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import org.crow.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun TopBar(onClickNavRailBar: () -> Unit,currentDestination: NavDestination?,onClickBackArrow: () ->Unit) {
    when (currentDestination?.route) {
        Route.Games::class.qualifiedName -> {
            GamesGraphTopBar()
        }
        Route.Search::class.qualifiedName -> {
            SearchGraphTopBar()
        }
        else->{
            GamesGraphTopBar(navigationIcon = { ArrowBackIcon(onClickArrowBack = onClickBackArrow) })
        }
    }
}