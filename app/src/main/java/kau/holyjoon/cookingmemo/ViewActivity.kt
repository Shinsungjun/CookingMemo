package kau.holyjoon.cookingmemo

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.pm.PackageManager
import android.os.Build
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_view.*


class ViewActivity : AppCompatActivity() {
    val cooknameintent by lazy{intent.extras?.get("name") as String?}
    val cookimgintent by lazy{intent.extras?.get("img") as String?}
    val recipeListintent by lazy{intent.getParcelableArrayListExtra<Recipe_item>("recipeList")}
    var recipeList = arrayListOf<Recipe_item>()
    val mAdapter = ViewAdapter(this, recipeList) {recipe ->
    }
    var cookname : String? = null
    var total_ingre : String? = null
    var total_time = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissions()
        }
        val text_ingre = findViewById<TextView>(R.id.text_ingredients)
        val name = findViewById<TextView>(R.id.text_cook)
        val user = User_Permission(this)
        user.checkPer()
        if(recipeListintent!= null) {
            for(i in 0 until recipeListintent.size) {
                recipeList.add(recipeListintent[i])
                total_time += recipeListintent[i].cooktime!!
                for(j in 0 until (recipeListintent[i].ingredient?.size ?: 0)) {
                    total_ingre.plus(recipeListintent[i].ingredient?.get(j)?.name)
                }
            }
            text_ingre.text = total_ingre
            mAdapter.notifyDataSetChanged()
        }
        if(cooknameintent != null) {
            cookname = cooknameintent
        }
        var recipe : hRecipe? = null
        val ImgView = findViewById<ImageView>(R.id.view_image)
        bt_view_edit.setOnClickListener {
            val editIntent = Intent(this, EditActivity::class.java)
            editIntent.putExtra("name",cookname)
            editIntent.putExtra("img",cookimgintent)
            editIntent.putParcelableArrayListExtra("recipeList",recipeList)
            startActivityForResult(editIntent,100)
        }

        val recipe_view = findViewById<RecyclerView>(R.id.viewlist)
        val lm = LinearLayoutManager(this) //레이아웃매니저 설정
        recipe_view.layoutManager = lm
        recipe_view.setHasFixedSize(true)
        recipe_view.adapter = mAdapter //Recipe_view는 recycleview의 id
        recipe_view.addItemDecoration(DividerItemDecoration(applicationContext, 1))//list에 구분선추가

        var timer = findViewById<TextView>(R.id.text_timer)
        timer.text = "${total_time/60} 분 ${total_time%60} 초"

        name.text = cookname

        bt_view_delete.setOnClickListener {  //삭제 버튼 .......... 다시 한번 더 물어봐야함 ..... ㅎㅎ

        }

        bt_start.setOnClickListener {
            val viewintent = Intent(this, SimulActivity::class.java) //뷰 화면 데이터 넘김
            viewintent.putExtra("name",cookname)
            viewintent.putParcelableArrayListExtra("recipeList",recipeList)
            startActivityForResult(viewintent,999)
        }
    }

    fun checkPermissions() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE),1050)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100) { //Edit에서 수정후 돌아옴
            if (data != null) {
                val nametext = findViewById<TextView>(R.id.text_cook)
                val resultintent = data.getStringExtra("name")
                if(resultintent != null)
                    nametext.text = resultintent
                val resultintent1: ArrayList<Recipe_item>? =
                    data.getParcelableArrayListExtra("recipeList")
                val resultintent2 = data.extras?.get("img") as String?
                cookname = resultintent
                if(resultintent1!= null) {
                    recipeList.clear()
                    total_time = 0
                    for (i in 0 until resultintent1.size) {
                        total_time += resultintent1[i].cooktime!!
                        recipeList.add(resultintent1[i])
                        mAdapter.notifyDataSetChanged()
                    }
                }
                text_timer.text = "${total_time/60} 분 ${total_time%60} 초"
            }
        }

    }

    override fun onBackPressed() {  //Edit했을 수도 있으므로 Intent로 넘김. 변경내용 그대로 Main에 다시 저장
        val name = cookname
        val save_intent = Intent(this, MainActivity::class.java)
        save_intent.putParcelableArrayListExtra("recipeList", recipeList)
        save_intent.putExtra("name",name)
        setResult(1555, save_intent)
        finish()
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
        1050 -> {
            // If request is cancelled, the result arrays are empty.
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                // permission was granted, yay! Do the
                // contacts-related task you need to do.
            }
            else {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
            }
            return
        }

        // Add other 'when' lines to check for other
        // permissions this app might request.
        else -> {
            // Ignore all other requests.
        }
        }
    }
}

