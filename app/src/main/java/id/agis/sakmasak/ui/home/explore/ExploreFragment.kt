package id.agis.sakmasak.ui.home.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import id.agis.sakmasak.databinding.FragmentExploreBinding
import id.agis.sakmasak.ui.home.HomeAdapter
import id.agis.sakmasak.ui.home.HomeViewModel
import id.agis.sakmasak.ui.home.loader.HomeLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class ExploreFragment : Fragment() {
    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    private val adapter = HomeAdapter()
    private val viewModel: HomeViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewAdapter()
        observeListRecipe()
    }

    private fun initRecyclerViewAdapter() {
        binding.recyclerView.adapter =
            adapter.withLoadStateHeaderAndFooter(
                header = HomeLoadStateAdapter { adapter.retry() },
                footer = HomeLoadStateAdapter { adapter.retry() }
            )
        adapter.addLoadStateListener { loadState ->
            binding.recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.progressCircular.isVisible = loadState.source.refresh is LoadState.Loading
            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error

            errorState?.let {
                Toast.makeText(
                    context, "\uD83D\uDE28 Wooops ${it.error}", Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun observeListRecipe() {
        // TODO fix loading
//        binding.progressCircular.visibility = View.GONE
        lifecycleScope.launch {
            viewModel.listRecipe.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}