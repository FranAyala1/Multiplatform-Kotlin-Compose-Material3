package org.crow.ui.topBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination


@Composable
actual fun TopBar(
    onClickNavRailBar: () -> Unit,
    currentDestination: NavDestination?,
    onClickBackArrow: () -> Unit
) {
}