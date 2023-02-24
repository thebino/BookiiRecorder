package pro.stuermer.bookii.transfers.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import pro.stuermer.bookii.common.navigation.LeafScreen
import pro.stuermer.bookii.common.navigation.Screen
import pro.stuermer.bookii.transfers.Transfers

fun NavGraphBuilder.transfersNavigation(
    navController: NavController,
    root: Screen = Screen.Transfers,
) {
    navigation(
        route = Screen.Transfers.route,
        startDestination = LeafScreen.Transfers.createRoute(root = root),
    ) {
        composable(
            route = LeafScreen.Transfers.createRoute(root = Screen.Transfers),
        ) {
            Transfers(
                navigateUp = navController::navigateUp
            )
        }
    }
}
