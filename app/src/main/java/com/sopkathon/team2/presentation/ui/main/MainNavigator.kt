package com.sopkathon.team2.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.sopkathon.team2.presentation.model.Route

class MainNavigator(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination: String = Route.Home::class.qualifiedName ?: ""

    val currentRoute: Route?
        @Composable get() = when (currentDestination?.route) {
            Route.Home::class.qualifiedName -> Route.Home
            Route.Profile::class.qualifiedName -> Route.Profile
            Route.Write::class.qualifiedName -> Route.Write
            Route.Complete::class.qualifiedName -> Route.Complete
            else -> null
        }

    // 기본 네비게이션
    fun navigate(route: Route) {
        val navOptions = navOptions {
            popUpTo(0) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        navController.navigate(route::class.qualifiedName ?: "", navOptions)
    }

    // 스택을 모두 지우고 특정 화면으로 이동
    fun navigateAndClearStack(route: Route) {
        val navOptions = navOptions {
            popUpTo(Route.Home::class.qualifiedName!!) { inclusive = true } // Home 포함해서 스택 초기화
            launchSingleTop = true
        }
        navController.navigate(route::class.qualifiedName ?: "", navOptions)
    }

    private fun popBackStack() {
        navController.popBackStack()
    }

    fun popBackStackIfNotHome() {
        if (!isSameCurrentDestination<Route.Home>()) {
            popBackStack()
        }
    }

    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean =
        navController.currentDestination?.route == T::class.qualifiedName
}