package pro.stuermer.bookii.devices

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DismissValue
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.get
import pro.stuermer.bookii.composables.StorageInformation
import pro.stuermer.bookii.devices.composables.DeviceInformation

@Composable
fun Devices(
    navigateUp: () -> Unit,
) {
    val viewmodel = get<DevicesViewModel>()

    Devices(
        viewState = viewmodel.uiState.collectAsState().value,
        navigateUp = navigateUp
    )
}

@Composable
internal fun Devices(
    viewState: DevicesViewState,
    navigateUp: () -> Unit
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
                title = { Text(text = stringResource(id = R.string.devices_title)) },
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
                },
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                SwipeToDismiss(
                    state = dismissSnackbarState,
                    background = {},
                    dismissContent = { Snackbar(snackbarData = data) },
                    modifier = Modifier
                        // padding?
                        .padding(
//                            horizontal = Layout.bodyMargin
                        )
                        .fillMaxWidth(),
                )
            }
        },
    ) { paddingValues ->
        Surface(
            shadowElevation = 2.dp,
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
        ) {
            AnimatedVisibility(visible = viewState.isConnected) {
                Column {
                    DeviceInformation(
                        isConnected = viewState.isConnected,
                        manufacture = viewState.manufacture,
                        model = viewState.model,
                        deviceVersion = viewState.deviceFirmwareVersion,
                        deviceSerial = viewState.deviceSerial,
                    )
                    if (viewState.isStorageInfoAvailable()) {
                        StorageInformation(
                            modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth(),
                            capacity = viewState.capacity!!,
                            occupiedSpace = viewState.occupiedSpace!!,
                            freeSpace = viewState.freeSpace!!
                        )
                    }
                }
            }
        }
    }
}
