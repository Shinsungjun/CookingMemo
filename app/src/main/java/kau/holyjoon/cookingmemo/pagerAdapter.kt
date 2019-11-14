package kau.holyjoon.cookingmemo

import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

class pagerAdapter(fm: FragmentManager)
    : FragmentStatePagerAdapter(fm) {
    val fragmentTitleList = arrayListOf<String>("Famous","Fruit","Meat")
    val list = arrayListOf<Fragment>(FamousFragment(),
        FruitFragment(), MeatFragment())
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
}