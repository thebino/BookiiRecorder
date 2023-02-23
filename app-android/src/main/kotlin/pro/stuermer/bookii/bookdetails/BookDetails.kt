package pro.stuermer.bookii.bookdetails

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DismissValue
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import pro.stuermer.bookii.common.Layout
import pro.stuermer.bookii.common.bodyWidth
import pro.stuermer.bookii.common.composable.Header

@Composable
fun BookDetails(
    navigateUp: () -> Unit,
    addRecord: () -> Unit,
    openRecordDetails: (bookId: Long, recordId: Long) -> Unit,
) {
    BookDetails(
        viewState = BookDetailsViewState.Empty,
        navigateUp = navigateUp,
        addRecord = addRecord,
        openRecordDetails = openRecordDetails,
    )
}

@Composable
internal fun BookDetails(
    viewState: BookDetailsViewState,
    navigateUp: () -> Unit,
    addRecord: () -> Unit,
    openRecordDetails: (bookId: Long, recordId: Long) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val listState = rememberLazyListState()

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
            snackbarHostState.showSnackbar(message.message)
            // Notify the view model that the message has been dismissed
            //onMessageShown(message.id)
        }
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        topBar = {
                 // TODO: app bar
//            ShowDetailsAppBar(
//                title = viewState.show.title ?: "",
//                isRefreshing = viewState.refreshing,
//                onNavigateUp = navigateUp,
//                onRefresh = refresh,
//                scrollBehavior = scrollBehavior,
//                modifier = Modifier.fillMaxWidth(),
//            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = addRecord,
                elevation = FloatingActionButtonDefaults.elevation(8.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Mic,
                    contentDescription = stringResource(id = R.string.cd_add_record)
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
        // The nav bar is handled by the root Scaffold
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets
            .exclude(WindowInsets.navigationBars),
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { contentPadding ->
        //LogCompositions("ShowDetails")

        Surface(modifier = Modifier.bodyWidth()) {
            val gutter = Layout.gutter
            val bodyMargin = Layout.bodyMargin

            LazyColumn(
                state = listState,
                contentPadding = contentPadding,
                modifier = Modifier,
            ) {
                item {
                    Text(text = "Book details")
                }
                item {
//                    Backdrop(
//                        imageModel = backdropImage,
//                        modifier = Modifier
//                            .padding(horizontal = bodyMargin, vertical = gutter)
//                            .fillMaxWidth()
//                            .aspectRatio(16f / 10),
//                    )
                }

                item {
                    Spacer(modifier = Modifier.height(max(gutter, bodyMargin)))
                }

                item {
//                    PosterInfoRow(
//                        show = show,
//                        posterImage = posterImage,
//                        modifier = Modifier.fillMaxWidth(),
//                    )
                }

//                gutterSpacer()

                item {
//                    Header(stringResource(UiR.string.details_about))
                }

//                if (show.summary != null) {
//                    item {
//                        ExpandingText(
//                            text = show.summary!!,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(horizontal = Layout.bodyMargin, vertical = Layout.gutter),
//                        )
//                    }
//                }
            }
        }
    }
}
