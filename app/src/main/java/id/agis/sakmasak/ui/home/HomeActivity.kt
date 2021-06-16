package id.agis.sakmasak.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.tabs.TabLayoutMediator
import id.agis.core.data.source.Resource
import id.agis.sakmasak.R
import id.agis.sakmasak.databinding.ActivityMainBinding
import id.agis.sakmasak.ui.favorite.FavoriteActivity
import org.koin.android.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAvatar()
        initStatusBar()
        initTodayPicks()
        initChip()
    }

    private fun initChip() {
        binding.viewPager.adapter = HomeViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position->
            tab.text = HomeViewPagerAdapter.fragments[position].title
        }.attach()
    }

    private fun initTodayPicks() {
        viewModel.todayPicks.observe(this, {
            when (it) {
                is Resource.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    val adapter = TodayPicksAdapter()
                    binding.rvTodayPicks.adapter = adapter
                    adapter.setItem(it.data!!)
                    binding.progressCircular.visibility = View.INVISIBLE
                }
                is Resource.Error -> {

                }
            }
        })
    }

    private fun initStatusBar() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
    }

    private fun setAvatar() {
        binding.ivAvatar.load(R.drawable.john){
            transformations(CircleCropTransformation())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item.itemId == R.id.menu_favorite) {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
        return true
    }

}