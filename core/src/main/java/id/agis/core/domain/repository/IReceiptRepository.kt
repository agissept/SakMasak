package id.agis.core.domain.repository

import id.agis.core.data.source.Resource
import id.agis.core.domain.model.Receipt
import kotlinx.coroutines.flow.Flow

interface IReceiptRepository {

    fun getListReceipt(): Flow<Resource<List<Receipt>>>

}