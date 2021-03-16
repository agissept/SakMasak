package id.agis.sakmasak.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import id.agis.core.data.source.Resource
import id.agis.sakmasak.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding
    private lateinit var ingredientAdapter: DetailAdapter
    private lateinit var stepAdapter: DetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initIngredientRecyclerView()
        initStepRecyclerView()

        val recipeKey = intent.getStringExtra(EXTRA_RECIPE_KEY)
        if (recipeKey !== null) {
            observeDetailRecipe(recipeKey)
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

    private fun observeDetailRecipe(recipeKey: String) {
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
                        //TODO remove double bang !!
                        with(it.data!!) {
                            binding.ivThumb.load(thumb)
                            binding.tvTitle.text = title
                            binding.tvServings.text = servings
                            binding.tvDifficulty.text = difficulty
                            binding.tvTimes.text = times
                            ingredientAdapter.setItem(ingredient)
                            stepAdapter.setItem(step)
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })

    }

    companion object {
        const val EXTRA_RECIPE_KEY = "extra_recipe_key"
    }
}