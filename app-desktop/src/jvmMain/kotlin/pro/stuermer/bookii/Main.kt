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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.awt.Desktop
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


/**
 * Entry-point for the application
 */
fun main() = application {
    val applicationState = rememberApplicationState()

    JBWindow(
        onCloseRequest = {
            exitApplication()
            exitProcess(0)
        },
        state = rememberWindowState(size = DpSize(900.dp, 700.dp)),
        visible = true,
        title = "Bookii Recorder",
        showTitle = true,
        theme = applicationState.theme,
        icon = null,
        resizable = true,
        enabled = true,
        focusable = true,
        alwaysOnTop = false,
        onPreviewKeyEvent = { true },
        onKeyEvent = { true },
        mainToolBar = {
            Row(Modifier.mainToolBarItem(Alignment.End)) {
                Tooltip("Open GitHub link in browser") {
                    ActionButton(
                        onClick = {
                            Desktop.getDesktop()
                                .browse(URI.create("https://github.com/thebino/BookiiRecorder"))
                        },
                        modifier = Modifier.size(40.dp),
                        shape = RectangleShape
                    ) {
                        Icon("icons/github.svg")
                    }
                }
                Tooltip("Switch between dark and light mode,\ncurrently is ${if (applicationState.isDark) "dark" else "light"} mode") {
                    ActionButton(
                        onClick = { applicationState.toggleTheme() },
                        modifier = Modifier.size(40.dp),
                        shape = RectangleShape
                    ) {
                        if (applicationState.isDark) {
                            Icon("icons/darkTheme.svg")
                        } else {
                            Icon("icons/lightTheme.svg")
                        }
                    }
                }
            }
        }
    ) {
        var selectedItem by remember { mutableStateOf(1) }
        Column(Modifier.fillMaxSize()) {
            // content
            Row(
                Modifier.weight(1f),
            ) {
                // toolbar
                Column(
                    Modifier.width(40.dp).padding(top = 4.dp).fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Tooltip("Bücher") {
                        ToolBarActionButton(
                            selected = selectedItem == 0,
                            onClick = { selectedItem = 0 },
                            modifier = Modifier.size(30.dp)
                        ) {
                            Icon(resource = "icons/music.svg")
                        }
                    }
                    Tooltip("Geräte") {
                        ToolBarActionButton(
                            selected = selectedItem == 1,
                            onClick = { selectedItem = 1 },
                            modifier = Modifier.size(30.dp)
                        ) {
                            // TODO: hide marker if no new device
                            Icon(
                                resource = "icons/pen.svg",
                                markerColor = LocalErrorAreaColors.current.text
                            )
                        }
                    }
                }

                // divider
                Spacer(Modifier.background(LocalAreaColors.current.startBorderColor).width(1.dp).fillMaxHeight())

                // content
                when (selectedItem) {
                    0 -> {
                        Label("Coming soon...")
                    }

                    else -> {
                        DebugScreen(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            // WindowStatusBar
            ApplicationStatusBar(
                modifier = Modifier,
                applicationState
            )
        }
    }
}
