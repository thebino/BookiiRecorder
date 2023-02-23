package pro.stuermer.bookii.common.records.model

import java.util.UUID

data class Record(
    val identifier: UUID,
    val filePath: String,
    val fileSize: Long,
)
