package id.agis.sakmasak.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.agis.core.domain.model.RecipeItem
import id.agis.sakmasak.databinding.ItemTodayPickBinding
import id.agis.sakmasak.ui.detail.DetailActivity

class TodayPicksAdapter : RecyclerView.Adapter<TodayPicksAdapter.ViewHolder>() {
    private val listTodayPicks = mutableListOf<RecipeItem>()

    fun setItem(listTodayPicks: List<RecipeItem>) {
        this.listTodayPicks.clear()
        this.listTodayPicks.addAll(listTodayPicks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemTodayPickBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = listTodayPicks[position]
        with(holder.binding) {
            ivTodayPick.load(recipe.thumb)
            tvTitle.text = recipe.title
            tvTimes.text = recipe.times
            root.setOnClickListener {
                val intent = Intent(it.context, DetailActivity::class.java ).apply {
                    putExtra(DetailActivity.EXTRA_RECIPE_KEY, recipe.key)
                }
                it.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount() = listTodayPicks.size

    inner class ViewHolder(val binding: ItemTodayPickBinding) :
        RecyclerView.ViewHolder(binding.root)
}