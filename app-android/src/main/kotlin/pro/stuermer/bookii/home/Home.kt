@file:OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)

package pro.stuermer.bookii.home

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lan
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Lan
import androidx.compose.material3.Divider
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import pro.stuermer.bookii.R
import pro.stuermer.bookii.common.navigation.LeafScreen
import pro.stuermer.bookii.common.navigation.Screen
import pro.stuermer.bookii.navigation.BookiiNavigation
import pro.stuermer.bookii.navigation.currentScreenAsState

import pro.stuermer.bookii.books.R as BooksR
import pro.stuermer.bookii.devices.R as DevicesR

@Composable
fun Home(
    modifier: Modifier = Modifier,
) {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberAnimatedNavController(bottomSheetNavigator)

    val currentSelectedItem by navController.currentScreenAsState()

    val configuration = LocalConfiguration.current
    val orientation = LocalConfiguration.current.orientation
    val useBottomNavigation =
        configuration.smallestScreenWidthDp < 600 && orientation == Configuration.ORIENTATION_PORTRAIT

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (useBottomNavigation) {
                NavigationBar(modifier = modifier) {
                    for (item in HomeNavigationItems) {
                        NavigationBarItem(
                            icon = {
                                HomeNavigationItemIcon(
                                    item = item,
                                    selected = currentSelectedItem == item.screen,
                                )
                            },
                            label = { Text(text = stringResource(item.labelResId)) },
                            selected = currentSelectedItem == item.screen,
                            onClick = {
                                navController.navigate(item.screen.route)
                            },
                        )
                    }
                }
            } else {
                Spacer(
                    Modifier
                        .windowInsetsBottomHeight(WindowInsets.navigationBars)
                        .fillMaxWidth(),
                )
            }
        },
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.exclude(WindowInsets.statusBars), // Is hanled by content
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            if (!useBottomNavigation) {
                NavigationRail(modifier = Modifier.fillMaxHeight()) {
                    for (item in HomeNavigationItems) {
                        NavigationRailItem(
                            icon = {
                                HomeNavigationItemIcon(
                                    item = item,
                                    selected = currentSelectedItem == item.screen,
                                )
                            },
                            alwaysShowLabel = false,
                            label = { Text(text = stringResource(item.labelResId)) },
                            selected = currentSelectedItem == item.screen,
                            onClick = {
                                navController.navigate(item.screen.route) {
                                    launchSingleTop = true
                                    restoreState = true

                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                }
                            },
                        )
                    }
                }

                Divider(
                    Modifier
                        .fillMaxHeight()
                        .width(1.dp),
                )
            }

            ModalBottomSheetLayout(
                bottomSheetNavigator = bottomSheetNavigator,
                sheetShape = MaterialTheme.shapes.large.copy(
                    bottomStart = CornerSize(0.dp),
                    bottomEnd = CornerSize(0.dp),
                ),
                sheetBackgroundColor = MaterialTheme.colorScheme.surface,
                sheetContentColor = MaterialTheme.colorScheme.onSurface,
                scrimColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.33f),
            ) {
                BookiiNavigation(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(), navController = navController
                )
            }
        }
    }
}


@Composable
private fun HomeNavigationItemIcon(item: HomeNavigationItem, selected: Boolean) {
    val painter = when (item) {
        is HomeNavigationItem.ResourceIcon -> painterResource(item.iconResId)
        is HomeNavigationItem.ImageVectorIcon -> rememberVectorPainter(item.iconImageVector)
    }
    val selectedPainter = when (item) {
        is HomeNavigationItem.ResourceIcon -> item.selectedIconResId?.let { painterResource(it) }
        is HomeNavigationItem.ImageVectorIcon -> item.selectedImageVector?.let {
            rememberVectorPainter(
                it
            )
        }
    }

    if (selectedPainter != null) {
        Crossfade(targetState = selected) {
            Icon(
                painter = if (it) selectedPainter else painter,
                contentDescription = stringResource(item.contentDescriptionResId),
            )
        }
    } else {
        Icon(
            painter = painter,
            contentDescription = stringResource(item.contentDescriptionResId),
        )
    }
}

private sealed class HomeNavigationItem(
    val screen: Screen,
    @StringRes val labelResId: Int,
    @StringRes val contentDescriptionResId: Int,
) {
    class ResourceIcon(
        screen: Screen,
        @StringRes labelResId: Int,
        @StringRes contentDescriptionResId: Int,
        @DrawableRes val iconResId: Int,
        @DrawableRes val selectedIconResId: Int? = null,
    ) : HomeNavigationItem(screen, labelResId, contentDescriptionResId)

    class ImageVectorIcon(
        screen: Screen,
        @StringRes labelResId: Int,
        @StringRes contentDescriptionResId: Int,
        val iconImageVector: ImageVector,
        val selectedImageVector: ImageVector? = null,
    ) : HomeNavigationItem(screen, labelResId, contentDescriptionResId)
}

private val HomeNavigationItems = listOf(
    HomeNavigationItem.ImageVectorIcon(
        screen = Screen.Books,
        labelResId = BooksR.string.books_title,
        contentDescriptionResId = BooksR.string.cd_books_title,
        iconImageVector = Icons.Outlined.AutoStories,
        selectedImageVector = Icons.Default.AutoStories,
    ),
    HomeNavigationItem.ImageVectorIcon(
        screen = Screen.Devices,
        labelResId = DevicesR.string.devices_title,
        contentDescriptionResId = DevicesR.string.cd_devices_title,
        iconImageVector = Icons.Default.Edit,
        selectedImageVector = Icons.Default.Edit,
    ),
    HomeNavigationItem.ImageVectorIcon(
        screen = Screen.Transfers,
        labelResId = R.string.transfers_title,
        contentDescriptionResId = R.string.cd_transfers_title,
        iconImageVector = Icons.Outlined.Lan,
        selectedImageVector = Icons.Default.Lan,
    ),
    HomeNavigationItem.ImageVectorIcon(
        screen = Screen.Search,
        labelResId = R.string.search_navigation_title,
        contentDescriptionResId = R.string.cd_search_navigation_title,
        iconImageVector = Icons.Default.Search,
    ),
)
