package id.agis.core.data.source

import id.agis.core.data.source.remote.RemoteDataSource
import id.agis.core.data.source.remote.network.ApiResponse
import id.agis.core.data.source.remote.response.ReceiptResponse
import id.agis.core.domain.model.Receipt
import id.agis.core.domain.model.toDomainModel
import id.agis.core.domain.repository.IReceiptRepository
import kotlinx.coroutines.flow.Flow

class ReceiptRepository(private val remoteDataSource: RemoteDataSource) : IReceiptRepository {
    override fun getListReceipt(): Flow<Resource<List<Receipt>>> =
        object : NetworkBoundResource<List<Receipt>, List<ReceiptResponse>>() {
            override fun convertDataToDomainModel(data: List<ReceiptResponse>): List<Receipt> {
                return data.map {
                    it.toDomainModel()
                }
            }

            override suspend fun getDataFromNetwork(): Flow<ApiResponse<List<ReceiptResponse>>> =
                remoteDataSource.getListReceipt()

        }.asFlow()
}
