package kau.holyjoon.cookingmemo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.edit_main.*
import androidx.recyclerview.widget.DividerItemDecoration
import java.io.ByteArrayOutputStream

class EditActivity() : AppCompatActivity() {
    var remindhowmake : String? = null
    var remindcooktime : Int? = null

    var recipeList = ArrayList<Recipe_item?>() //recyclerview에 들어갈 데이터리스트
    val mAdapter = RecipeAdapter(this, recipeList){recipeItem -> //Edit에서 각 단계 클릭했을때
        remindhowmake = recipeItem?.howmake
        remindcooktime = recipeItem?.cooktime
        val editintent = Intent(this, PlusActivity::class.java)
        val intentrecipe = Recipe_item(recipeItem?.ingredient,recipeItem?.howmake,recipeItem?.cooktime,recipeItem?.comment)
        editintent.putExtra("recipe", intentrecipe)
        startActivityForResult(editintent,1000)
        true
    }
    var cookname:String = ""
    var photoUri : Uri? = null
    var photo : Bitmap? = null
    var foldername: String? = "기본폴더"
    val intentname by lazy{intent?.extras?.get("name") as String?}
    val intentimg by lazy{intent?.extras?.get("img") as String?}
    val intentrecipeList by lazy{intent.getParcelableArrayListExtra<Recipe_item?>("recipeList")}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_main)

        val view = findViewById<View>(R.id.Recipe_view)
        val Recipeview = findViewById<RecyclerView>(R.id.Recipe_view)
        val lm = LinearLayoutManager(this) //레이아웃매니저 설정
        Recipeview.layoutManager = lm
        Recipeview.setHasFixedSize(true)
        Recipeview.adapter = mAdapter //Recipe_view는 recycleview의 id
        Recipeview.addItemDecoration(DividerItemDecoration(applicationContext, 1))//list에 구분선추가

        if(intentname != null) {
            edit_cookname.setText(intentname)
        }
        if(intentimg != null) {
            edit_imageView.setImageURI(intentimg!!.toUri())
        }
        if(intentrecipeList != null) {
            for(i in 0 until intentrecipeList!!.size){
                recipeList.add(intentrecipeList!![i])
            }
            println("${recipeList[0]?.comment}")
            mAdapter.notifyDataSetChanged()
        }
        val edit_imageView = findViewById<ImageView>(R.id.edit_imageView)
        edit_imageView.setOnClickListener { v: View? ->
            registerForContextMenu(v)
            openContextMenu(v)
        }
        aboutView()
        val gestureListener = MyGesture()
        val gesturedetector = GestureDetector(this,gestureListener)

        view.setOnTouchListener{v, event ->
            return@setOnTouchListener gesturedetector.onTouchEvent(event)
        }

    }
    inner class MyGesture : GestureDetector.OnGestureListener {

        override fun onShowPress(e: MotionEvent?) {
            println("showpress!!!!!!!!!!!!")
        }

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            println("싱글탭!")
            return false
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            println("플링!!")
            return false
        }

        @SuppressLint("RestrictedApi")
        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            if(e1?.action == MotionEvent.ACTION_DOWN) {
                println("액선 1아래로 스크롤!!!")
                println("e1:${e1.action}, e2:${e2?.action}, distanceX :${distanceX},distanceY :${distanceY}")
                if (recipeList.size > 2) {
                    if (distanceY > 0) {
                        bt_floating.visibility = View.INVISIBLE
                    }
                }
                if(distanceY <0) bt_floating.visibility = View.VISIBLE
            }
            return true
        }

        override fun onLongPress(e: MotionEvent?) {

        }

        override fun onDown(e: MotionEvent?): Boolean {
            println("터치!!")
            return false
        }
    }
    private fun aboutView() {
        bt_floating.setOnClickListener {
            //메모버튼 눌렀을때
            openPlusActivity()
        }
        bt_save.setOnClickListener {

            val name:String = edit_cookname.text.toString()
            val save_intent = Intent(this, MainActivity::class.java)
            save_intent.putParcelableArrayListExtra("recipeList", recipeList)
            save_intent.putExtra("name",name)
            save_intent.putExtra("foldername",foldername)
            if(photoUri != null) {
                save_intent.putExtra("img", photoUri.toString())
            }
            setResult(1, save_intent)
            finish()

        }

        bt_folder.setOnClickListener {
            openFolderActivity()
        }
    }

    private fun openPlusActivity() {
        val intent = Intent(this, PlusActivity::class.java)
        startActivityForResult(intent, 1)   //popup을 띄우는 requestcode = 1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1000) { //재료 고침
            for(i in 0 until recipeList.size) {
                if(recipeList[i]?.howmake == remindhowmake && recipeList[i]?.cooktime == remindcooktime){
                    val deleteintent = data?.extras?.get("delete") as String?
                    if(deleteintent == "delete"){
                        recipeList.removeAt(i)
                        break
                    }
                    if(data!= null) {
                        val resultintent = data.extras?.get("recipe") as Recipe_item?
                        if(resultintent != null) {
                            recipeList[i]?.howmake = resultintent.howmake
                            recipeList[i]?.cooktime = resultintent.cooktime
                            recipeList[i]?.ingredient = resultintent.ingredient
                            recipeList[i]?.comment = resultintent.comment
                            break
                        }
                    }
                }
            }
            mAdapter.notifyDataSetChanged()
        }
        if (requestCode == 1) {  //popup창에서 재료를 넘기는 requestCode
            if (data != null) {

                val resultintent1 = data.extras?.get("recipe") as Recipe_item

                recipeList.add(
                    Recipe_item(
                        resultintent1.ingredient,
                        resultintent1.howmake,
                        resultintent1.cooktime,
                        resultintent1.comment
                    )
                )
                mAdapter.notifyDataSetChanged()

            }

            mAdapter.notifyDataSetChanged()

        }
        if (requestCode == 2) {   //album에서 보내는 requestcode
            if (data != null) {
                photoUri = data.data
                photo = null  //Bitmap 초기화
                edit_imageView.setImageURI(photoUri)

            }
        }
        if (requestCode == 3) {
            if (data != null) {
                photo = data.extras?.get("data") as Bitmap
                photoUri = getImageUri(this, photo!!)
                edit_imageView.setImageURI(photoUri)

            }
        }
        if (requestCode == 4){
            if (data != null) {
                foldername = data.extras?.get("folder").toString()
            }
            else {
                foldername = "기본폴더"
            }

        }
    }

    private fun openFolderActivity() {
        val folderintent = Intent(this, FolderActivity::class.java)
        startActivityForResult(folderintent,4)
    }

    fun goToAlbum() {  //엘범에서 Intent를 통해 사진을 받아옴.
        val albumintent = Intent(Intent.ACTION_PICK)
        albumintent.setType(MediaStore.Images.Media.CONTENT_TYPE)
        startActivityForResult(albumintent,2)
    }

    fun goToCamera() {
        val cameraintent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraintent,3)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        val inflater = menuInflater
        inflater.inflate(R.menu.album_camera_contextmenu, menu)
    }

    override fun onContextItemSelected(item : MenuItem) : Boolean{
        if(item.itemId == R.id.album_contextmenu) {
            val user_p = User_Permission(this)
            user_p.checkPer()
            goToAlbum()
            return true
        }
        else if(item.itemId == R.id.camera_contextmenu) {
            val user_p = User_Permission(this)
            user_p.checkPer()
            goToCamera()
            return true
        }
        else if(item.itemId == R.id.cancel_contextmenu) {
            return true
        }
        return super.onContextItemSelected(item)
    }

    fun getImageUri(context : Context, inImage : Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context.contentResolver,
            inImage,"Title",null)
        return Uri.parse(path)
    }
}

