package id.agis.core.domain.model

import id.agis.core.data.source.remote.response.ReceiptItemResponse

data class ReceiptItem(
    val title: String,
    val thumb: String,
    val key: String,
    val times: String,
    val portion: String,
    val difficulty: String
)

fun ReceiptItemResponse.toDomainModel(): ReceiptItem {
    return ReceiptItem(
        title = title,
        thumb = thumb,
        key = key,
        times = times,
        portion = portion,
        difficulty = difficulty
    )
}