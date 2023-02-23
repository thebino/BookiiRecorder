package pro.stuermer.bookii.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import timber.log.Timber

@Composable
fun StorageInformation(
    modifier: Modifier = Modifier,
    capacity: Long,
    occupiedSpace: Long,
    freeSpace: Long,
) {
    var size by remember { mutableStateOf(IntSize.Zero) }

    Row(modifier = modifier
        .onSizeChanged {
            size = it
        }
        .background(Color.LightGray)
    ) {
        val freePercentage = freeSpace * 100 / capacity
        val occupiedPercentage = occupiedSpace * 100 / capacity

        val density = LocalDensity.current
        val freeWidth = with(density) {
            (freePercentage * size.width / 100).toInt().toDp()
        }

        val occupiedWidth = with(density) {
            (occupiedPercentage * size.width / 100).toInt().toDp()
        }

        Divider(
            modifier = Modifier.width(occupiedWidth), color = Color.DarkGray, thickness = 4.dp
        )
        Divider(
            modifier = Modifier.width(freeWidth), color = Color.Green, thickness = 4.dp
        )
    }
}
