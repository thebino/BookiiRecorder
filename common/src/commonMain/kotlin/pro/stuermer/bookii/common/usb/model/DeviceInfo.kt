package pro.stuermer.bookii.common.usb.model

data class DeviceInfo(
    val manufacture: String? = null,
    val model: String? = null,
    val deviceVersion: String? = null,
    val deviceSerial: String? = null,
    val storageInfo: StorageInfo? = null
)
