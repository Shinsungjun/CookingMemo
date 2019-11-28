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
        val viewintent = Intent(this, ViewActivity::class.java)

        viewintent.putExtra("name",hRecipe.name)
        viewintent.putExtra("img",hRecipe.img)
        println("이미지 소스 in Going Intent: ${hRecipe.img}")
        viewintent.putParcelableArrayListExtra("recipeList",hRecipe.hrecipeList)
        startActivityForResult(viewintent,1)
    } //만든 어댑터를 설정해주는 작업

    var intent2 : String? = null
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
        val resultintent = data?.getStringExtra("name")


        if (data != null) {
            val resultintent1: ArrayList<Recipe_item>? =
                data.getParcelableArrayListExtra("recipeList")
            intent2 = data.extras?.get("img") as String
            val item: hRecipe = hRecipe(name.text.toString(), "", resultintent1)

            Toast.makeText(this, "${resultintent1!![0].comment}",Toast.LENGTH_SHORT).show()
            hRecipeList?.add(hRecipe(resultintent, intent2, resultintent1))
            println("이미지 소스 in Main Result Intent: ${intent2}")
            hAdapter.notifyDataSetChanged()
        }


        }
    }
