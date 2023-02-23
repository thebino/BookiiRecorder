package pro.stuermer.bookii

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import pro.stuermer.bookii.home.Home
import pro.stuermer.bookii.theme.ApplicationTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

const val USB_PERMISSION = "de.stuermerbenjamin.bookii.USB_PERMISSION"

/**
 * Activity to observe USB Ports for attached devices.
 * Also the required [USB_PERMISSION] is requested and handeled here.
 */
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()
    private lateinit var usbManager: UsbManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        usbManager = applicationContext.getSystemService(Context.USB_SERVICE) as UsbManager

        registerReceiver(usbPermissionReceiver, IntentFilter(USB_PERMISSION))

        setContent {
            ApplicationTheme {
                Home()
            }
        }
    }

    /**
     * when the app is in background and is opened by the system via intent eg. USB_DEVICE_ATTACHED
     */
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        this.intent = intent
    }

    override fun onResume() {
        super.onResume()

        val filterAttached = IntentFilter("android.hardware.usb.action.USB_DEVICE_ATTACHED")
        registerReceiver(usbDeviceAttachedReceiver, filterAttached)

        val filterDetached = IntentFilter("android.hardware.usb.action.USB_DEVICE_DETACHED")
        registerReceiver(usbDeviceDetachedReceiver, filterDetached)

        scanForAlreadyAttachedDevices()
    }

    override fun onPause() {
        viewModel.stopCommunication()
        unregisterReceiver(usbDeviceAttachedReceiver)
        unregisterReceiver(usbDeviceDetachedReceiver)

        super.onPause()
    }

    override fun onDestroy() {
        unregisterReceiver(usbPermissionReceiver)

        super.onDestroy()
    }

    private val usbPermissionReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (USB_PERMISSION == intent.action) {
                synchronized(this) {
                    val device: UsbDevice? = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE)

                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        Timber.v("USB Permission granted for device " + device?.productName + " on port " + device?.deviceName)
                        device?.let {
                            viewModel.startCommunication(it)
                        }
                    } else {
                        Timber.w("USB Permission denied!")
                    }
                }
            }
        }
    }

    private val usbDeviceAttachedReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val device: UsbDevice? =
                intent.getParcelableExtra<Parcelable>(UsbManager.EXTRA_DEVICE) as UsbDevice?
            Timber.i("USB Device attached '${device?.productName}' on port '${device?.deviceName}'")

            device?.let {
                if (usbManager.hasPermission(device)) {
                    Timber.i("USB permission for device already granted")
                    viewModel.startCommunication(device)
                } else {
                    Timber.v("request USB permission for device '${device.productName}' on port '${device.deviceName}'")
                    requestPermissionForDevice(applicationContext, device)
                }
            }
        }
    }

    private val usbDeviceDetachedReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            Timber.i("USB Device detached")
            viewModel.stopCommunication()
        }
    }

    /**
     * Scan if any usb device is already attached when the app is started.
     * Check permission and/or start communication with the device.
     */
    private fun scanForAlreadyAttachedDevices() {
        // iterate through all currently attached usb devices
        for ((deviceName, usbDevice) in usbManager.deviceList) {
            if (usbManager.hasPermission(usbDevice)) {
                Timber.v("Has permission for attached device $deviceName")
                viewModel.startCommunication(usbDevice)
            } else {
                Timber.v("Request permission for attached device $deviceName at port.")
                requestPermissionForDevice(applicationContext, usbDevice)
            }
        }
    }

    /**
     * request permission at runtime for a specific usb device.
     */
    private fun requestPermissionForDevice(context: Context, usbDevice: UsbDevice) {
        val permissionIntent =
            PendingIntent.getBroadcast(
                context,
                REQUEST_CODE_USB_PERMISSION,
                Intent(USB_PERMISSION),
                PendingIntent.FLAG_IMMUTABLE
            )

        usbManager.requestPermission(usbDevice, permissionIntent)
    }
}
const val REQUEST_CODE_USB_PERMISSION = 14
