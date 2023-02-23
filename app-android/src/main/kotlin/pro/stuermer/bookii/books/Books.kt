package pro.stuermer.bookii.books

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.DismissValue
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import pro.stuermer.bookii.books.composables.Book
import pro.stuermer.bookii.common.Layout
import pro.stuermer.bookii.common.preview.MultiDevicePreview

@Composable
fun Books(
    navigateUp: () -> Unit,
    addBook: () -> Unit,
    openDetails: (bookId: Long, recordId: Long?) -> Unit
) {
//    val viewState by viewModel.state.collectAsState()
    Books(
        viewState = BooksViewState.Empty,
        navigateUp = navigateUp,
        addBook = addBook,
        openDetails = openDetails,
    )
}

@Composable
internal fun Books(
    viewState: BooksViewState,
    navigateUp: () -> Unit,
    addBook: () -> Unit,
    openDetails: (bookId: Long, recordId: Long?) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val dismissSnackbarState = rememberDismissState { value ->
        when {
            value != DismissValue.Default -> {
                snackbarHostState.currentSnackbarData?.dismiss()
                true
            }

            else -> false
        }
    }

    viewState.message?.let { message ->
        LaunchedEffect(message) {
//            snackbarHostState.showSnackbar(message.message)
//            // Notify the view model that the message has been dismissed
//            onMessageShown(message.id)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier,
                scrollBehavior = null,
                title = { Text(text = stringResource(id = R.string.books_title)) },
                actions = {
                    // This button refresh allows screen-readers, etc to trigger a refresh.
                    // We only show the button to trigger a refresh, not to indicate that
                    // we're currently refreshing, otherwise we have 4 indicators showing the
                    // same thing.
                    Crossfade(
                        targetState = true,
                        modifier = Modifier.align(Alignment.CenterVertically),
                    ) { isRefreshing ->
                        if (!isRefreshing) {
                            // TODO: refresh button
                            // RefreshButton(onClick = onRefreshActionClick)
                        }
                    }

                    // TODO: settings
//                    UserProfileButton(
//                        loggedIn = loggedIn,
//                        user = user,
//                        onClick = onUserActionClick,
//                        modifier = Modifier.align(Alignment.CenterVertically),
//                    )
                },
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = addBook) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.cd_create_item)
                )
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                SwipeToDismiss(
                    state = dismissSnackbarState,
                    background = {},
                    dismissContent = { Snackbar(snackbarData = data) },
                    modifier = Modifier
                        .padding(horizontal = Layout.bodyMargin)
                        .fillMaxWidth(),
                )
            }
        },
    ) { paddingValues ->
        Surface(
            shadowElevation = 2.dp,
            modifier = Modifier.fillMaxSize(),
        ) {
            val columns = Layout.columns

            LazyVerticalGrid(
                columns = GridCells.Fixed((columns / 1.5).roundToInt()),
                contentPadding = paddingValues,
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                // TODO: empty screen
                // TODO: content
                item {
                    Book(
                        title = "Was ist was junior, Baustelle",
                        description = "Wie baut man ein Haus, eine Straße, eine Brücke, einen Tunnel?",
                        ISBN = "978-3-7886-2203-9",
                        cover = painterResource(id = R.drawable.was_ist_was_junior_baustelle),
                        records = listOf<String>(),
                        onClick = {
                            openDetails(1L, null)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, device = "spec:width=673.5dp,height=841dp,dpi=480", name = "Foldable")
@MultiDevicePreview
@Composable
private fun PreviewBooks() {
    Surface(Modifier.fillMaxWidth()) {
        Books(
            viewState = BooksViewState.Empty,
            navigateUp = {},
            addBook = {},
            openDetails = { _, _ -> }
        )
    }
}
