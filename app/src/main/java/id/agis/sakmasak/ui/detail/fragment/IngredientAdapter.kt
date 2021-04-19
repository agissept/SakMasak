package id.agis.sakmasak.ui.detail.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.agis.sakmasak.databinding.ItemIngredientBinding

class IngredientAdapter(private val listIngredient: List<String>) : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvItem.text = listIngredient[position]
    }

    override fun getItemCount(): Int {
        return listIngredient.size
    }

    inner class ViewHolder(val binding: ItemIngredientBinding) :
        RecyclerView.ViewHolder(binding.root)
}