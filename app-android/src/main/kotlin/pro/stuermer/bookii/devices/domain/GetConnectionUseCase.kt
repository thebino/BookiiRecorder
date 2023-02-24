package pro.stuermer.bookii.devices.domain

import pro.stuermer.bookii.usb.repository.ConnectionRepository
import kotlinx.coroutines.flow.Flow
import pro.stuermer.bookii.usb.model.DeviceConnection

class GetConnectionUseCase(
    private val connectionRepository: ConnectionRepository
) {
    operator fun invoke(): Flow<DeviceConnection> {
        return connectionRepository.pollDeviceConnection()
    }
}
