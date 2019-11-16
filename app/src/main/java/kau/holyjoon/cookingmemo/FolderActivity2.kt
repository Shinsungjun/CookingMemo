package kau.holyjoon.cookingmemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_new_folder.*
import kotlinx.android.synthetic.main.folder_popup.*

class FolderActivity2 : AppCompatActivity() {
    var folderList = arrayListOf<String>("양식","한식")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.folder_popup)

//        var name = intent.getStringExtra("newfolder")
        val folderAdapter = FolderAdapter(this, folderList)

        val folderview = findViewById<RecyclerView>(R.id.folder_view)
        folderview.adapter = folderAdapter
        val lm = LinearLayoutManager(this) //레이아웃매니저 설정
        folderview.layoutManager = lm
        folderview.setHasFixedSize(true)

        aboutview()


    }
    private fun aboutview(){

        bt_folder_add.setOnClickListener() {
            val intent = Intent(this,NewFolderActivity::class.java)
            startActivityForResult(intent,2)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Toast.makeText(this, data?.getStringExtra("newfolder"), Toast.LENGTH_SHORT).show()

    }

}
