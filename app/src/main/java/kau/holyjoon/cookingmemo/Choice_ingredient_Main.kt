package kau.holyjoon.cookingmemo

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import androidx.fragment.app.FragmentActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.choice_ingredient_main.*

class Choice_ingredient_Main : FragmentActivity() {  //Fragment를 포함하는 Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choice_ingredient_main)
        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef : DatabaseReference = database.getReference("test")
       supportFragmentManager.beginTransaction()
            .replace(R.id.view_item,Choice_Ingredient_Item())
            .commit()
        choice_bt_item.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.view_item,Choice_Ingredient_Item())
                .commit()
        }
        choice_bt_refri.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .replace(R.id.view_item,Choice_Ingredient_refri())
                .commit()
        }

        choice_bt_add.setOnClickListener{            //추가된 재료들을 intent로 넘겨줘야 함

        }
        choice_bt_cancel.setOnClickListener{
            finish()
        }

    }
}