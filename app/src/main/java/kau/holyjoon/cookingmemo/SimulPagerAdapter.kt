package kau.holyjoon.cookingmemo

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class SimulPagerAdapter(fm: FragmentManager)
    : FragmentStatePagerAdapter(fm) {
    val fragmentTitleList = arrayListOf<String>()
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

    fun addFragment(title : String, size : String,recipe : Recipe_item) {  //Fragment를 동적으로 추가하기 위한 함수
        list.add(SimulFragment(title, size, recipe))
        fragmentTitleList.add(title)
    }
}