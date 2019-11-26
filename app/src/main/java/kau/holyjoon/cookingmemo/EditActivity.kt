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
    val mAdapter = RecipeAdapter(this, recipeList)
    var cookname:String = ""
    var photoUri : Uri? = null
    var photo : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_main)

        val Recipeview = findViewById<RecyclerView>(R.id.Recipe_view)
        val lm = LinearLayoutManager(this) //레이아웃매니저 설정
        Recipeview.layoutManager = lm
        Recipeview.setHasFixedSize(true)
        Recipeview.adapter = mAdapter //Recipe_view는 recycleview의 id
        Recipeview.addItemDecoration(DividerItemDecoration(applicationContext, 1))//list에 구분선추가


        val edit_imageView = findViewById<ImageView>(R.id.edit_imageView)
        edit_imageView.setOnClickListener(View.OnClickListener { v: View? ->
            registerForContextMenu(v)
            openContextMenu(v)
        })
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

