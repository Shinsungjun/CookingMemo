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
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View


//메모한 레시피를 보여주는 홈화면
class MainActivity : AppCompatActivity() {

    var hRecipeList = ArrayList<hRecipe>() //홈화면에 표시될 데이터 리스트
    var remindname : String? = null
    //홈화면에 recyclerview찍어낼 어댑터
    val hAdapter = MainAdapter(this, hRecipeList) { hRecipe -> //홈화면의 메모 클릭시
        val viewintent = Intent(this, ViewActivity::class.java) //뷰 화면 데이터 넘김
        viewintent.putExtra("name",hRecipe.name)
        viewintent.putExtra("img",hRecipe.img)
        println("이미지 소스 in Going Intent: ${hRecipe.img}")
        viewintent.putParcelableArrayListExtra("recipeList",hRecipe.hrecipeList)
        remindname = hRecipe.name
        startActivityForResult(viewintent,1555)  //View로 넘어가는 Intent 다시 넘어올때 1555의 requestcode면 해당 recipe가 변경점이 있을 때 변경함
    } //만든 어댑터를 설정해주는 작업

    var intent2 : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loadintent = Intent(this, LoadingActivity::class.java)
        startActivity(loadintent)

        val name = findViewById<TextView>(R.id.edit_cookname)

        val numberOfColumns = 2

        val gridview = findViewById<RecyclerView>(R.id.grid_view)
        gridview.adapter = hAdapter //어댑터 적용

        val Gm = GridLayoutManager(this, numberOfColumns) //레이아웃매니저 설정
        gridview.layoutManager = Gm as RecyclerView.LayoutManager?
        gridview.setHasFixedSize(true)
        if(hRecipeList.size!=0) //데이터가 없으면 화면에 메세지 보이기
            empty_text.visibility = INVISIBLE
        else if(hRecipeList.size==0)
            empty_text.visibility = VISIBLE
        aboutView()


    }

    private fun aboutView() {
        bt_edit.setOnClickListener {    //메모버튼 누르면
            openActivity()
        }
    }

    private fun openActivity() {  //Edit 창으로 넘어가는 코드
        val intent = Intent(this, EditActivity::class.java)
        startActivityForResult(intent, 1) //Edit화면으로 이동
    }

    private fun viewOpenActivity() {
        val viewintent = Intent(this, ViewActivity::class.java)
        startActivity(viewintent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val name = findViewById<TextView>(R.id.edit_cookname)

        if(requestCode == 1555) {  //View -> Main 으로 왔을 때 Main에 있는 data 업데이트
            for(i in 0 until hRecipeList.size) {
                if(hRecipeList[i].name == remindname){
                    val deleteintent = data?.extras?.get("delete") as String?
                    if(deleteintent == "delete"){
                        hRecipeList.removeAt(i)
                        break
                    }
                    if(data!= null) {
                        val resultintent = data.getStringExtra("name")
                        val resultintent1: ArrayList<Recipe_item>? =
                            data.getParcelableArrayListExtra("recipeList")
                        if(resultintent != null) {
                            hRecipeList[i].name = resultintent
                            hRecipeList[i].img = null  //수정필요!!
                            hRecipeList[i].hrecipeList = resultintent1
                            hAdapter.notifyDataSetChanged()
                            break
                        }
                    }
                }
            }
        }
        else {
            if (data != null) {
                val resultintent = data.getStringExtra("name")
                val resultintent1: ArrayList<Recipe_item>? =
                    data.getParcelableArrayListExtra("recipeList")
                val folder = data.getStringExtra("foldername")
                intent2 = data.extras?.get("img") as String?
                val item: hRecipe = hRecipe(name.text.toString(), "", folder, resultintent1)

                hRecipeList.add(hRecipe(resultintent, intent2,folder, resultintent1))
                Toast.makeText(this, "${item.folder}", Toast.LENGTH_LONG).show()
                println("이미지 소스 in Main Result Intent: ${intent2}")
                hAdapter.notifyDataSetChanged()
            }
        }
        if(hRecipeList.size!=0) //데이터가 없으면 화면에 메세지 보이기
            empty_text.visibility = INVISIBLE
        else if(hRecipeList.size==0)
            empty_text.visibility = VISIBLE
    }
}
