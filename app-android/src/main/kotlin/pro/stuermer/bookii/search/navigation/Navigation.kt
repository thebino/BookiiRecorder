package pro.stuermer.bookii.search.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import pro.stuermer.bookii.common.navigation.LeafScreen
import pro.stuermer.bookii.common.navigation.Screen

fun NavGraphBuilder.searchNavigation(
    navController: NavController,
    root: Screen = Screen.Search,
) {
    navigation(
        route = Screen.Search.route,
        startDestination = LeafScreen.Search.createRoute(root = root),
    ) {
        composable(
            route = LeafScreen.Search.createRoute(root = Screen.Search),
        ) {
            Text(
                modifier = Modifier.clickable {
                    navController::navigateUp
                }.statusBarsPadding(),
                text = "Search",
            )
        }
    }
}
