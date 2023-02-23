package pro.stuermer.bookii.records.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import pro.stuermer.bookii.common.navigation.LeafScreen
import pro.stuermer.bookii.common.navigation.Screen
import pro.stuermer.bookii.records.Records

fun NavGraphBuilder.recordsNavigation(
    navController: NavController,
    root: Screen = Screen.Books,
) {
    // new record
    composable(
        route = LeafScreen.Records.createRoute(root = Screen.Books)
    ) {
        Records()
    }

    // record details
    composable(
        route = LeafScreen.RecordDetails.createRoute(root = Screen.Books),
        arguments = listOf(
            navArgument("bookId") { type = NavType.LongType },
            navArgument("recordId") { type = NavType.LongType },
        ),
    ) {
        Records(

        )
    }
}
