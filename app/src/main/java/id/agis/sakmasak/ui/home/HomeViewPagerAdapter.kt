package id.agis.sakmasak.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.agis.sakmasak.ui.home.explore.ExploreFragment

class HomeViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return fragments[position].fragment
    }

    companion object {
        data class ViewPagerModel(val title: String, val fragment: Fragment)

        val fragments = arrayOf(
            ViewPagerModel("Explore", ExploreFragment()),
            ViewPagerModel(
                "Favorite",
                Class.forName("id.agis.sakmasak.favorite.FavoriteFragment")
                    .newInstance() as Fragment
            )
        )
    }
}