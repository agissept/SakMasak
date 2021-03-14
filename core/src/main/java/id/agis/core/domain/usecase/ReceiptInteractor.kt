package id.agis.core.domain.usecase

import id.agis.core.domain.repository.IReceiptRepository

class ReceiptInteractor(private val receiptRepository: IReceiptRepository) : ReceiptUseCase {

    override fun getListReceipt() = receiptRepository.getListReceipt()

    override fun getDetailRecipe(receiptKey: String) =
        receiptRepository.getDetailRecipe(receiptKey)
}