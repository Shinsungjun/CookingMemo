package kau.holyjoon.cookingmemo

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast
import android.R.id.message
import android.annotation.SuppressLint
import android.content.pm.PackageManager

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Build
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import kotlin.concurrent.timer


class ViewActivity : AppCompatActivity() {
    val cookname by lazy{intent.extras?.get("name") as String}
    val cookimg by lazy{intent.extras?.get("img") as String}
    val recipeList by lazy{intent.getParcelableArrayListExtra<Recipe_item>("recipeList")}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissions()
        }
        val user = User_Permission(this)
        user.checkPer()
        val mAdapter = ViewAdapter(this, recipeList) {recipe ->
        }

        var recipe : hRecipe? = null
        val ImgView = findViewById<ImageView>(R.id.view_image)


        val recipe_view = findViewById<RecyclerView>(R.id.viewlist)
        val lm = LinearLayoutManager(this) //레이아웃매니저 설정
        recipe_view.layoutManager = lm
        recipe_view.setHasFixedSize(true)
        recipe_view.adapter = mAdapter //Recipe_view는 recycleview의 id
        recipe_view.addItemDecoration(DividerItemDecoration(applicationContext, 1))//list에 구분선추가


        val ingredients = findViewById<TextView>(R.id.ingredients)

        var timer = findViewById<TextView>(R.id.text_timer)

        val name = findViewById<TextView>(R.id.text_cook)

        name.text = cookname
    }

    fun checkPermissions() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE),1050)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
        1050 -> {
            // If request is cancelled, the result arrays are empty.
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                // permission was granted, yay! Do the
                // contacts-related task you need to do.
            }
            else {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
            }
            return
        }

        // Add other 'when' lines to check for other
        // permissions this app might request.
        else -> {
            // Ignore all other requests.
        }
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



