package org.crow.ui.screens

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.crow.data.DataRepository
import org.crow.data.getKoinModules
import org.crow.navigation.CrowDestination
import org.crow.navigation.CrowNavigationTopLevel
import org.crow.navigation.Graph
import org.crow.navigation.Route
import org.crow.navigation.mainNavHost
import org.crow.ui.bottomBar.BottomBar
import org.crow.ui.theme.CrowTheme
import org.crow.ui.topBar.TopBar
import org.crow.viewModel.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.context.startKoin
import kotlin.reflect.KClass

@Composable
actual fun MainScreen(
    onClickBottomBar: (route: Graph) -> Unit,
    currentBackStackEntry: NavBackStackEntry?,
    navController: NavHostController,
    onClickBack: () -> Unit
) {
    MainScreenAndroid(
        onClickBottomBar = onClickBottomBar,
        currentBackStackEntry = currentBackStackEntry,
        navController = navController,
        onClickBack = onClickBack
    )
}

@Composable
fun MainScreenAndroid(
    onClickBottomBar: (route: Graph) -> Unit,
    currentBackStackEntry: NavBackStackEntry?,
    navController: NavHostController,
    onClickBack: () -> Unit,
    viewModel: LoginViewModel= koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    CrowTheme {
        Screen {
            Scaffold(
                contentWindowInsets = WindowInsets.statusBars,
                bottomBar = {
                    if (isBottomBarNeeded(CrowNavigationTopLevel, currentBackStackEntry)) {
                        BottomBar(
                            onClick = { onClickBottomBar(it) },
                            isSelected = { topLevelRoute ->
                                checkNotNull(currentBackStackEntry?.destination?.hierarchy?.any {
                                    it.hasRoute(
                                        topLevelRoute.route::class
                                    )
                                })
                            }
                        )
                    }
                },
            ) { innerPadding ->
                mainNavHost(navController, modifier = Modifier.padding(innerPadding),uiState)

            }
        }
    }
}

private fun isBottomBarNeeded(
    route: List<CrowDestination>,
    currentBackStackEntry: NavBackStackEntry?
): Boolean {
    var isNeeded = false
    route.forEach { navTopLevel ->
        if (currentBackStackEntry?.destination.isRouteInHierarchy(navTopLevel.route::class)) {
            isNeeded = true
        }
    }
    return isNeeded
}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false