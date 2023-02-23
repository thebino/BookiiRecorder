package pro.stuermer.bookii

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.awt.Label
import org.jetbrains.jewel.themes.expui.standalone.style.LocalAreaColors
import org.jetbrains.jewel.themes.expui.standalone.style.LocalErrorAreaColors
import org.jetbrains.jewel.themes.expui.standalone.control.Label

/**
 * Bottom line to display the overall application status
 */
@Composable
fun ApplicationStatusBar(
    modifier: Modifier = Modifier, state: ApplicationState
) = Box(
    modifier.height(32.dp).fillMaxWidth()
) {
    Column {
        // bottom line
        Spacer(Modifier.background(LocalAreaColors.current.startBorderColor).height(1.dp).fillMaxWidth())

        // content
        Row(Modifier.fillMaxHeight().padding(4.dp)) {
            if (state.connectedDeviceCount > 0) {

                // Status text
                Label(
                    text = "${state.connectedDeviceCount} Booki device(s) connected",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                // Progress
                if (state.isTransferActive) {
                    Label(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = "Daten werden übertragen",
                    )
                    LinearProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterVertically).padding(horizontal = 8.dp)
                    )
                }
            } else {
                // Ready label
                Label(
                    text = state.statusText,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontSize = 12.sp
                )
            }
        }
    }
}
