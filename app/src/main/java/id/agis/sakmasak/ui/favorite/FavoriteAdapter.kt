package id.agis.sakmasak.ui.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.agis.core.domain.model.RecipeItem
import id.agis.sakmasak.databinding.ItemRecipeBinding
import id.agis.sakmasak.ui.detail.DetailActivity

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    private var listRecipe = mutableListOf<RecipeItem>()

    fun setList(listRecipe: List<RecipeItem>) {
        this.listRecipe.clear()
        this.listRecipe.addAll(listRecipe)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int) {
        val recipe = listRecipe[position]
        with(holder.binding) {
            tvTitle.text = recipe.title
            tvDifficulty.text = recipe.difficulty
            tvPortion.text = recipe.portion
            tvTimes.text = recipe.times
            ivThumb.load(recipe.thumb)
            root.setOnClickListener {
                val intent = Intent(it.context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_RECIPE_KEY, recipe.key)
                }
                it.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount() = listRecipe.size

    inner class ViewHolder(val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root)
}