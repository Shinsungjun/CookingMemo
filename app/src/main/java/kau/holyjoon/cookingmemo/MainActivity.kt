package kau.holyjoon.cookingmemo

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {  //내가 지금까지 만든 요리를 보여주는 홈화면

    //var hRecipeList: ArrayList<hRecipe>? = arrayListOf(hRecipe("요리", ""), hRecipe("메모", ""))
    var hRecipeList = ArrayList<hRecipe>()
    val hAdapter = GridAdapter(this, hRecipeList) { hRecipe ->
        var viewintent = Intent(this, ViewActivity::class.java)
        viewintent.putExtra("name",hRecipe.name)
        viewintent.putExtra("img",hRecipe.img)
        viewintent.putExtra("ingredient",hRecipe.ingredient)
        viewintent.putExtra("recipe",hRecipe.hrecipeList)
        startActivity(viewintent)


    } //만든 어댑터를 설정해주는 작업

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mStorageRef: StorageReference;
        setContentView(R.layout.activity_main)
        val text = findViewById<TextView>(R.id.empty_text)
        var name = findViewById<TextView>(R.id.edit_cookname)


//        if(hRecipeList.size!==0)
//            text.setVisibility(INVISIBLE)
//        else if(hRecipeList.size==0)
//            text.setVisibility(VISIBLE)


        val numberOfColumns = 2

        val gridview = findViewById<RecyclerView>(R.id.grid_view)
        gridview.adapter = hAdapter

        val Gm = GridLayoutManager(this, numberOfColumns) //레이아웃매니저 설정
        gridview.layoutManager = Gm as RecyclerView.LayoutManager?
        gridview.setHasFixedSize(true)


        aboutView()


    }

    private fun aboutView() {
        bt_edit.setOnClickListener {
            //메모버튼 눌렀을때
            openActivity()
        }
    }

    private fun openActivity() {
        val intent = Intent(this, EditActivity::class.java)
        startActivityForResult(intent, 1)
    }

    private fun viewOpenActivity() {
        val viewintent = Intent(this, ViewActivity::class.java)
        startActivity(viewintent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val name = findViewById<TextView>(R.id.edit_cookname)

        if (data != null) {
            val resultintent = data?.getStringExtra("name")
            val resultintent1: ArrayList<Recipe>? =
                Intent().getParcelableArrayListExtra("recipeList")
            val resultintent2: ArrayList<Ingredient?>?
                    = data.extras?.getParcelableArrayList("ingredient")
            val resultintent3 = data.extras?.get("Uri") as Uri?

            val item: hRecipe = hRecipe(name.text.toString(), resultintent3.toString(), resultintent2, resultintent1)
            //Toast.makeText(this, resultintent1?.get(0)?.howmake.toString(), Toast.LENGTH_SHORT)
              //  .show()

            //main_imgtest.setImageURI(resultintent3)

            Toast.makeText(this, data?.getStringExtra("name"), Toast.LENGTH_SHORT).show()
            hRecipeList?.add(hRecipe(resultintent, resultintent3.toString(), resultintent2, resultintent1))
            hAdapter.notifyDataSetChanged()
        }


        }
    }
