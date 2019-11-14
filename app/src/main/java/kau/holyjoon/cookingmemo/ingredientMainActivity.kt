package kau.holyjoon.cookingmemo

import android.os.Bundle
import android.widget.TableLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.choice_ingredient.*

class ingredientMainActivity : AppCompatActivity() {
    val adapter by lazy {pagerAdapter(supportFragmentManager)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choice_ingredient)
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
    }
}
