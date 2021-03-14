package id.agis.core.domain.usecase

import id.agis.core.data.source.Resource
import id.agis.core.domain.model.ReceiptItem
import kotlinx.coroutines.flow.Flow

interface ReceiptUseCase{
    fun getListReceipt(): Flow<Resource<List<ReceiptItem>>>
}