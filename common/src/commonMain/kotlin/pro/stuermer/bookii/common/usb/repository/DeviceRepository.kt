package pro.stuermer.bookii.common.usb.repository

import pro.stuermer.bookii.common.usb.model.DeviceInfo


interface DeviceRepository {
    val hasAccess: Boolean
    fun requestAccess()

    val connectedDevices: List<DeviceInfo>
    val isTransferActive: Boolean
}
