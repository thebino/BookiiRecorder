package pro.stuermer.bookii.bookdetails.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import pro.stuermer.bookii.bookdetails.BookDetails
import pro.stuermer.bookii.common.navigation.LeafScreen
import pro.stuermer.bookii.common.navigation.Screen

fun NavGraphBuilder.bookdetailsNavigation(
    navController: NavController,
    root: Screen = Screen.Books,
) {
    composable(
        route = LeafScreen.BookDetails.createRoute(root = Screen.Books),
        arguments = listOf(
            navArgument("bookId") { type = NavType.LongType },
        ),
    ) {
        BookDetails(
            navigateUp = navController::navigateUp,
            addRecord = {
                navController.navigate(
                    LeafScreen.Records.createRoute(root = Screen.Books),
                )
            },
            openRecordDetails = { bookId: Long, recordId: Long ->
                navController.navigate(
                    LeafScreen.RecordDetails.createRoute(
                        root, bookId = bookId, recordId = recordId
                    )
                )
            }
        )
    }
}
