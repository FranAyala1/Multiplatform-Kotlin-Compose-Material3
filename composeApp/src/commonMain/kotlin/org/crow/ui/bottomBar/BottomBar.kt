package org.crow.ui.bottomBar

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.crow.navigation.CrowDestination
import org.crow.navigation.CrowNavigationTopLevel
import org.crow.navigation.Graph


@Composable
fun BottomBar(
    onClick: (route: Graph) -> Unit,
    isSelected:(topLevelRoute:CrowDestination) -> Boolean,
) {
    NavigationBar(windowInsets = NavigationBarDefaults.windowInsets,
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer) {
        CrowNavigationTopLevel.forEach { topLevelRoute ->
            NavigationBarItem(
                icon = { Icon(topLevelRoute.icon, contentDescription = null) },
                label = { Text(topLevelRoute.name) },
                selected = isSelected(topLevelRoute),
                onClick = { onClick(topLevelRoute.route) }
            )
        }
    }
}