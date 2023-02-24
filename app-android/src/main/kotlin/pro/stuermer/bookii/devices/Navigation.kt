package pro.stuermer.bookii.devices

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import pro.stuermer.bookii.common.navigation.LeafScreen
import pro.stuermer.bookii.common.navigation.Screen

fun NavGraphBuilder.devicesNavigation(
    navController: NavController,
    root: Screen = Screen.Devices,
) {
    navigation(
        route = Screen.Devices.route,
        startDestination = LeafScreen.Devices.createRoute(root = root),
    ) {
        composable(
            route = LeafScreen.Devices.createRoute(root = Screen.Devices),
        ) {
            Devices(
                navigateUp = navController::navigateUp
            )
        }
    }
}
