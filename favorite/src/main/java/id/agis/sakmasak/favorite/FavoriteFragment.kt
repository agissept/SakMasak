package id.agis.sakmasak.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.agis.sakmasak.favorite.databinding.FragmentFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

@Suppress("unused")
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val adapter = FavoriteAdapter()
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadKoinModules(favoriteModule)
    }

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
        viewModel.lisFavoriteRecipe.observe(viewLifecycleOwner, {
            if(it.isEmpty()){
                showEmptyFavoriteIllustration()
                binding.recyclerView.visibility = View.GONE
            }else{
                hideFavoriteIllustration()
                adapter.setItem(it)
                binding.recyclerView.visibility = View.VISIBLE
            }
        })

    }

    private fun showEmptyFavoriteIllustration(){
        binding.ivEmpty.visibility = View.VISIBLE
        binding.tvEmptyFavorite.visibility = View.VISIBLE
    }

    private fun hideFavoriteIllustration(){
        binding.ivEmpty.visibility = View.GONE
        binding.tvEmptyFavorite.visibility = View.GONE
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}