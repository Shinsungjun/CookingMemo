package kau.holyjoon.cookingmemo

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity

class ingredientMainActivity : FragmentActivity() {
    companion object {
        var resultList = arrayListOf<Ingredient>()
        var totalList = arrayListOf<Ingredient>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choice_ingredient)
        val pagerfragment = ingredientMainFragment()
        val searchfragment = ingredientSearchFragment()
        val editSearch = findViewById<EditText>(R.id.edit_ingre_search)
        editSearch.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.ingre_frame,searchfragment)
                .commit()
        }

        Toast.makeText(this, "재료 선택 후 백버튼을 눌러주세요! 길게 터치시 선택 취소입니다.", Toast.LENGTH_LONG).show()

        supportFragmentManager.beginTransaction()
            .replace(R.id.ingre_frame,pagerfragment)
            .commit()

    }


    override fun onBackPressed() {
        val resultIntent = Intent(this, PlusActivity::class.java)
        if(resultList.size != 0) {
            resultIntent.putExtra("back", resultList)
        }
        setResult(1,resultIntent)
        finish()
        resultList.clear()
        super.onBackPressed()
    }
}
