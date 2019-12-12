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

//폴더 액티비티 FolderAdapter 사용
class FolderActivity : AppCompatActivity() {
    var folderList = arrayListOf(Folder("기본폴더",false),Folder("찌개/국",false),Folder("구이류",false),Folder("볶음류",false)) //기본 폴더구성
    var foldername = "기본폴더"//폴더이름 default
    val folderAdapter = FolderAdapter(this, folderList) //폴더 어댑터

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.folder_popup)

        val folderview = findViewById<RecyclerView>(R.id.folder_main)
        folderview.adapter = folderAdapter //어댑터 적용
        val lm = LinearLayoutManager(this) //레이아웃매니저 설정
        folderview.layoutManager = lm
        folderview.setHasFixedSize(true)

        folderAdapter.notifyDataSetChanged()

        aboutview()
    }
    private fun aboutview() {
        folderAdapter.setItemClickListener( object : FolderAdapter.ItemClickListener{ //폴더아이템 클릭이벤트
            override fun onClick(view: View, position: Int) {

                foldername = folderList[position].name
                if (folderList[position].check)
                    Toast.makeText(this@FolderActivity, "${folderList[position].name}", Toast.LENGTH_SHORT).show()
                    //이미 선택되어있는 폴더누르면 변화없음
                else {
                    for(i in 0 until folderList.size)
                        folderList[i].check=false
                    folderList[position].check = true
                    Toast.makeText(this@FolderActivity, "${folderList[position].name}", Toast.LENGTH_SHORT).show()
                    //선택되어있지 않은 폴더 누르면 나머지 false로 만든후 해당 폴더 체크
                }
                folderAdapter.notifyDataSetChanged()
            }
        })
        bt_folder_ok.setOnClickListener { //확인-> 데이터를 Edit으로 넘김
            var folderintent = Intent(this, EditActivity::class.java)
            folderintent.putExtra("folder", foldername)
            folderintent.putExtra("folderlist", folderList)
            setResult(4,folderintent)
            finish()
        }
        bt_folder_add.setOnClickListener() { //New버튼-> 새폴더 생성 액티비티 이동
            val intent = Intent(this, NewFolderActivity::class.java)
            startActivityForResult(intent, 2)
        }
    }
    //NewFolderActivity에서 데이터 받아옴
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data!=null) {
            folderList.add(Folder(data.getStringExtra("newfolder"), false))
            folderAdapter.notifyDataSetChanged()
        }

    }

}

