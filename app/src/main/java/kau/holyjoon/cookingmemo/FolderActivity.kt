package kau.holyjoon.cookingmemo

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import android.view.Window
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.folder_item.*
import kotlinx.android.synthetic.main.folder_popup.*
import android.widget.Toast.makeText as makeText1

class FolderActivity : AppCompatActivity() {
    var folderList = ArrayList<Folder>()
    var foldername:String = ""
    val folderAdapter = FolderAdapter(this, folderList)
//    { Folder ->
//        if (Folder.name != null) {
//            foldername = Folder.name!!
//            if (Folder.check == false)
//                Folder.check = true
//            if (Folder.check == true)
//                Folder.check = false
//            Toast.makeText(this, "${Folder.check}", Toast.LENGTH_SHORT).show()
//
//        }
//    }

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

        folderList.add(0,Folder("한식",true))
        folderList.add(1,Folder("중식",false))
        folderList.add(2,Folder("양식",false))
        folderAdapter.notifyDataSetChanged()


        aboutview()
    }
    private fun aboutview() {
        bt_folder_ok.setOnClickListener {
            var folderintent = Intent(this, EditActivity::class.java)
            folderintent.putExtra("folder", foldername)
            Toast.makeText(this, "${foldername}", Toast.LENGTH_SHORT).show()
            setResult(4)
            finish()
        }

        folderAdapter.setItemClickListener( object : FolderAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {

                foldername = folderList[position].name
                if (folderList[position].check == false)
                    folderList[position].check = true
                if (folderList[position].check == true)
                    folderList[position].check = false
                Toast.makeText(this@FolderActivity, "${folderList[position].check}", Toast.LENGTH_SHORT).show()

            }
        })




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

