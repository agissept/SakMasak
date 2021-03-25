package id.agis.sakmasak.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import id.agis.sakmasak.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding
    private val recipeAdapter = HomeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        observeListRecipe()
    }

    private fun observeListRecipe() {
        // TODO fix loading
        binding.progressCircular.visibility = View.GONE
        lifecycleScope.launch {
            viewModel.listRecipe.collectLatest {
                recipeAdapter.submitData(it)
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = recipeAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

}