package kau.holyjoon.cookingmemo


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.edit_main.*


class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_main)

        aboutView()
        //RecyclerView 어댑터, 레이아웃 매니저 설정(하는중)
        /*var recipeList = arrayListOf<memo>()


        val memos = arrayOf(getData())
        //val list:ListView=findViewById(R.id.editlist)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,memos)
        val Adapter1 = listAdapter(this, recipeList)
        recycler1.adapter = Adapter1

        //list.adapter = adapter
        val lm = LinearLayoutManager(this)
        recycler1.layoutManager = lm
        recycler1.setHasFixedSize(true)

        var recipeList = arrayListOf<memo>(*/
        val memos = arrayOf(getData())
        //val list:ListView=findViewById(R.id.editlist)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,memos)

        //list.adapter = adapter
    }

    private fun aboutView(){
        bt_plus.setOnClickListener{ //메모버튼 눌렀을때
            openPlusActivity()
        }
    }
    private fun getData(){
        intent.getStringExtra("texthow")
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

