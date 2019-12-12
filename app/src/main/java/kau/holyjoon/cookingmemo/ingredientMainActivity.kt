package kau.holyjoon.cookingmemo

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity

class ingredientMainActivity : FragmentActivity() {
    companion object {  //static 기능, Activity와 해당 Activity위에 떠있는 Fragment간의 공통적인 데이터
        var resultList = arrayListOf<Ingredient>()
        var totalList = arrayListOf<Ingredient>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choice_ingredient)
        val pagerfragment = ingredientMainFragment()
        val searchfragment = ingredientSearchFragment()
        val editSearch = findViewById<EditText>(R.id.edit_ingre_search)
        editSearch.setOnClickListener {  //search 칸을 클릭하면 Fragment를 변환시키면서 SearchView를 보여주려고 했음.
            supportFragmentManager.beginTransaction()
                .replace(R.id.ingre_frame,searchfragment)
                .commit()
        }

        Toast.makeText(this, "재료 선택 후 백버튼을 눌러주세요!", Toast.LENGTH_LONG).show()

        supportFragmentManager.beginTransaction()  //Main 위에 Fragment로 띄움
            .replace(R.id.ingre_frame,pagerfragment)
            .commit()
        //pager이 존재하는 Fragment와 검색기능을 가진 Fragment를 만들어서 검색 기능을 추가하려고 했으나,
        //실력과 시간관계상 구현하지 못했음 .. 지금은 ViewPager이 있는 Fragment만 작동
    }


    override fun onBackPressed() {   //재료를 선택 후 backpress를 통해 선택한 재료를 popup창으로 데이터 전달
        val resultIntent = Intent(this, PlusActivity::class.java)
        if(resultList.size != 0) {  //선택한 재료가 있다면 intent로 넘김
            resultIntent.putExtra("back", resultList)
        }
        setResult(1,resultIntent)
        finish()
        resultList.clear()
        super.onBackPressed()
    }
}
