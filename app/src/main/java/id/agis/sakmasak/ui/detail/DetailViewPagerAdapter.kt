package id.agis.sakmasak.ui.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.agis.sakmasak.ui.detail.fragment.IngredientsFragment
import id.agis.sakmasak.ui.detail.fragment.StepsFragment

class DetailViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return fragments[position].fragment
    }

    companion object {
        data class ViewPagerModel(val title: String, val fragment: Fragment)

        val fragments = arrayOf(
            ViewPagerModel("Bahan-bahan", IngredientsFragment()),
            ViewPagerModel("Cara Membuat", StepsFragment())
        )
    }
}