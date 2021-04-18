package id.agis.sakmasak.ui.home.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.agis.sakmasak.databinding.FragmentFavoriteBinding
import id.agis.sakmasak.ui.home.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val adapter = FavoriteAdapter()
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerViewAdapter()
        observeListFavoriteRecipe()
    }

    private fun initRecyclerViewAdapter() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun observeListFavoriteRecipe() {
        viewModel.lisFavoriteRecipe.observe(this, {
            adapter.setItem(it)
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}