package kau.holyjoon.cookingmemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new_folder.*

class NewFolderActivity : AppCompatActivity() {  //새폴더만드는 액티비티

    val newFolder = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_folder)

        aboutview()
    }

    private fun aboutview(){
        bt_new_folder.setOnClickListener{ //완료 버튼누르면
            val name:String = new_folder_name.text.toString()

            //폴더 액티비티로 name 넘김
            val intent = Intent(this, FolderActivity::class.java)
            intent.putExtra("newfolder",name)
            setResult(2,intent)
            finish()
        }
    }


}
