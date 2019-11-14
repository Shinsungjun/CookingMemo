package kau.holyjoon.cookingmemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridView
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {  //내가 지금까지 만든 요리를 보여주는 홈화면

    var hRecipeList: ArrayList<hRecipe> = arrayListOf(hRecipe("요리", ""), hRecipe("메모", ""))
    val hAdapter = GridAdapter(this, hRecipeList) { hRecipe ->
        Toast.makeText(this, "${hRecipe.name}", Toast.LENGTH_SHORT).show()
        viewOpenActivity()

    } //만든 어댑터를 설정해주는 작업

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mStorageRef: StorageReference;
        setContentView(R.layout.activity_main)

        val numberOfColumns: Int = 2

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
        startActivityForResult(viewintent, 2)
    }
}
