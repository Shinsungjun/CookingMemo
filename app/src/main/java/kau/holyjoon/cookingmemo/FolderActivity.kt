package kau.holyjoon.cookingmemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.folder_popup.*
import android.widget.Toast.makeText as makeText1

class FolderActivity : AppCompatActivity() {
    var folderList = arrayListOf(Folder("기본폴더",false),Folder("찌개/국",false),Folder("구이",false),Folder("볶음",false))
    var foldername = "기본폴더"
    val folderAdapter = FolderAdapter(this, folderList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.folder_popup)

//        var name = intent.getStringExtra("newfolder")
        val folderview = findViewById<RecyclerView>(R.id.folder_main)
        folderview.adapter = folderAdapter
        val lm = LinearLayoutManager(this) //레이아웃매니저 설정
        folderview.layoutManager = lm
        folderview.setHasFixedSize(true)

        folderAdapter.notifyDataSetChanged()

        aboutview()
    }
    private fun aboutview() {
        folderAdapter.setItemClickListener( object : FolderAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {

                foldername = folderList[position].name
                if (folderList[position].check){
                    for(i in 0 until folderList.size)
                        folderList[i].check=false

                }
                else {
                    for(i in 0 until folderList.size)
                        folderList[i].check=false
                    folderList[position].check = true
                }
                Toast.makeText(this@FolderActivity, "${folderList[position].name}", Toast.LENGTH_SHORT).show()
                folderAdapter.notifyDataSetChanged()
            }
        })
        bt_folder_ok.setOnClickListener {
            var folderintent = Intent(this, EditActivity::class.java)
            folderintent.putExtra("folder", foldername)
            setResult(4,folderintent)
            finish()
        }
        bt_folder_add.setOnClickListener() {
            val intent = Intent(this, NewFolderActivity::class.java)
            startActivityForResult(intent, 2)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data!=null)
        folderList.add(Folder(data.getStringExtra("newfolder"),false))
        folderAdapter.notifyDataSetChanged()
        makeText1(this, data?.getStringExtra("newfolder"), Toast.LENGTH_SHORT).show()

    }

}

