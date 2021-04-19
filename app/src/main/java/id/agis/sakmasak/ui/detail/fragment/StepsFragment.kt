package id.agis.sakmasak.ui.detail.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import id.agis.sakmasak.databinding.FragmentStepsBinding

class StepsFragment : Fragment() {

    private var _binding: FragmentStepsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentStepsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = StepsAdapter(listSteps)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    companion object {
        lateinit var listSteps: List<String>

        fun newInstance(listSteps: List<String>): StepsFragment {
            this.listSteps = listSteps
            return StepsFragment()
        }
    }
}