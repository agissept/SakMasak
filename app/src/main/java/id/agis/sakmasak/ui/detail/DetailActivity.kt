package id.agis.sakmasak.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import id.agis.core.data.source.Resource
import id.agis.core.domain.model.DetailRecipe
import id.agis.core.domain.model.RecipeItem
import id.agis.sakmasak.R
import id.agis.sakmasak.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding
    private lateinit var ingredientAdapter: DetailAdapter
    private lateinit var stepAdapter: DetailAdapter
    private var recipeItem: RecipeItem? = null

    private val recipeKey by lazy { intent.getStringExtra(EXTRA_RECIPE_KEY) ?: "" }
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initStatusBarColor()
        initIngredientRecyclerView()
        initStepRecyclerView()
        observeDetailRecipe()
        initFavoriteButton()
        initBackButton()
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
                Toast.makeText(this, "black", Toast.LENGTH_SHORT).show()
            }else if(scrollY < 900 && window.statusBarColor != Color.TRANSPARENT){
                window.statusBarColor = Color.TRANSPARENT
                Toast.makeText(this, "white", Toast.LENGTH_SHORT).show()
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
                    true
                } else {
                    viewModel.removeRecipeFromFavorite(it)
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

    private fun initStepRecyclerView() {
        stepAdapter = DetailAdapter()
        binding.rvStep.adapter = ingredientAdapter
        binding.rvStep.layoutManager = LinearLayoutManager(this)
    }

    private fun initIngredientRecyclerView() {
        ingredientAdapter = DetailAdapter()
        binding.rvIngredient.adapter = ingredientAdapter
        binding.rvIngredient.layoutManager = LinearLayoutManager(this)
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
            binding.ivThumb.load(thumb)
            binding.tvTitle.text = title
            binding.tvServings.text = servings
            binding.tvDifficulty.text = difficulty
            binding.tvTimes.text = times
            ingredientAdapter.setItem(ingredient)
            stepAdapter.setItem(step)
        }

        recipeItem = with(recipe) {
            return@with RecipeItem(
                title = title,
                thumb = thumb ?: "",
                key = recipeKey,
                times = times,
                portion = servings,
                difficulty = difficulty,
            )
        }
    }

    companion object {
        const val EXTRA_RECIPE_KEY = "extra_recipe_key"
    }
}