package id.agis.sakmasak.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import id.agis.sakmasak.databinding.ActivityMainBinding
import id.agis.sakmasak.ui.home.loader.HomeLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding
    private val adapter = HomeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerViewAdapter()
        observeListRecipe()
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
                    this, "\uD83D\uDE28 Wooops ${it.error}", Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

}