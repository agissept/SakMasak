package id.agis.sakmasak.ui.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.agis.sakmasak.ui.detail.fragment.ViewPagerModel

class DetailViewPagerAdapter(
    fragment: FragmentActivity,
    private val fragments: Array<ViewPagerModel>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return fragments[position].fragment
    }
}