package kau.holyjoon.cookingmemo

import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

class pagerAdapter(fm: FragmentManager)
    : FragmentStatePagerAdapter(fm) {
    val fragmentTitleList = arrayListOf<String>("인기","채소/과일","육류","수산물","곡물/견과류","양념/소스","가공/유제품","기타")
    val list = arrayListOf(FamousFragment(),FruitFragment(), MeatFragment(),SeafoodFragment(),GrainFragment(),SourceFragment(),YogurtFragment(),EtcFragment())
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