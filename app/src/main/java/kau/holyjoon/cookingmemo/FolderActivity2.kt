package kau.holyjoon.cookingmemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.folder_popup.*

class FolderActivity2 : AppCompatActivity() {
    var folderList = arrayListOf(Folder("한식"),Folder("중식"),Folder("양식"))
    var foldername:String = ""
    val folderAdapter = FolderAdapter(this, folderList){Folder->
        if(Folder.name!=null) {
            foldername = Folder.name!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.folder_popup)

//        var name = intent.getStringExtra("newfolder")

        val folderview = findViewById<RecyclerView>(R.id.folder_view)
        folderview.adapter = folderAdapter
        val lm = LinearLayoutManager(this) //레이아웃매니저 설정
        folderview.layoutManager = lm
        folderview.setHasFixedSize(true)


        //Toast.makeText(this,"${folderList[0]},${folderList[1]},${folderList[2]}", Toast.LENGTH_SHORT).show()



        aboutview()


    }
    private fun aboutview(){

        bt_folder_add.setOnClickListener {
            var folderintent = Intent(this, EditActivity::class.java)
            folderintent.putExtra("folder", foldername)
            Toast.makeText(this,"${foldername}", Toast.LENGTH_SHORT).show()
            setResult(4)
            finish()
        }

        bt_folder_add.setOnClickListener() {
            val intent = Intent(this,NewFolderActivity::class.java)
            startActivityForResult(intent,2)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data!=null)
        folderList.add(Folder(data.getStringExtra("newfolder")))
        folderAdapter.notifyDataSetChanged()
        Toast.makeText(this, data?.getStringExtra("newfolder"), Toast.LENGTH_SHORT).show()

    }

}
