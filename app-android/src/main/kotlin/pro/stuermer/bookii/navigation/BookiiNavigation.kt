package pro.stuermer.bookii.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import pro.stuermer.bookii.bookdetails.navigation.bookdetailsNavigation
import pro.stuermer.bookii.books.Books
import pro.stuermer.bookii.books.navigation.booksNavigation
import pro.stuermer.bookii.common.navigation.LeafScreen
import pro.stuermer.bookii.common.navigation.Screen
import pro.stuermer.bookii.devices.devicesNavigation
import pro.stuermer.bookii.records.navigation.recordsNavigation
import pro.stuermer.bookii.search.navigation.searchNavigation
import pro.stuermer.bookii.transfers.navigation.transfersNavigation

@ExperimentalAnimationApi
@Composable
internal fun BookiiNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Books.route,
    ) {
        booksNavigation(navController = navController)
        bookdetailsNavigation(navController = navController)
        recordsNavigation(navController = navController)
        devicesNavigation(navController = navController)
        transfersNavigation(navController = navController)
        searchNavigation(navController = navController)
    }
}
