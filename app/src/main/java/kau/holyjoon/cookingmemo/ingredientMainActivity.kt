package kau.holyjoon.cookingmemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.choice_ingredient.*

class ingredientMainActivity : AppCompatActivity() {
    val adapter by lazy {pagerAdapter(supportFragmentManager)}
    companion object {
        var resultList = arrayListOf<Ingredient>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choice_ingredient)
        val ingre_bt_ok = findViewById<Button>(R.id.ingre_bt_ok)
        ingre_bt_ok.setOnClickListener {
            val resultIntent = Intent(this, PlusActivity::class.java)
            if(resultList.size != 0) {
                resultIntent.putExtra("back", resultList)
            }
            setResult(1,resultIntent)
            finish()
            resultList.clear()
        }
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
