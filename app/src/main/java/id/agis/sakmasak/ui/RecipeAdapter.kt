package id.agis.sakmasak.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.agis.core.domain.model.RecipeItem
import id.agis.sakmasak.databinding.ItemRecipeBinding

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
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
    }

    override fun getItemCount() = listRecipe.size

    inner class ViewHolder(val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root)
}