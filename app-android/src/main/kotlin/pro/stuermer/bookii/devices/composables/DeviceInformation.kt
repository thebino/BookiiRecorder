package pro.stuermer.bookii.devices.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.stuermer.bookii.devices.R
import pro.stuermer.bookii.usb.model.DeviceInfo

@Composable
fun DeviceInformation(
    modifier: Modifier = Modifier,
    isConnected: Boolean,
    manufacture: String? = null,
    model: String? = null,
    deviceVersion: String? = null,
    deviceSerial: String? = null,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Column {
            Text(
                fontSize = 12.sp,
                lineHeight = 24.sp,
                text = stringResource(id = R.string.device_status_label)
            )
            Text(
                fontSize = 12.sp,
                lineHeight = 24.sp,
                text = stringResource(id = R.string.device_manufacturer_label)
            )
            Text(
                fontSize = 12.sp,
                lineHeight = 24.sp,
                text = stringResource(id = R.string.device_model_label)
            )
            Text(
                fontSize = 12.sp,
                lineHeight = 24.sp,
                text = stringResource(id = R.string.device_firmware_label)
            )
            Text(
                fontSize = 12.sp,
                lineHeight = 24.sp,
                text = stringResource(id = R.string.device_serial_label)
            )
        }
        Column(Modifier.padding(horizontal = 16.dp)) {
            if (isConnected) {
                Text(
                    modifier = Modifier.testTag("isConnected"),
                    text = "Connected",
                    fontSize = 12.sp,
                    lineHeight = 24.sp,
                    color = Color(0xFF4CAF50),
                    fontWeight = FontWeight.Bold
                )
            } else {
                Text(
                    modifier = Modifier.testTag("isDisconnected"),
                    text = "Disconnected",
                    fontSize = 12.sp,
                    lineHeight = 24.sp,
                    color = Color(0xFFC0362C),
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                maxLines = 1,
                fontSize = 12.sp,
                lineHeight = 24.sp,
                text = manufacture ?: "",
                fontWeight = FontWeight.Bold
            )
            Text(
                maxLines = 1,
                fontSize = 12.sp,
                lineHeight = 24.sp,
                text = model ?: "",
                fontWeight = FontWeight.Bold
            )
            Text(
                maxLines = 1,
                fontSize = 12.sp,
                lineHeight = 24.sp,
                text = deviceVersion ?: "",
                fontWeight = FontWeight.Bold
            )
            Text(
                maxLines = 1,
                fontSize = 12.sp,
                lineHeight = 24.sp,
                text = deviceSerial?.trim('0') ?: "",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
internal fun InformationPreview(@PreviewParameter(DeviceInfoProvider::class) info: DeviceInfo) {
    MaterialTheme {
        DeviceInformation(
            isConnected = true,
            manufacture = info.manufacture,
            model = info.model,
            deviceVersion = info.deviceVersion,
            deviceSerial = info.deviceSerial,
        )
    }
}

class DeviceInfoProvider : PreviewParameterProvider<DeviceInfo> {
    override val values = sequenceOf(
        DeviceInfo(
            manufacture = "",
            model = "",
            deviceVersion = "v1.11",
            deviceSerial = "61023450000000000",
        ), DeviceInfo(
            manufacture = null,
            model = null,
            deviceVersion = null,
            deviceSerial = null,
        )
    )
}
