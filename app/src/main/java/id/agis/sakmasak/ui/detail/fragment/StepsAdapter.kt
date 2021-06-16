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
        val step = listSteps[position].removeNumber()
        holder.binding.tvItem.text = listSteps[position]

        with(holder.binding){
            tvNumber.text = (position + 1).toString()
            tvItem.text = step
        }
    }

    override fun getItemCount(): Int {
        return listSteps.size
    }

    override fun getItemViewType(position: Int) = if (position == listSteps.lastIndex) 1 else 0

    private fun String.removeNumber() = substring(this.indexOfFirst { it == ' ' }).trim()

    inner class ViewHolder(val binding: ItemStepBinding) : RecyclerView.ViewHolder(binding.root)
}