package id.agis.core

import id.agis.core.data.source.Resource
import id.agis.core.domain.model.ReceiptItem
import id.agis.core.domain.repository.IReceiptRepository
import id.agis.core.domain.usecase.ReceiptInteractor
import id.agis.core.domain.usecase.ReceiptUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetReceiptTest {

    @Mock
    private lateinit var receiptRepository: IReceiptRepository
    private lateinit var receiptUseCase: ReceiptUseCase
    private lateinit var dummyResource: Resource<List<ReceiptItem>>

    @Before
    fun setUp() {
        receiptUseCase = ReceiptInteractor(receiptRepository)

        dummyResource = Resource.Success(
            listOf(
                ReceiptItem(
                    title = "Resep Rendang Daging Sapi Paling Istimewa",
                    thumb = "https://www.masakapahariini.com/wp-content/uploads/2018/04/resep-rendang-daging-sapi-400x240.jpg",
                    key = "resep-rendang-daging-sapi",
                    times = "1jam 30mnt",
                    portion = "6 Porsi",
                    difficulty = "Level Chef Panji"
                ),
                ReceiptItem(
                    title = "Resep Lemper Ayam Bumbu Rendang, Camilan Tradisional yang Enak",
                    thumb = "https://www.masakapahariini.com/wp-content/uploads/2018/11/lemper-ayam-bumbu-rendang-MAHI-1-400x240.jpg",
                    key = "resep-lemper-ayam-bumbu-rendang",
                    times = "1jam 30mnt",
                    portion = "4 Porsi",
                    difficulty = "Cukup Rumit"
                )
            )
        )
    }

    @Test
    fun `when get list receipt is success`() = runBlocking {
        `when`(receiptRepository.getListReceipt()).thenReturn(flow {
            emit(dummyResource)
        })

        val listReceipt = receiptUseCase.getListReceipt().first().data
        assertEquals(dummyResource.data, listReceipt)
    }
}