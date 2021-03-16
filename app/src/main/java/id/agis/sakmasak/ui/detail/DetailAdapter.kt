package id.agis.sakmasak.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.agis.sakmasak.databinding.ItemStepBinding

class DetailAdapter : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {
    private val listItem = mutableListOf<String>()

    fun setItem(listItem: List<String>) {
        this.listItem.clear()
        this.listItem.addAll(listItem)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStepBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvItem.text = listItem[position]
    }

    override fun getItemCount() = listItem.size

    inner class ViewHolder(val binding: ItemStepBinding) :
        RecyclerView.ViewHolder(binding.root)
}