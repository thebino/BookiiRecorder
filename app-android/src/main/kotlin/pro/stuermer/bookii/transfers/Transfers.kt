package pro.stuermer.bookii.transfers

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Transfers(
    navigateUp: () -> Unit,
) {
    Text(
        modifier = Modifier.statusBarsPadding(),
        text = "Transfers"
    )
}
