package pro.stuermer.bookii

import pro.stuermer.bookii.common.usb.repository.DeviceRepository
import pro.stuermer.bookii.common.usb.repository.UsbDeviceRepository
import androidx.compose.runtime.mutableStateOf
import pro.stuermer.bookii.common.usb.model.DeviceInfo
import androidx.compose.ui.window.WindowState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope

object DebugState {
    private lateinit var repository: DeviceRepository
    lateinit var windowState: WindowState
    val scope = CoroutineScope(Dispatchers.IO)

    private val deviceInfo = mutableStateOf<DeviceInfo?>(null)
    fun deviceInfo(): DeviceInfo? {
        return deviceInfo.value
    }

    fun applyContent(state: WindowState): DebugState {
        windowState = state

//        repository = UsbDeviceRepository()

//        initData()

        return this
    }

    /**
     * Check usb
     */
    fun initData() {
//        scope.launch(Dispatchers.IO) {
//            try {
//                deviceInfo.value = repository.deviceInfo
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
    }
}
