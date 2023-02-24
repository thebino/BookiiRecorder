package pro.stuermer.bookii.common.usb.model

data class StorageInfo(
    val capacity: Long,
    val occupiedSpace: Long,
    val freeSpace: Long
)
