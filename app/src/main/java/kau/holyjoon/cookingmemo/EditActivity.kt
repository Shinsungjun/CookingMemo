package kau.holyjoon.cookingmemo


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.edit_main.*
import kotlinx.android.synthetic.main.recyclerview.*


class EditActivity : AppCompatActivity() {

        var recipeList = arrayListOf<Recipe_item>( //recyclerview에 들어갈 데이터리스트
        //Recipe_item("감자", "튀기기", "4", "바삭하게"),//임의의 데이터 넣어줌
        //Recipe_item("토스트", "굽기", "3", "노릇노릇")
    //얘네들도  Recipe_item("토스트", "굽기", "3", "노릇노릇") 의 첫 Param에 "토스트"가 아니고 여러 Ingredient를 가지고 있는
    //Array<Ingredient> 를 받아서 출력해야함..


    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_main)
        val mAdapter = RecipeAdapter(this, recipeList) //만든 어댑터를 설정해주는 작업
        Recipe_view.adapter = mAdapter //Recipe_view는 recycleview의 id

        val lm = LinearLayoutManager(this) //레이아웃매니저 설정
        Recipe_view.layoutManager = lm
        Recipe_view.setHasFixedSize(true)

        registerForContextMenu(Recipe_view)

        aboutView()

    }

    private fun aboutView(){
        bt_plus.setOnClickListener{ //메모버튼 눌렀을때
            openPlusActivity()
        }
        bt_save.setOnClickListener {
            Toast.makeText(this@EditActivity,"Save!",Toast.LENGTH_LONG).show()
        }
    }
    private fun getData(){
        /*how = intent.getStringExtra("how")
        time = intent.getStringExtra("time")
        comment = intent.getStringExtra("comment")*/
    }
    private fun openPlusActivity() { //근데 dialog라서 Activity 취급이 아닌거같아
       /* val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.plus_popup,null)
        val dialoghow = dialogView.findViewById<EditText>(R.id.edit_how)
        val dialogtime = dialogView.findViewById<EditText>(R.id.edit_time)
        builder.setView(dialogView)
            .setPositiveButton("DONE") {dialogInterface, i ->
                if(dialoghow.text == null) {
                //조리 방법을 선택해주셔야 합니다.
                }
                //조건이 만족한다면 Recipe 객체를 만들어, 해당 객체를 넘김 ..?
                //아니면 굳이 그럴 필요 없고 그냥 EditActivity에 객체를 만들어서 값을 넘겨주면 될듯
            } //내생각엔 PopupActivity는 쓸모 없어 보이는거 같기도 함.. 안쓰는거같아 Dialog에서는
            .setNegativeButton("CANCEL") {dialogInterface, i ->

            }
            .show()*/
        //!!위의 dialog부르는건 activity가 아니라서 더 햇갈려가지고 그냥 Activity를 Dialog처럼 띄우는걸로 했음.!!
        val intent = Intent (this, PlusActivity::class.java)
        startActivityForResult(intent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data!=null){
            val resultintent1 = data.extras?.get("recipe")as Recipe
            val ingredient = data.extras?.getParcelableArrayList<Ingredient?>("array")

            if(resultintent1 != null && ingredient != null) {
                Toast.makeText(this,"${resultintent1.howmake}, ${ingredient[0]?.name}", Toast.LENGTH_LONG).show()
            }
        }
    }
}

