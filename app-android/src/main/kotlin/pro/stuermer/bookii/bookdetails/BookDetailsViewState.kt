package pro.stuermer.bookii.bookdetails

import androidx.compose.runtime.Immutable
import pro.stuermer.bookii.common.message.UiMessage

@Immutable
internal data class BookDetailsViewState(
    val isLoading: Boolean = false,
    val message: UiMessage? = null,
) {
    companion object {
        val Empty = BookDetailsViewState()
    }
}
