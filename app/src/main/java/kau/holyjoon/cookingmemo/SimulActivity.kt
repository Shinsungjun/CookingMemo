package kau.holyjoon.cookingmemo

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

class SimulActivity : AppCompatActivity() {  //simul 기능을 하는 Activity. 미완성
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
        for(i in 0 until recipeListintent.size) {   //ViewPager을 동적으로 만들어야하기 때문에 이런식으로 구현
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
                Log.d("Check","onPageScrolled : $position")
            }

            override fun onPageSelected(position: Int) {
                Log.d("Check", "onPageSeleted : $position")
            }
        })
    }
}