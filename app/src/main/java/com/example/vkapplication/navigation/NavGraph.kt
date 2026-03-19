package com.example.vkapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.vkapplication.presentation.appdetail.AppDetailScreen
import com.example.vkapplication.presentation.applist.AppListScreen

private const val ROUTE_APP_LIST = "app_list"
private const val ROUTE_APP_DETAIL = "app_detail"
private const val ARG_APP_ID = "appId"

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_APP_LIST
    ) {
        composable(ROUTE_APP_LIST) {
            AppListScreen(
                onAppClick = { appId ->
                    navController.navigate("$ROUTE_APP_DETAIL/$appId")
                }
            )
        }
        composable(
            route = "$ROUTE_APP_DETAIL/{$ARG_APP_ID}",
            arguments = listOf(navArgument(ARG_APP_ID) { type = NavType.IntType })
        ) { backStackEntry ->
            val appId = backStackEntry.arguments?.getInt(ARG_APP_ID) ?: return@composable
            AppDetailScreen(
                appId = appId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
