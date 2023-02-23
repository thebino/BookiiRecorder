package pro.stuermer.bookii.common.usb.repository

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import net.samuelcampos.usbdrivedetector.USBDeviceDetectorManager
import net.samuelcampos.usbdrivedetector.USBStorageDevice
import net.samuelcampos.usbdrivedetector.events.DeviceEventType
import net.samuelcampos.usbdrivedetector.events.USBStorageEvent
import okio.FileSystem
import okio.Path
import okio.Path.Companion.toOkioPath
import okio.Path.Companion.toPath
import pro.stuermer.bookii.common.usb.model.DeviceInfo
import pro.stuermer.bookii.common.usb.model.StorageInfo

private const val POLLING_INTERVAL_MILLIS: Long = 1000
class UsbDeviceRepository: DeviceRepository {

    override val hasAccess: Boolean
        get() = true

    override fun requestAccess() {
        // TODO: check if needed for Windows or Linux
        // no-op for desktop
    }

    private val _connectedDevices = mutableStateMapOf<String, DeviceInfo>()
    override val connectedDevices: List<DeviceInfo>
        get() = _connectedDevices.values.toList()

    override val isTransferActive: Boolean
        // TODO: update state on transfer
        get() = false

    init {
        println("======= init =======")
        val driveDetector = USBDeviceDetectorManager(POLLING_INTERVAL_MILLIS)

        // Display all the USB storage devices currently connected
        driveDetector.removableDevices.forEach { device: USBStorageDevice? ->
            // TODO: filter for vendor & productID
            // vendorId = 3141, productId = 12801
            device?.let {
                // TODO: check root directory for specific files
                it.rootDirectory
            }
        }

        // Add an event listener to be notified when a USB storage device is connected or removed
        driveDetector.addDriveListener { usbStorageEvent: USBStorageEvent? ->
            println(usbStorageEvent)
            when (usbStorageEvent?.eventType) {
                DeviceEventType.CONNECTED -> {
                    println("CONNECTED")

                    usbStorageEvent.storageDevice.let { device ->
                        println("device: $device")

                        if (isBookiiPen(usbStorageEvent.storageDevice)) {
                            println("[+] uuid: ${device.uuid}, ${device.deviceName}, ${device.rootDirectory}")
                        }

                        _connectedDevices[device.rootDirectory.absolutePath] = DeviceInfo(
                            manufacture = "",
                            model = "",
                            deviceVersion = "",
                            deviceSerial = "",
                            storageInfo = StorageInfo(
                                capacity = 0L,
                                occupiedSpace = 0L,
                                freeSpace = 0L
                            )
                        )
                    }
                }
                DeviceEventType.REMOVED -> {
                    println("REMOVED")

                    usbStorageEvent.storageDevice.let { device ->
                        println("device: $device")

                        println("[-] uuid: ${device.uuid}, ${device.deviceName}, ${device.rootDirectory}")
                        _connectedDevices.remove(device.rootDirectory.absolutePath)
                    }
                }
                else -> {
                    // no-op
                }
            }
        }
    }

    /**
     * Check if the given usb storage device is a Bookii PEN
     */
    private fun isBookiiPen(usbStorageDevice: USBStorageDevice?): Boolean {
        // Check if all required root directories are there
        // TODO: maybe read the 'configure/settings.ini' instead
        usbStorageDevice?.let { device: USBStorageDevice ->
            val root = FileSystem.SYSTEM.list(device.rootDirectory.toOkioPath())
            val requiredSubDirectories = mutableListOf<Path>()


            val diyrecordPath = device.rootDirectory.absolutePath + Path.DIRECTORY_SEPARATOR + "diyrecord"
            val configurePath = device.rootDirectory.absolutePath + Path.DIRECTORY_SEPARATOR + "configure"
            val bookPath = device.rootDirectory.absolutePath + Path.DIRECTORY_SEPARATOR + "book"
            requiredSubDirectories.addAll(
                listOf(
                    diyrecordPath.toPath(),
                    configurePath.toPath(),
                    bookPath.toPath(),
                )
            )

            if (root.containsAll(requiredSubDirectories)) {
                return true
            }
        }

        return false
    }
}
