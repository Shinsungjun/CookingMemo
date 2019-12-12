package kau.holyjoon.cookingmemo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class ingredientMainFragment : Fragment() {  //ViewPager 기능이 있는 Fragment. Fragment 위에 ViewPager이 존재하고 해당 ViewPager에서 Fragment를 보여주는 방식

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.choice_ingredientfragment, container, false)
        val adapter = pagerAdapter(activity!!.supportFragmentManager)  //pagerAdapter선언
        val ingreViewPager = view.findViewById<ViewPager>(R.id.ingreViewPager)
        val ingre_tablayout = view.findViewById<TabLayout>(R.id.ingre_tablayout)
        ingreViewPager.adapter = adapter

        ingreViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
            }
        })
        ingre_tablayout.setupWithViewPager(ingreViewPager)   //tavlayout과 ViewPager을 붙임

        return view
    }
}
