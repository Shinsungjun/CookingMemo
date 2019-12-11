package kau.holyjoon.cookingmemo

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_simul.*
import kotlinx.android.synthetic.main.choice_ingredientfragment.*

class SimulActivity : AppCompatActivity() {
    companion object {
        var timerun = 1
    }
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
            adapter.addFragment((i+1).toString(),recipeListintent.size.toString(),recipeListintent[i])
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
                Log.d("ITPANGPANG", "onPageScrolled : $position")
            }

            override fun onPageSelected(position: Int) {
                Log.d("ITPANGPANG", "onPageSeleted : $position")
            }
        })
    }
}