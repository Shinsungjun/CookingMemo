package kau.holyjoon.cookingmemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast
import android.R.id.message
import android.annotation.SuppressLint

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ViewActivity : AppCompatActivity() {

    val resultintent1:hRecipe? = null
    val mAdapter = RecipeAdapter(this,resultintent1!!.hrecipeList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)


        val name = findViewById<TextView>(R.id.text_cook)
        val ingredients = findViewById<TextView>(R.id.ingredients)

        var timer = findViewById<TextView>(R.id.text_timer)

////        val recipelist  =
//            Intent().getParcelableArrayListExtra("recipe")
        val data:Intent = Intent()

//        val passedIntent = Intent()
//        processIntent(passedIntent)

        val resultintent1:hRecipe = data.getParcelableExtra("hrecipe") as hRecipe
        val cookname = resultintent1.name
        name.text = cookname.toString()



        //Toast.makeText(this, resultintent1.hrecipeList!![0].ingredient!![0]!!.name.toString(), Toast.LENGTH_LONG).show()

    }

//    private fun processIntent(intent:Intent)
//    {
//
//        val result:hRecipe? = intent.getParcelableExtra("hrecipe")
//        val mAdapter = RecipeAdapter(this,result!!.hrecipeList!!)
//
//
//        val Viewlist = findViewById<RecyclerView>(R.id.viewlist)
//        val lm = LinearLayoutManager(this) //레이아웃매니저 설정
//        Viewlist.layoutManager = lm
//        Viewlist.setHasFixedSize(true)
//        Viewlist.adapter = mAdapter //Recipe_view는 recycleview의 id
//        Viewlist.addItemDecoration(DividerItemDecoration(applicationContext, 1))//list에 구분선추가
//
//        Toast.makeText(
//                applicationContext,
//                "got simpledata :${result.name.toString()}",
//                Toast.LENGTH_SHORT
//            ).show()
//        }







//        if(recipelist!=null) {
//            Toast.makeText(this, recipelist[0].ingredient!!.get(0)!!.name.toString(), Toast.LENGTH_SHORT)
//                .show()
//        }

        //Toast.makeText(this, ingredientlist?.get(0)?.name.toString(), Toast.LENGTH_SHORT)
         //   .show()


    }



