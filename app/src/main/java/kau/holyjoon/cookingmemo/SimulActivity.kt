package kau.holyjoon.cookingmemo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_simul.*

class SimulActivity : AppCompatActivity() {
    val cooknameintent by lazy{intent.extras?.get("name") as String?}
    val recipeListintent by lazy{intent.getParcelableArrayListExtra<Recipe_item>("recipeList")}
    val adapter by lazy { SimulPagerAdapter(supportFragmentManager) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simul)
        val text_cooktitle = findViewById<TextView>(R.id.cook_title)
        text_cooktitle.text = cooknameintent
        val ingreViewPager = findViewById<ViewPager>(R.id.simul_pager)
        ingreViewPager.adapter = adapter
        for(i in 0 until recipeListintent.size) {
            adapter.addFragment(recipeListintent[i].howmake!!,recipeListintent[i])
            adapter.notifyDataSetChanged()
        }
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


    }
}