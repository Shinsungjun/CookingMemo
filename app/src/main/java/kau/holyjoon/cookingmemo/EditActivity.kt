package kau.holyjoon.cookingmemo


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.edit_main.*
import kotlinx.android.synthetic.main.recyclerview.*


class EditActivity : AppCompatActivity() {

    var recipeList = arrayListOf<Recipe_item>()//리사이클뷰에 들어갈 Array

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_main)

        val mAdapter = RecipeAdapter(this, recipeList) //만든 어댑터를 설정해주는 작업
        //Recipe_view.adapter = mAdapter //Recipe_view는 recycleview의 id

        val lm = LinearLayoutManager(this) //레이아웃매니저 설정
//        Recipe_view.layoutManager = lm   세줄 오류남 22,25,26
//        Recipe_view.setHasFixedSize(true)


        aboutView()

    }

    private fun aboutView(){
        bt_plus.setOnClickListener{ //메모버튼 눌렀을때
            openPlusActivity()
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
    }
}

