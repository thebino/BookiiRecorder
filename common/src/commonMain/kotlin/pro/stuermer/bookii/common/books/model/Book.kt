package pro.stuermer.bookii.common.books.model

import java.util.UUID
typealias RecordIdentifier = UUID

data class Book(
    val identifier: UUID,
    val title: String,
    val imagePath: String?,
    val records: List<RecordIdentifier>,
)
