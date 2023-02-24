@file:OptIn(ExperimentalFoundationApi::class)

package pro.stuermer.bookii

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.awt.Desktop
import androidx.compose.runtime.Composable
import java.net.URI
import kotlin.system.exitProcess
import org.jetbrains.jewel.themes.expui.desktop.window.JBWindow
import org.jetbrains.jewel.themes.expui.standalone.control.ActionButton
import org.jetbrains.jewel.themes.expui.standalone.control.CloseableTab
import org.jetbrains.jewel.themes.expui.standalone.control.ComboBox
import org.jetbrains.jewel.themes.expui.standalone.control.DropdownLink
import org.jetbrains.jewel.themes.expui.standalone.control.DropdownMenu
import org.jetbrains.jewel.themes.expui.standalone.control.DropdownMenuItem
import org.jetbrains.jewel.themes.expui.standalone.control.ExternalLink
import org.jetbrains.jewel.themes.expui.standalone.control.Icon
import org.jetbrains.jewel.themes.expui.standalone.control.Label
import org.jetbrains.jewel.themes.expui.standalone.control.Link
import org.jetbrains.jewel.themes.expui.standalone.control.OutlineButton
import org.jetbrains.jewel.themes.expui.standalone.control.PrimaryButton
import org.jetbrains.jewel.themes.expui.standalone.control.ProgressBar
import org.jetbrains.jewel.themes.expui.standalone.control.RadioButton
import org.jetbrains.jewel.themes.expui.standalone.control.SegmentedButton
import org.jetbrains.jewel.themes.expui.standalone.control.Tab
import org.jetbrains.jewel.themes.expui.standalone.control.TextArea
import org.jetbrains.jewel.themes.expui.standalone.control.TextField
import org.jetbrains.jewel.themes.expui.standalone.control.ToolBarActionButton
import org.jetbrains.jewel.themes.expui.standalone.control.Tooltip
import org.jetbrains.jewel.themes.expui.standalone.control.TriStateCheckbox
import org.jetbrains.jewel.themes.expui.standalone.style.LocalAreaColors
import org.jetbrains.jewel.themes.expui.standalone.style.LocalErrorAreaColors
import org.jetbrains.jewel.themes.expui.standalone.theme.DarkTheme
import org.jetbrains.jewel.themes.expui.standalone.theme.LightTheme


@Composable
fun DebugScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state = rememberWindowState()
        val debugState = remember {
            DebugState.applyContent(state)
        }

        // Device
        Label("Verbinde einen Bookii Stift via USB")

        // TODO: display usb device info


        // Audio records
        Tooltip("Starte eine neue Aufnahme") {
            PrimaryButton(
                onClick = {
                    // TODO: start record
                },
                modifier = Modifier.width(180.dp),
                enabled = true
            ) {
                Label("Aufnahme starten")
            }
        }





//                InfiniteProgressBar()

//        ProgressBar()

//        Row(Modifier.height(40.dp).selectableGroup()) {
//            var selected by remember { mutableStateOf(0) }
//            CloseableTab(selected == 0, {
//                selected = 0
//            }, {}, modifier = Modifier.fillMaxHeight()) {
//                Icon("icons/kotlin.svg")
//                Label("First.kt")
//            }
//            CloseableTab(selected == 1, {
//                selected = 1
//            }, {}, modifier = Modifier.fillMaxHeight()) {
//                Icon("icons/kotlin.svg")
//                Label("Second.kt")
//            }
//            CloseableTab(selected == 2, {
//                selected = 2
//            }, {}, modifier = Modifier.fillMaxHeight()) {
//                Icon("icons/kotlin.svg")
//                Label("Third.kt")
//            }
//        }
    }
}
