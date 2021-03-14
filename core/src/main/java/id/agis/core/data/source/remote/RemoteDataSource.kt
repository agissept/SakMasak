package id.agis.core.data.source.remote

import android.util.Log
import id.agis.core.data.source.remote.network.ApiResponse
import id.agis.core.data.source.remote.network.ApiService
import id.agis.core.data.source.remote.response.ReceiptResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getListReceipt(): Flow<ApiResponse<List<ReceiptResponse>>> {
        return flow {
            try {
                val response = apiService.getListReceipt()
                val dataArray = response.listReceipt
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.listReceipt))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }}