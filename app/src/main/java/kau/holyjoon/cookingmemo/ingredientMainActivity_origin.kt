package kau.holyjoon.cookingmemo
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import android.widget.TableLayout
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.viewpager.widget.ViewPager
//import com.google.android.material.tabs.TabLayout
//import kotlinx.android.synthetic.main.choice_ingredient.*
//
//class ingredientMainActivity_origin : AppCompatActivity() {
//    val adapter by lazy {pagerAdapter(supportFragmentManager)}
//    companion object {
//        var resultList = arrayListOf<Ingredient>()
//        var totalList = arrayListOf<Ingredient>()
//    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.choice_ingredient)
//        Toast.makeText(this, "재료 선택 후 백버튼을 눌러주세요! 길게 터치시 선택 취소입니다.", Toast.LENGTH_LONG).show()
//        ingreViewPager.adapter = adapter
//
//        ingreViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
//            override fun onPageScrollStateChanged(state: Int) {
//            }
//
//            override fun onPageScrolled(
//                position: Int,
//                positionOffset: Float,
//                positionOffsetPixels: Int
//            ) {
//            }
//
//            override fun onPageSelected(position: Int) {
//            }
//        })
//        ingre_tablayout.setupWithViewPager(ingreViewPager)
//    }
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
//}
