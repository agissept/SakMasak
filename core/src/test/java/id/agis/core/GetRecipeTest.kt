package id.agis.core

import id.agis.core.data.source.Resource
import id.agis.core.domain.model.Author
import id.agis.core.domain.model.DetailRecipe
import id.agis.core.domain.repository.IRecipeRepository
import id.agis.core.domain.usecase.RecipeInteractor
import id.agis.core.domain.usecase.RecipeUseCase
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
class GetRecipeTest {

    @Mock
    private lateinit var recipeRepository: IRecipeRepository
    private lateinit var recipeUseCase: RecipeUseCase

    @Before
    fun setUp() {
        recipeUseCase = RecipeInteractor(recipeRepository)
    }

//    @Test
//    fun `when get list recipe is success`() = runBlocking {
//        val dummyResource = Resource.Success(
//            listOf(
//                RecipeItem(
//                    title = "Resep Rendang Daging Sapi Paling Istimewa",
//                    thumb = "https://www.masakapahariini.com/wp-content/uploads/2018/04/resep-rendang-daging-sapi-400x240.jpg",
//                    key = "resep-rendang-daging-sapi",
//                    times = "1jam 30mnt",
//                    portion = "6 Porsi",
//                    difficulty = "Level Chef Panji"
//                ),
//                RecipeItem(
//                    title = "Resep Lemper Ayam Bumbu Rendang, Camilan Tradisional yang Enak",
//                    thumb = "https://www.masakapahariini.com/wp-content/uploads/2018/11/lemper-ayam-bumbu-rendang-MAHI-1-400x240.jpg",
//                    key = "resep-lemper-ayam-bumbu-rendang",
//                    times = "1jam 30mnt",
//                    portion = "4 Porsi",
//                    difficulty = "Cukup Rumit"
//                )
//            )
//        )
//
//        `when`(recipeRepository.getListRecipe()).thenReturn(flow {
//            emit(dummyResource)
//        })
//
//        val listRecipe = recipeUseCase.getListRecipe().first().data
//        assertEquals(dummyResource.data, listRecipe)
//    }

    @Test
    fun `when get detail recipe is success`() = runBlocking {
        val dummyResource = Resource.Success(
            DetailRecipe(
                title = "Rendang Daging Sapi",
                thumb = "Gambar rendang",
                servings = "4 Porsi",
                times = "1jam 30mnt",
                difficulty = "Cukup Rumit",
                author = Author(user = "Asep", datePublished = "Juni 2020"),
                desc = "Ini adalaha makanan",
                ingredient = listOf("garam", "sapi"),
                step = listOf("direbus", "digoreng")
            )
        )

        val recipeKey = "resep-rendang-daging-sapi"

        `when`(recipeRepository.getDetailRecipe(recipeKey)).thenReturn(flow {
            emit(dummyResource)
        })

        val recipe = recipeUseCase.getDetailRecipe(recipeKey).first().data
        assertEquals(dummyResource.data, recipe)
    }
}