package kau.holyjoon.cookingmemo


import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.edit_main.*
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.db_additem.*
import kotlinx.android.synthetic.main.folder_item.*
import java.io.ByteArrayOutputStream


class EditActivity() : AppCompatActivity() {

    var recipeList = ArrayList<Recipe_item>() //recyclerview에 들어갈 데이터리스트
    var resultarray = ArrayList<Ingredient?>()
    var ingredientarray = arrayListOf(ArrayList<Ingredient?>())
    //var recipeL = ArrayList<Recipe>()
    val mAdapter = RecipeAdapter(this, recipeList)
    var cookname:String = ""
    var photoUri : Uri? = null
    var photo : Bitmap? = null
    //만든 어댑터를 설정해주는 작업
    // 여러 Ingredient를 가지고 있는
    //Array<Ingredient> 를 받아서 출력해야함..

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_main)

        val Recipeview = findViewById<RecyclerView>(R.id.Recipe_view)
        val lm = LinearLayoutManager(this) //레이아웃매니저 설정
        Recipeview.layoutManager = lm
        Recipeview.setHasFixedSize(true)
        Recipeview.adapter = mAdapter //Recipe_view는 recycleview의 id
        Recipeview.addItemDecoration(DividerItemDecoration(applicationContext, 1))//list에 구분선추가

        val bt_image_load = findViewById<ImageButton>(R.id.bt_camera)
        registerForContextMenu(bt_image_load)

        val edit_imageView = findViewById<ImageView>(R.id.edit_imageView)

        aboutView()

    }

    private fun aboutView() {
        bt_plus.setOnClickListener {
            //메모버튼 눌렀을때
            openPlusActivity()
        }
        bt_save.setOnClickListener {

            val name:String = `edit_cookname`.text.toString()
            val save_intent = Intent(this, MainActivity::class.java)
            save_intent.putParcelableArrayListExtra("recipeList", recipeList)
            save_intent.putExtra("name",name)
            save_intent.putExtra("img", photoUri.toString())

            setResult(1, save_intent)
            finish()

        }
        /*bt_camera.setOnClickListener {
            val user_p = User_Permission(this)
            user_p.checkPer()
            goToAlbum()
        }*/

        bt_folder.setOnClickListener {
            openFolderActivity()
        }
    }

    private fun getData() {
        /*how = intent.getStringExtra("how")
        time = intent.getStringExtra("time")
        comment = intent.getStringExtra("comment")*/
    }

    private fun openPlusActivity() { //근데 dialog라서 Activity 취급이 아닌거같아
        /* val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.plus_popup,null)
        val dialoghow = dialogView.findViewById<EditText>(R.id.edit_how)
        val dialogtime = dialogView.findViewById<EditText>(R.id.edit_time)
        builder.setView(dialogView)
            .setPositiveButton("DONE") {dialogInterface, i ->
                if(dialoghow.text == null) {
                //조리 방법을 선택해주셔야 합니다.
                }
                //조건이 만족한다면 Recipe 객체를 만들어, 해당 객체를 넘김 ..?
                //아니면 굳이 그럴 필요 없고 그냥 EditActivity에 객체를 만들어서 값을 넘겨주면 될듯
            } //내생각엔 PopupActivity는 쓸모 없어 보이는거 같기도 함.. 안쓰는거같아 Dialog에서는
            .setNegativeButton("CANCEL") {dialogInterface, i ->

            }
            .show()*/
        //!!위의 dialog부르는건 activity가 아니라서 더 햇갈려가지고 그냥 Activity를 Dialog처럼 띄우는걸로 했음.!!
        val intent = Intent(this, PlusActivity::class.java)
        startActivityForResult(intent, 1)   //popup을 띄우는 requestcode = 1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {  //popup창에서 재료를 넘기는 requestCode
            if (data != null) {

                val resultintent1 = data.extras?.get("recipe") as Recipe_item
//            val ingredient = data.extras?.getParcelableArrayList<Ingredient?>("array")
//
//            if (resultintent1 != null && ingredient != null) {
//                //Toast.makeText(this, "${resultintent1.howmake}, ${ingredient[0]?.name}", Toast.LENGTH_LONG).show()
//                for (i in 0 until ingredient.size) {   //이렇게 배열을 받아야 정확하게 받아짐
//                    resultarray.add(ingredient[i])
//                }
//            }
//            recipeList.add(
//                Recipe_item(
//                    resultarray,
//                    resultintent1.howmake,
//                    resultintent1.cooktime,
//                    resultintent1.comment
//                ))
                recipeList.add(
                    Recipe_item(
                        resultintent1.ingredient,
                        resultintent1.howmake,
                        resultintent1.cooktime,
                        resultintent1.comment
                    )
                )
                mAdapter.notifyDataSetChanged()
                //Toast.makeText(this@EditActivity, "${resultintent1.ingredient!![0]!!.name},${resultintent1.ingredient!![1]!!.name}", Toast.LENGTH_LONG).show()
            }
//            recipeL.add(
//                Recipe(
//                    resultintent1.howmake,
//                    resultintent1.cooktime,
//                    resultintent1.comment
//                )
//            )
            mAdapter.notifyDataSetChanged()
            //Toast.makeText(this@EditActivity, "${resultintent1.ingredient!![0]!!.name},${resultintent1.ingredient!![1]!!.name}", Toast.LENGTH_LONG).show()
        }
        if (requestCode == 2) {   //album에서 보내는 requestcode
            if (data != null) {
                photoUri = data.data
                photo = null  //Bitmap 초기화
                edit_imageView.setImageURI(photoUri)
                // Toast.makeText(this,"album intent come",Toast.LENGTH_LONG).show()
            }
        }
        if (requestCode == 3) {   //album에서 보내는 requestcode
            if (data != null) {
                photo = data.extras?.get("data") as Bitmap
                photoUri = getImageUri(this, photo!!)
                edit_imageView.setImageURI(photoUri)
                //Toast.makeText(this,"camera intent come", Toast.LENGTH_LONG).show()
            }
        }
        if (requestCode == 4){
            if (data != null)
                Toast.makeText(
                    this,
                    data.extras?.get("folder").toString(),
                    Toast.LENGTH_LONG
                ).show()
    }



    }


    private fun openFolderActivity() {
        val folderintent = Intent(this, FolderActivity2::class.java)
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

