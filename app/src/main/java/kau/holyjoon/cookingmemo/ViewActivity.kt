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
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import android.Manifest.permission
import android.Manifest.permission.READ_CONTACTS
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.Manifest.permission.READ_CONTACTS
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.util.jar.Manifest
import android.Manifest.permission.READ_CONTACTS
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class ViewActivity : AppCompatActivity() {

    var recipeList = ArrayList<Recipe_item>()
    val mAdapter = RecipeAdapter(this, recipeList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        var recipe : hRecipe? = null
        val recipe_view = findViewById<RecyclerView>(R.id.viewlist)
        val lm = LinearLayoutManager(this) //레이아웃매니저 설정
        recipe_view.layoutManager = lm
        recipe_view.setHasFixedSize(true)
        recipe_view.adapter = mAdapter //Recipe_view는 recycleview의 id
        recipe_view.addItemDecoration(DividerItemDecoration(applicationContext, 1))//list에 구분선추가

        val ingredients = findViewById<TextView>(R.id.ingredients)

        var timer = findViewById<TextView>(R.id.text_timer)

////        val recipelist  =
//            Intent().getParcelableArrayListExtra("recipe")
        val data: Intent = Intent()

//        val passedIntent = Intent()
//        processIntent(passedIntent)

        val name = findViewById<TextView>(R.id.text_cook)
        val view_img = findViewById<ImageView>(R.id.view_img)
        if(intent != null) {
            val resultintent = intent.extras?.getParcelable<hRecipe>("hrecipe")
            name.text = resultintent?.name

             //view_img.setImageURI(resultintent?.img?.toUri())   string
            //recipeList = resultintent!!.hrecipeList!!

            //Toast.makeText(this,"${resultintent?.name}",Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(this,"hello",Toast.LENGTH_LONG).show()
        }
    }

    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        val name = findViewById<TextView>(R.id.text_cook)
//        if(data != null) {
//            val resultintent = data.extras?.getParcelable<hRecipe>("hrecipe")
//            name.text = resultintent?.name
//            Toast.makeText(this,"${resultintent?.name}",Toast.LENGTH_LONG).show()
//        }
//        else {
//            Toast.makeText(this,"hello",Toast.LENGTH_LONG).show()
//        }





        //Toast.makeText(this, resultintent1.hrecipeList!![0].ingredient!![0]!!.name.toString(), Toast.LENGTH_LONG).show()



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


 //   }



