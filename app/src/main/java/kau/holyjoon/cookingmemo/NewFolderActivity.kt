package kau.holyjoon.cookingmemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_folder.*

class NewFolderActivity : AppCompatActivity() {

    val newFolder = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_folder)

        aboutview()
    }

    private fun aboutview(){
        bt_new_folder.setOnClickListener{
            val name:String = new_folder_name.text.toString()


            val intent = Intent(this, FolderActivity2::class.java)
            intent.putExtra("newfolder",name)
            setResult(2,intent)
            finish()
        }
    }


}
