package pro.stuermer.bookii

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.jewel.themes.expui.standalone.theme.DarkTheme
import org.jetbrains.jewel.themes.expui.standalone.theme.LightTheme
import org.jetbrains.jewel.themes.expui.standalone.theme.Theme
import pro.stuermer.bookii.common.usb.repository.DeviceRepository
import pro.stuermer.bookii.common.usb.repository.UsbDeviceRepository

/**
 * Keep track of changes in the current running application.
 */
class ApplicationState {
    // Theme
    var isDark by mutableStateOf(true)
        private set

    fun toggleTheme() {
        isDark = !isDark
    }

    val theme: Theme
        get() {
            return if (isDark) {
                DarkTheme
            } else {
                LightTheme
            }
        }

    // Status
    private var _statusText by mutableStateOf("Ready")
    var statusText: String
        get() = _statusText
        set(value) {
            _statusText = value
        }

    // Devices
    val repository: DeviceRepository = UsbDeviceRepository()
    val connectedDeviceCount: Int
        get() = repository.connectedDevices.size
    val isTransferActive: Boolean
        get() = repository.isTransferActive
}

@Composable
fun rememberApplicationState() = remember {
    ApplicationState()
}
