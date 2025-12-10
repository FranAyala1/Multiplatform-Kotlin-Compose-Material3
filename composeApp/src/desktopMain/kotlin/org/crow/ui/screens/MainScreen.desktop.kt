package org.crow.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.WideNavigationRail
import androidx.compose.material3.WideNavigationRailColors
import androidx.compose.material3.WideNavigationRailItem
import androidx.compose.material3.WideNavigationRailState
import androidx.compose.material3.WideNavigationRailValue
import androidx.compose.material3.rememberWideNavigationRailState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.crow.navigation.CrowDestination
import org.crow.navigation.CrowNavigationTopLevel
import org.crow.navigation.Graph
import org.crow.navigation.mainNavHost
import org.crow.ui.theme.CrowTheme
import org.crow.ui.theme.primaryDark
import org.crow.ui.topBar.TopBar
import org.crow.viewModel.LoginViewModel
import org.koin.compose.viewmodel.koinViewModel
import kotlin.reflect.KClass


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenDesktop(
    onClickBottomBar: (route: Graph) -> Unit,
    onClickBack: () -> Unit,
    currentBackStackEntry: NavBackStackEntry?,
    navController: NavHostController,
    viewModel: LoginViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val navRailState = rememberWideNavigationRailState(WideNavigationRailValue.Expanded)
    CrowTheme {
        Scaffold(
            topBar = {
            }) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
            {
                if (isNavigationRailNeeded(CrowNavigationTopLevel, currentBackStackEntry)) {
                    NavigationRailDesktop(
                        onClickBottomBar = { onClickBottomBar(it) },
                        isSelected = { topLevelRoute ->
                            checkNotNull(currentBackStackEntry?.destination?.hierarchy?.any {
                                it.hasRoute(
                                    topLevelRoute.route::class
                                )
                            })
                        },
                        navRailState = navRailState
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                {
                    mainNavHost(navController, modifier = Modifier.padding(innerPadding),uiState)
                }
            }
        }
    }
}

@Composable
fun NavigationRailDesktop(
    isSelected: (topLevelRoute: CrowDestination) -> Boolean,
    onClickBottomBar: (route: Graph) -> Unit,
    navRailState: WideNavigationRailState
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        WideNavigationRail(
            state = navRailState,
        ) {
            CrowNavigationTopLevel.forEach { topLevelRoute ->
                WideNavigationRailItem(
                    railExpanded = navRailState.currentValue == WideNavigationRailValue.Expanded,
                    icon = { Icon(topLevelRoute.icon, contentDescription = null) },
                    label = { Text(topLevelRoute.name) },
                    selected = isSelected(topLevelRoute),
                    onClick = { onClickBottomBar(topLevelRoute.route) }
                )
            }
        }
        VerticalDivider(thickness = 1.dp)
    }
}

@Composable
actual fun MainScreen(
    onClickBottomBar: (route: Graph) -> Unit,
    currentBackStackEntry: NavBackStackEntry?,
    navController: NavHostController,
    onClickBack: () -> Unit
) {
    MainScreenDesktop(
        onClickBottomBar = onClickBottomBar,
        currentBackStackEntry = currentBackStackEntry,
        navController = navController,
        onClickBack = onClickBack
    )
}

private fun isNavigationRailNeeded(
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