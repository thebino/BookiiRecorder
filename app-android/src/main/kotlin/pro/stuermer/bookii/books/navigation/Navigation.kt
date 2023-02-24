package pro.stuermer.bookii.books.navigation

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import pro.stuermer.bookii.books.Books
import pro.stuermer.bookii.common.navigation.LeafScreen
import pro.stuermer.bookii.common.navigation.Screen

fun NavGraphBuilder.booksNavigation(
    navController: NavController,
    root: Screen = Screen.Books,
) {
    navigation(
        route = Screen.Books.route,
        startDestination = LeafScreen.Books.createRoute(root),
    ) {
        composable(route = LeafScreen.Books.createRoute(root = Screen.Books),) {
            Books(
                navigateUp = navController::navigateUp,
                addBook = {
                    navController.navigate(
                        LeafScreen.AddBook.createRoute(root = Screen.Books),
                    )
                },
                openDetails = { bookId, recordId ->
                    navController.navigate(LeafScreen.BookDetails.createRoute(root, bookId))

                    // If we have an record id, we also open that
                    if (recordId != null) {
                        navController.navigate(
                            LeafScreen.RecordDetails.createRoute(root, bookId, recordId),
                        )
                    }
                },
            )
        }

        composable(route = LeafScreen.RecordDetails.createRoute(root = Screen.Books),) {
            Text(
                modifier = Modifier.statusBarsPadding(),
                text = "Record details",
                color = Color.Green
            )
        }

        composable(route = LeafScreen.AddBook.createRoute(root = Screen.Books),) {
            Text(
                modifier = Modifier.statusBarsPadding(),
                text = "Add Book",
                color = Color.Green
            )
        }
    }
}
