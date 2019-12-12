package kau.holyjoon.cookingmemo
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.db_additem.*


class Db_AddActivity : AppCompatActivity() {  //storage에 사진을 올리고 realtime database에 이름과 사진의 src를 저장할 수 있는 activity. 사용은 안함
    val storage : FirebaseStorage = FirebaseStorage.getInstance("gs://kau-mobile-cookingapp.appspot.com")
    val storageRef = storage.reference

    var photoUri : Uri? = null
    var uploadname : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.db_additem)
        val itemimage = findViewById<ImageView>(R.id.db_image)
        val editdbName = findViewById<EditText>(R.id.db_edit)
        val editdb2 = findViewById<EditText>(R.id.db_edit2)
        var downloadUri : Uri? = null
        bt_album_db.setOnClickListener {
            val user_p = User_Permission(this)
            user_p.checkPer()
            goToAlbum()
        }

        bt_cancel_pop.setOnClickListener {
            finish()
        }

        bt_add_db.setOnClickListener {  //사진, 이름을 넣고 add하면 firebase storage에 등록됨
            if(photoUri != null && editdbName.text != null) {
                uploadname = editdbName.text.toString()
                val riversRef = storageRef.child("${uploadname}.jpg")
                val uploadTask = riversRef.putFile(photoUri!!)
                uploadTask.addOnFailureListener {
                    Toast.makeText(this,"Fail",Toast.LENGTH_SHORT).show()
                }.addOnSuccessListener {
                    //Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
                }
                    .addOnProgressListener {  //progress 부분은 나중에 구현..
                    }
                val urlTask = uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            throw it
                        }
                    }
                    return@Continuation riversRef.downloadUrl
                }).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val downloadUri = task.result
                        postFirebaseDatabase(editdbName.text.toString(), downloadUri.toString())

                    } else {
                        // Handle failures
                        // ...
                    }
                }

                finish()
            }
            else if(photoUri != null) {
                Toast.makeText(this, "재료 명을 입력해주세요!", Toast.LENGTH_LONG).show()
            }
            else if(editdbName.text != null) {
                Toast.makeText(this, "사진을 등록해주세요!", Toast.LENGTH_LONG).show()
            }
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

    fun postFirebaseDatabase(name : String, source : String) {
        val mPostReference = FirebaseDatabase.getInstance().getReference().child("ingredient").child("etc").child("liquid")
        var childUpdates = hashMapOf<String, HashMap<String, String>>()
        val ingredient = Ingredient(name, source)
        var postValue = ingredient.toMap()
        //childUpdates.put("/ingredient/etc/liquid/",postValue)
        mPostReference.push().updateChildren(postValue as Map<String, Any>)
    }
}

