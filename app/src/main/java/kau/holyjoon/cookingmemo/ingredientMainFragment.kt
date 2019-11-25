package kau.holyjoon.cookingmemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class ingredientMainFragment : Fragment() {

    //val adapter by lazy {pagerAdapter(activity!!.supportFragmentManager)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.choice_ingredientfragment, container, false)
        val adapter = pagerAdapter(activity!!.supportFragmentManager)
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
        ingre_tablayout.setupWithViewPager(ingreViewPager)

        return view
    }
//
//    override fun onBackPressed() {
//        val resultIntent = Intent(this, PlusActivity::class.java)
//        if(resultList.size != 0) {
//            resultIntent.putExtra("back", resultList)
//        }
//        setResult(1,resultIntent)
//        finish()
//        resultList.clear()
//        super.onBackPressed()
//    }
}
