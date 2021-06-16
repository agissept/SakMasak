package id.agis.sakmasak.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import id.agis.core.domain.model.RecipeItem
import id.agis.sakmasak.databinding.ItemRecipeBinding
import id.agis.sakmasak.ui.detail.DetailActivity
import id.agis.sakmasak.ui.detail.DetailActivity.Companion.EXTRA_RECIPE_KEY
import id.agis.sakmasak.utils.takeMainContent

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    private val listFavorite = mutableListOf<RecipeItem>()

    fun setItem(listFavorite: List<RecipeItem>) {
        this.listFavorite.clear()
        this.listFavorite.addAll(listFavorite)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = listFavorite[position]

        with(holder.binding) {
            with(recipe) {
                tvTitle.text = title.takeMainContent()
                tvDifficulty.text = difficulty
                tvPortion.text = portion
                tvTimes.text = times
                ivThumb.load(thumb){
                    transformations(RoundedCornersTransformation(32f))
                }
            }
        }

        holder.itemView.setOnClickListener {
            with(holder.itemView.context) {
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra(EXTRA_RECIPE_KEY, recipe.key)
                    putExtra(DetailActivity.EXTRA_RECIPE_IMAGE_KEY, recipe.thumb)
                }
                startActivity(intent)
            }
        }

    }

    override fun getItemCount() = listFavorite.size

    inner class ViewHolder(val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root)
}