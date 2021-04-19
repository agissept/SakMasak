package id.agis.sakmasak.ui.detail.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.agis.sakmasak.databinding.ItemStepBinding

class StepsAdapter(private val listSteps: List<String>) :
    RecyclerView.Adapter<StepsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemStepBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvItem.text = listSteps[position]
    }

    override fun getItemCount(): Int {
        return listSteps.size
    }

    inner class ViewHolder(val binding: ItemStepBinding) :
        RecyclerView.ViewHolder(binding.root)
}