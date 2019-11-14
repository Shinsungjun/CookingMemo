package kau.holyjoon.cookingmemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.choice_ingredient_main.*
import kotlinx.android.synthetic.main.plus_popup.*

class Choice_ingredient_Main : FragmentActivity(){  //Fragment를 포함하는 Activity


    companion object {  //static 의 기능을 함. fragment에서 가져다가 사용하기 위해 Main에 선언!
        /*var ingredients = arrayOf(
            Ingredient(
                "milk",
                "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/milk.jpg?alt=media&token=61a05de3-aec1-41dd-9697-c3045fbc30dc"
            ),
            Ingredient(
                "bread",
                "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/bread.jpg?alt=media&token=835efe50-ff93-41ef-9177-8518e372dc3c"
            ),
            Ingredient(
                "egg",
                "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/egg.jpg?alt=media&token=99254b1a-aac7-4c66-888e-c5df58d3a706"
            ),
            Ingredient(
                "sugar",
                "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/sugar.jpg?alt=media&token=81401354-4a05-4d6e-86e4-e51f953c94bb"
            )
        )*/
        var ingredients = ArrayList<Ingredient>()
        var resultitems =  ArrayList<Ingredient?>()//선택한 재료들이 들어가는 리스트 사이즈 = 50

    }
    //val resultAdapter = Result_Adapter(this, resultitems)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choice_ingredient_main)


        val fragmentitem = Choice_Ingredient_Item()
        val fragmentrefri = Choice_Ingredient_refri()
        /*ingredients = arrayOf(Ingredient("milk", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/milk.jpg?alt=media&token=61a05de3-aec1-41dd-9697-c3045fbc30dc"),
            Ingredient("bread", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/bread.jpg?alt=media&token=835efe50-ff93-41ef-9177-8518e372dc3c"),
            Ingredient("egg", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/egg.jpg?alt=media&token=99254b1a-aac7-4c66-888e-c5df58d3a706"),
            Ingredient("sugar", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/sugar.jpg?alt=media&token=81401354-4a05-4d6e-86e4-e51f953c94bb")
        ) //새로 OnCreate 될때만 ingredients가 제대로 된 갑슬 가지게. 클릭되면 사진이 사라지니까!*/

        supportFragmentManager.beginTransaction()
            .replace(R.id.view_item,fragmentitem)
            .commit()
        choice_bt_item.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.view_item,fragmentitem)
                .commit()

        }

        choice_bt_refri.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .replace(R.id.view_item,fragmentrefri)
                .commit()
          //  resultAdapter.notifyDataSetChanged()
        }
        var positions : Int?
       // val gridView : GridView = findViewById(R.id.grid_result_inmain)
        //gridView.adapter = resultAdapter
//        gridView.setOnItemClickListener {
//                parent, itemView, position, id ->
//            positions = position
//            resultAdapter.notifyDataSetChanged()
//        }
        choice_bt_add.setOnClickListener{            //추가된 재료들을 intent로 넘겨줘야 함
            val resultIntent = Intent(this, PlusActivity::class.java)
            resultIntent.putExtra("back", resultitems)
            setResult(1,resultIntent)
            finish()
            resultitems.clear()
        }
        choice_bt_cancel.setOnClickListener{
            finish()
            resultitems.clear()
        }
        bt_additem_db.setOnClickListener {
            val intent = Intent(this, Db_AddActivity::class.java)
            startActivityForResult(intent,1)
        }
        val resultingredient = intent.extras?.getParcelableArrayList<Ingredient?>("back")   //intent 받아오는 부분
        if (resultingredient != null) {
            for(i in 0 until resultingredient.size) {
                resultitems.add(resultingredient[i])
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}