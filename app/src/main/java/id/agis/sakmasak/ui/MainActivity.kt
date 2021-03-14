package id.agis.sakmasak.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.agis.core.data.source.Resource
import id.agis.sakmasak.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        observeListRecipe()
    }

    private fun observeListRecipe() {
        viewModel.listRecipe.observe(this, {
            if (it.data != null) {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressCircular.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        adapter.setItem(it.data!!)
                        binding.progressCircular.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun initRecyclerView() {
        adapter = RecipeAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

}