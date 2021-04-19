package id.agis.sakmasak.ui.home.explore

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import id.agis.core.domain.model.RecipeItem
import id.agis.sakmasak.databinding.ItemRecipeBinding
import id.agis.sakmasak.ui.detail.DetailActivity
import id.agis.sakmasak.ui.detail.DetailActivity.Companion.EXTRA_RECIPE_KEY
import id.agis.sakmasak.utils.takeMainContent

class ExploreAdapter : PagingDataAdapter<RecipeItem, ExploreAdapter.ViewHolder>(RECIPE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = getItem(position)
        if (recipe != null) {
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
                    }
                    startActivity(intent)
                }
            }
        }
    }

    inner class ViewHolder(val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val RECIPE_COMPARATOR = object : DiffUtil.ItemCallback<RecipeItem>() {
            override fun areItemsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
                return true
            }

            override fun areContentsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}