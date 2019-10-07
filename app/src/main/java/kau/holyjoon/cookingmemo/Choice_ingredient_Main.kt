package kau.holyjoon.cookingmemo

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.choice_ingredient_main.*

class Choice_ingredient_Main : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choice_ingredient_main)

        btn1.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.view,Choice_Ingredient_Item())
                .commit()
        }
        btn2.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .replace(R.id.view,Choice_Ingredient_refri())
                .commit()
        }
        btn3.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .replace(R.id.view,Choice_Ingredient_result())
                .commit()
        }
    }
}