package kau.holyjoon.cookingmemo

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import androidx.core.view.isVisible
import android.widget.Toast.makeText as makeText1

//메모한 레시피를 보여주는 홈화면
class MainActivity : AppCompatActivity() {

    var hRecipeList = ArrayList<hRecipe>() //홈화면에 표시될 데이터 리스트
    var remindname : String? = null
    var gridview:RecyclerView? = null //홈화면에 표시될 view
    var hAdapter = MainAdapter(this, hRecipeList){hRecipe->  //홈화면에 recyclerview찍어낼 어댑터, 클릭이벤트
        viewintent!!.putExtra("name",hRecipe!!.name)
        viewintent!!.putExtra("img",hRecipe.img)
        println("이미지 소스 in Going Intent: ${hRecipe.img}")
        viewintent!!.putParcelableArrayListExtra("recipeList",hRecipe.hrecipeList)
        remindname = hRecipe.name
        startActivityForResult(viewintent,1555)  //View로 넘어가는 Intent 다시 넘어올때 1555의 requestcode면 해당 recipe가 변경점이 있을 때 변경함
    } //만든 어댑터를 설정해주는 작업
    var viewintent:Intent? = null //Viewer로 넘어갈 데이터 intent

    var intent2 : String? = null
    var isPageOpen:Boolean = false //메뉴바 오픈되있는가
    var translateLeftAnim:Animation? = null //왼쪽으로 슬라이드 Anim
    var translateRightAnim: Animation? = null //오른쪽으로 슬라이드 Anim
    var slidingPage01: ConstraintLayout? = null //메뉴바 레이아웃
    var folderList = arrayListOf(Folder("기본폴더",false),Folder("찌개/국",false),Folder("구이",false),Folder("볶음",false))
    val folderAdapter = FolderAdapter(this, folderList)//메뉴바에 폴더 리스트 찍는 Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mStorageRef: StorageReference;
        setContentView(R.layout.activity_main)
        val loadintent = Intent(this, LoadingActivity::class.java)
        startActivity(loadintent) //메인 실행시 로딩화면 먼저 호출
        viewintent = Intent(this, ViewActivity::class.java) //뷰 화면 데이터 넘김

        val text = findViewById<TextView>(R.id.empty_text) //데이터가 없으면 보여줄 텍스트
        var name = findViewById<TextView>(R.id.edit_cookname) //Title text
        val folderview = findViewById<RecyclerView>(R.id.folder_main)
        val numberOfColumns = 2 //한줄에 두개씩 찍히도록
        slidingPage01 = findViewById(R.id.slidingPage01) //메뉴바 레이아웃

        gridview = findViewById<RecyclerView>(R.id.grid_view) //홈화면에 찍어낼 view
        gridview!!.adapter = hAdapter //어댑터 적용

        val Gm = GridLayoutManager(this, numberOfColumns) //홈화면 레이아웃매니저 설정 - 그리드 형식
        gridview!!.layoutManager = Gm as RecyclerView.LayoutManager?
        gridview!!.setHasFixedSize(true)

        folderview.adapter = folderAdapter //폴더 어댑터 적용
        val lm = LinearLayoutManager(this) //폴더 레이아웃매니저 설정
        folderview.layoutManager = lm
        folderview.setHasFixedSize(true)

        if(hRecipeList.size!=0)
            empty_text.visibility = INVISIBLE
        else if(hRecipeList.size==0)  //데이터가 없으면 화면에 텍스트 보이기
            text.setVisibility(VISIBLE)

        //왼쪽,오른쪽으로 슬라이드 Animation
        translateLeftAnim = AnimationUtils.loadAnimation(this,R.anim.translate_left)
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        //애니메이션 리스너 설정
        var animationListener:SlidingPageAnimationListenen = SlidingPageAnimationListenen()

        translateLeftAnim!!.setAnimationListener(animationListener)
        translateRightAnim!!.setAnimationListener(animationListener)

        empty_text.visibility = VISIBLE

