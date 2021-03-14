package id.agis.core.data.source.remote.network

import id.agis.core.data.source.remote.response.ListReceiptResponse
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    suspend fun getListReceipt(): ListReceiptResponse
}
