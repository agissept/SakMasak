package id.agis.sakmasak.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.agis.sakmasak.databinding.ActivityFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {
    private val adapter = FavoriteAdapter()
    lateinit var binding: ActivityFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        observeListFavoriteRecipe()
    }

    private fun observeListFavoriteRecipe() {
        viewModel.listFavoriteRecipe.observe(this, {
            adapter.setList(it)
        })
    }

    private fun initAdapter() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}