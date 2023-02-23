package pro.stuermer.bookii.devices.domain

import android.hardware.usb.UsbDevice
import pro.stuermer.bookii.usb.repository.ConnectionRepository

class InitConnectionUseCase(
    private val connectionRepository: ConnectionRepository
) {
    operator fun invoke(device: UsbDevice) {
        connectionRepository.initConnection(device)
    }
}
