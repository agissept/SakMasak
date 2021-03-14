package id.agis.core.domain.repository

import id.agis.core.data.source.Resource
import id.agis.core.domain.model.DetailRecipe
import id.agis.core.domain.model.ReceiptItem
import kotlinx.coroutines.flow.Flow

interface IReceiptRepository {

    fun getListReceipt(): Flow<Resource<List<ReceiptItem>>>

    fun getDetailRecipe(receiptKey: String): Flow<Resource<DetailRecipe>>
}