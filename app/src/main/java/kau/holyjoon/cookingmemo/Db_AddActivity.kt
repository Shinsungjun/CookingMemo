package kau.holyjoon.cookingmemo

import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.db_additem.*
import java.io.File


class Db_AddActivity : AppCompatActivity() {
    val storage : FirebaseStorage = FirebaseStorage.getInstance("gs://kau-mobile-cookingapp.appspot.com")
    val storageRef = storage.reference
    var photoUri : Uri? = null
    var uploadname : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.db_additem)
        val itemimage = findViewById<ImageView>(R.id.db_image)
        val editdb = findViewById<EditText>(R.id.db_edit)
        val dbtext = findViewById<TextView>(R.id.db_textv)
        val spaceRef = storageRef.child("images/space.jpg")


        bt_album_db.setOnClickListener {
            val user_p = User_Permission(this)
            user_p.checkPer()
            goToAlbum()
        }
        bt_cancel_pop.setOnClickListener {
            finish()
        }
        bt_add_db.setOnClickListener {  //사진, 이름을 넣고 add하면 firebase storage에 등록됨
            if(photoUri != null && editdb.text != null) {
                uploadname = editdb.text.toString()
                val riversRef = storageRef.child("${uploadname}.jpg")
                var uploadTask = riversRef.putFile(photoUri!!)
                uploadTask.addOnFailureListener {
                    Toast.makeText(this,"Fail",Toast.LENGTH_SHORT).show()
                }.addOnSuccessListener {
                    Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
                }
            }
            finish()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1) {  //from Album request code == 1
            if(data != null) {
                photoUri = data.data
                db_image.setImageURI(photoUri)
            }
        }
    }

    fun goToAlbum() {  //엘범에서 Intent를 통해 사진을 받아옴.
        val albumintent = Intent(Intent.ACTION_PICK)
        albumintent.setType(MediaStore.Images.Media.CONTENT_TYPE)
        startActivityForResult(albumintent,1)
    }
}

