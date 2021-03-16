package id.agis.sakmasak.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.agis.core.domain.model.RecipeItem
import id.agis.sakmasak.databinding.ItemRecipeBinding
import id.agis.sakmasak.ui.detail.DetailActivity
import id.agis.sakmasak.ui.detail.DetailActivity.Companion.EXTRA_RECIPE_KEY

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private val listRecipe = mutableListOf<RecipeItem>()

    fun setItem(listRecipe: List<RecipeItem>) {
        this.listRecipe.addAll(listRecipe)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = listRecipe[position]
        with(holder.binding) {
            with(recipe) {
                tvTitle.text = title
                tvDifficulty.text = difficulty
                tvPortion.text = portion
                tvTimes.text = times
                ivThumb.load(thumb)
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

    override fun getItemCount() = listRecipe.size

    inner class ViewHolder(val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root)
}