package pro.stuermer.bookii

import android.hardware.usb.UsbDevice
import androidx.lifecycle.ViewModel
import pro.stuermer.bookii.devices.domain.CloseConnectionUseCase
import pro.stuermer.bookii.devices.domain.InitConnectionUseCase

/**
 * Initialize and close device connections
 */
class MainViewModel(
    private val initConnectionUseCase: InitConnectionUseCase,
    private val closeConnectionUseCase: CloseConnectionUseCase,
) : ViewModel() {
    fun startCommunication(device: UsbDevice) {
        initConnectionUseCase(device = device)
    }

    fun stopCommunication() {
        closeConnectionUseCase()
    }
}
