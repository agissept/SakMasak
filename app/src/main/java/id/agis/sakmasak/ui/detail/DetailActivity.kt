package id.agis.sakmasak.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import coil.load
import com.google.android.material.tabs.TabLayoutMediator
import id.agis.core.data.source.Resource
import id.agis.core.domain.model.DetailRecipe
import id.agis.core.domain.model.RecipeItem
import id.agis.sakmasak.R
import id.agis.sakmasak.databinding.ActivityDetailBinding
import id.agis.sakmasak.ui.detail.fragment.IngredientsFragment
import id.agis.sakmasak.ui.detail.fragment.StepsFragment
import id.agis.sakmasak.ui.detail.fragment.ViewPagerModel
import id.agis.sakmasak.utils.toast
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding
    private var recipeItem: RecipeItem? = null

    private val recipeKey by lazy { intent.getStringExtra(EXTRA_RECIPE_KEY) ?: "" }
    private val recipeImage by lazy { intent.getStringExtra(EXTRA_RECIPE_IMAGE_KEY) ?: "" }
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initStatusBarColor()
        observeDetailRecipe()
        initFavoriteButton()
        initBackButton()
    }

    private fun initTabLayout(listIngredients: List<String>, listSteps: List<String>) {
        val fragments = arrayOf(
            ViewPagerModel("Bahan-bahan", IngredientsFragment.newInstance(listIngredients)),
            ViewPagerModel("Cara Membuat", StepsFragment.newInstance(listSteps))
        )
        binding.viewPager.adapter = DetailViewPagerAdapter(this, fragments)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = fragments[position].title
        }.attach()
    }

    private fun initBackButton() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initStatusBarColor() {
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding.mainView.viewTreeObserver.addOnScrollChangedListener {
            val scrollY: Int = binding.mainView.scrollY
            if (scrollY > 900 && window.statusBarColor != Color.BLACK) {
                window.statusBarColor = Color.BLACK
            } else if (scrollY < 900 && window.statusBarColor != Color.TRANSPARENT) {
                window.statusBarColor = Color.TRANSPARENT
            }
        }
    }

    private fun initFavoriteButton() {
        viewModel.getFavoriteRecipeById(recipeKey)
        viewModel.recipe.observe(this, {
            if (it != null) {
                isFavorite = true
                changeButtonFavorite()
            }
        })
        binding.btnFavorite.setOnClickListener {
            recipeItem?.let {
                isFavorite = if (!isFavorite) {
                    viewModel.addRecipeToFavorite(it)
                    toast("Berhasil Dimasukkan Ke Favorite")
                    true
                } else {
                    viewModel.removeRecipeFromFavorite(it)
                    toast("Berhasil Dihapus Dari Favorite")
                    false
                }
                changeButtonFavorite()
            }
        }
    }

    private fun changeButtonFavorite() {
        if (isFavorite) {
            binding.btnFavorite.load(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.btnFavorite.load(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun observeDetailRecipe() {
        viewModel.getGetDetailRecipe(recipeKey).observe(this, {
            if (it.data != null) {
                when (it) {
                    is Resource.Loading -> {
                        binding.mainView.visibility = View.INVISIBLE
                        binding.progressCircular.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.mainView.visibility = View.VISIBLE
                        binding.progressCircular.visibility = View.GONE
                        it.data?.let { detailRecipe ->
                            showDataToUI(detailRecipe)
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })

    }

    private fun showDataToUI(recipe: DetailRecipe) {
        with(recipe) {
            binding.ivThumb.load(recipeImage)
            binding.tvTitle.text = title
            binding.tvServings.text = servings
            binding.tvDifficulty.text = difficulty
            binding.tvTimes.text = times
            initTabLayout(ingredient, step)
        }

        recipeItem = with(recipe) {
            return@with RecipeItem(
                title = title,
                thumb = recipeImage,
                key = recipeKey,
                times = times,
                portion = servings,
                difficulty = difficulty,
            )
        }
    }

    companion object {
        const val EXTRA_RECIPE_KEY = "extra_recipe_key"
        const val EXTRA_RECIPE_IMAGE_KEY = "extra_recipe_image_key"
    }
}