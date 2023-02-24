package pro.stuermer.bookii.common.records.repository

import pro.stuermer.bookii.common.records.model.Record

interface RecordRepository {
    val records: List<Record>
}
