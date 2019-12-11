package kau.holyjoon.cookingmemo

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class SimulPagerAdapter(fm: FragmentManager)
    : FragmentStatePagerAdapter(fm) {
    val fragmentTitleList = arrayListOf<String>()  //tablayout에 띄울 tab
    val list = arrayListOf<SimulFragment>()
    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }

    fun addFragment(title : String, size : String,recipe : Recipe_item) {
        list.add(SimulFragment(title, size, recipe))
        fragmentTitleList.add(title)
    }
}