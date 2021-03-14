package id.agis.core.domain.model

import id.agis.core.data.source.remote.response.ReceiptResponse

data class Receipt(
    val title: String,
    val thumb: String,
    val key: String,
    val times: String,
    val portion: String,
    val difficulty: String
)

fun ReceiptResponse.toDomainModel(): Receipt {
    return Receipt(
        title = title,
        thumb = thumb,
        key = key,
        times = times,
        portion = portion,
        difficulty = difficulty
    )
}