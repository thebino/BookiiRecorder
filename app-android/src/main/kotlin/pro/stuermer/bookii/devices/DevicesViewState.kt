package pro.stuermer.bookii.devices

import androidx.compose.runtime.Immutable

@Immutable
internal data class DevicesViewState(
    val refreshing: Boolean = false,
    val message: String? = null,

    val isConnected: Boolean = false,

    // device info
    val manufacture: String? = null,
    val model: String? = null,
    val deviceFirmwareVersion: String? = null,
    val deviceSerial: String? = null,

    // storage info
    val capacity: Long? = null,
    val occupiedSpace: Long? = null,
    val freeSpace: Long? = null,
) {
    fun isStorageInfoAvailable(): Boolean {
        return capacity != null && occupiedSpace != null && freeSpace != null
    }

    companion object {
        val Empty = DevicesViewState()
    }
}
