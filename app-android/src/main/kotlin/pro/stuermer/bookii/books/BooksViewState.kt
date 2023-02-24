package pro.stuermer.bookii.books

import androidx.compose.runtime.Immutable
import pro.stuermer.bookii.common.message.UiMessage

@Immutable
internal data class BooksViewState(
    val refreshing: Boolean = false,
    val message: UiMessage? = null,
) {
    companion object {
        val Empty = BooksViewState()
    }
}
