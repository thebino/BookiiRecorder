package pro.stuermer.bookii.books.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pro.stuermer.bookii.books.R
import pro.stuermer.bookii.common.Layout
import pro.stuermer.bookii.common.composable.Cover
import pro.stuermer.bookii.common.composable.Header

@Composable
internal fun Book(
    title: String,
    description: String,
    ISBN: String,
    records: List<String>,
    cover: Painter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = modifier,
    ) {
        Column(Modifier.padding(Layout.bodyMargin)) {
            Header(
                title = title,
                modifier = Modifier.padding(bottom = 16.dp),
            )

            Row(Modifier.fillMaxWidth()) {
                if (cover != null) {
                    Cover(
                        title = title,
                        cover = cover,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .width(64.dp)
                            .aspectRatio(2 / 3f),
                    )
                }

//                Column(Modifier.align(Alignment.CenterVertically)) {
//                    Text(
//                        text = title,
//                        style = MaterialTheme.typography.labelMedium,
//                    )
//
//                    Spacer(Modifier.height(4.dp))
//
//                    Text(
//                        text = title
//                            ?: stringResource(R.string.book_title_fallback, 1),
//                        style = MaterialTheme.typography.bodyLarge,
//                    )
//                }
            }
        }
    }
}