package pro.stuermer.bookii.devices.domain

import pro.stuermer.bookii.usb.repository.ConnectionRepository

class CloseConnectionUseCase(
    private val connectionRepository: ConnectionRepository
) {
    operator fun invoke(): Unit = connectionRepository.closeConnection()
}