        hAdapter.notifyDataSetChanged()
        aboutView()
    }

    private fun aboutView() {
        bt_edit.setOnClickListener {   //메모버튼 누르면
            openActivity()
        }
        bt_menu.setOnClickListener { //메뉴버튼 누르면

                if (isPageOpen) {
                    //열려있으면 닫기
                    slidingPage01!!.startAnimation(translateLeftAnim);
                }
                else {
                    //닫혀있으면 열기
                    slidingPage01!!.setVisibility(VISIBLE);
                    slidingPage01!!.startAnimation(translateRightAnim);
                }
            }
        bt_menu_cancel.setOnClickListener { //메뉴버튼 닫기 누르면
            if (isPageOpen) {
                //열려있으면 닫기
                slidingPage01!!.startAnimation(translateLeftAnim)
            }
            else {
                //닫혀있으면 열기
                slidingPage01!!.setVisibility(VISIBLE);
                slidingPage01!!.startAnimation(translateRightAnim)
            }
        }
        //폴더 아이템 클릭시
        folderAdapter.setItemClickListener( object : FolderAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
                if(slidingPage01!!.isVisible) {
                    if (isPageOpen) {
                        slidingPage01!!.startAnimation(translateLeftAnim)
                    }
                    edit_cookname.text = folderList[position].name.toString() //title을 폴더이름으로 바꾸기
                }}
        })
    }

    private fun openActivity() {  //Edit 창으로 넘어가는 코드
        val intent = Intent(this, EditActivity::class.java)
        startActivityForResult(intent, 1) //Edit화면으로 이동
    }
    private fun viewOpenActivity() { //view로 넘어가는 코드
        val viewintent = Intent(this, ViewActivity::class.java)
        startActivity(viewintent)
    }

    //애니메이션 리스너
    private inner class SlidingPageAnimationListenen():Animation.AnimationListener
    {

        override fun onAnimationEnd(p0: Animation?) {
            if(isPageOpen){
                slidingPage01!!.setVisibility(INVISIBLE)
                isPageOpen = false
            }
            //슬라이드 닫기->열기
            else{
                isPageOpen = true
            }
        }
        override fun onAnimationRepeat(p0: Animation?) {
        }

        override fun onAnimationStart(p0: Animation?) {
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val name = findViewById<TextView>(R.id.edit_cookname)

        if(requestCode == 1555) {  //View -> Main 으로 왔을 때 Main에 있는 data 업데이트
            for(i in 0 until hRecipeList.size) {
                if(hRecipeList[i].name == remindname){
                    val deleteintent = data?.extras?.get("delete") as String?
                    if(deleteintent == "delete"){
                        hRecipeList.removeAt(i)
                        break
                    }
                    if(data!= null) {
                        val resultintent = data.getStringExtra("name")
                        val resultintent1: ArrayList<Recipe_item>? =
                            data.getParcelableArrayListExtra("recipeList")
                        if(resultintent != null) {
                            hRecipeList[i].name = resultintent
                            hRecipeList[i].img = null  //수정필요!!
                            hRecipeList[i].hrecipeList = resultintent1
                            hAdapter.notifyDataSetChanged()
                            break
                        }
                        hAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
        else { //Edit -> Main 으로 왔을 때 requestcode = 1
            if (data != null) {
                val name = data.getStringExtra("name")
                val resultintent1: ArrayList<Recipe_item>? =
                    data.getParcelableArrayListExtra("recipeList")
                var folder = data.getStringExtra("foldername")
                if(folder==null)
                    folder = "기본폴더" //받아온 폴더가 없으면 default값
                intent2 = data.extras?.get("img") as String?
                println("이미지 소스 in Main Result Intent: ${intent2}")
                hRecipeList.add(hRecipe(name, intent2,folder, resultintent1))  //받아온 데이터로 hRecipeList에 추가
                hAdapter.notifyDataSetChanged()
            }
        }
        if(hRecipeList.size!=0) //데이터가 없으면 화면에 메세지 보이기
            empty_text.visibility = INVISIBLE
        else if(hRecipeList.size==0)
            empty_text.visibility = VISIBLE
    }
}
