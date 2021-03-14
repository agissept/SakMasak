package id.agis.core.data.source

import id.agis.core.data.source.remote.RemoteDataSource
import id.agis.core.data.source.remote.network.ApiResponse
import id.agis.core.data.source.remote.response.detailrecipe.DetailRecipeResponse
import id.agis.core.data.source.remote.response.ReceiptItemResponse
import id.agis.core.domain.model.DetailRecipe
import id.agis.core.domain.model.ReceiptItem
import id.agis.core.domain.model.toDomainModel
import id.agis.core.domain.repository.IReceiptRepository
import kotlinx.coroutines.flow.Flow

class ReceiptRepository(private val remoteDataSource: RemoteDataSource) : IReceiptRepository {
    override fun getListReceipt(): Flow<Resource<List<ReceiptItem>>> =
        object : NetworkBoundResource<List<ReceiptItem>, List<ReceiptItemResponse>>() {
            override fun convertDataToDomainModel(data: List<ReceiptItemResponse>): List<ReceiptItem> {
                return data.map {
                    it.toDomainModel()
                }
            }

            override suspend fun getDataFromNetwork(): Flow<ApiResponse<List<ReceiptItemResponse>>> =
                remoteDataSource.getListReceipt()

        }.asFlow()

    override fun getDetailRecipe(receiptKey: String): Flow<Resource<DetailRecipe>> =
        object : NetworkBoundResource<DetailRecipe, DetailRecipeResponse>() {
            override fun convertDataToDomainModel(data: DetailRecipeResponse): DetailRecipe =
                data.toDomainModel()


            override suspend fun getDataFromNetwork(): Flow<ApiResponse<DetailRecipeResponse>> =
                remoteDataSource.getDetailRecipe(receiptKey)


        }.asFlow()
}
