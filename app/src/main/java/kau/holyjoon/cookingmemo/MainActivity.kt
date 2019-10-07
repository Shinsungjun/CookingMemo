package kau.holyjoon.cookingmemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {  //내가 지금까지 만든 요리를 보여주는 홈화면

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        aboutView()


    }
    private fun aboutView(){
        bt_edit.setOnClickListener{ //메모버튼 눌렀을때
            openActivity()

        }
    }
    private fun openActivity(){
        val intent = Intent (this, EditActivity::class.java)
        startActivity(intent)
    }
}
