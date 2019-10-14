package kau.holyjoon.cookingmemo


import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.plus_popup.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class PlusActivity :AppCompatActivity() {   //+ 버튼 클릭 시 나타나는 popup형식 Activity 여기서 만들어진 Recipe객체를 Intent를 통해 EditActivity로 넘겨준다. 그러면 EditActivity에서도 똑같이 객체화!
    //done 버튼을 누르면 지금까지 입력한 재료, 방법, 시간에 대한 정보를 가지고 조건에 맞는다면 Recipe객체를 만들어 Intent로 전달.
    //조건에 맞지 않는다면 무엇이 부족한지 알려줌.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.plus_popup)



        val gridView : GridView = findViewById(R.id.grid_image)
        val texthow : TextView = findViewById(R.id.edit_how)
        val texttime : TextView = findViewById(R.id.edit_time)
        val comment : TextView = findViewById(R.id.edit_comment)

        val intent : Intent = getIntent()
        aboutView()
    }
    fun aboutView() {
        bt_cancel.setOnClickListener { view ->
            finish()
        }
        bt_done.setOnClickListener(){

            //이제 이 버튼 눌렀을 떄가 중요 ..
            //조건에 적합한지 체크 후, 적합하지 않으면 toast가 뜨면서 적절하지 않음을 알려줌. 그리고 Activity를 종료하지 않음
            //조건에 적합하면 Intent에 담아서 바깥에 정보를 가져다가 줌.
            val plusintent = Intent(this,EditActivity::class.java)

            val how = edit_how.text.toString()
            val time = edit_time.text.toString()
            val comment = edit_comment.text.toString()

            val recipelist = ArrayList<Recipe_item>()

            recipelist.add(element = Recipe_item(how,time,comment,comment))


            plusintent.putExtra("how",how)
            plusintent.putExtra("time",time)
            plusintent.putExtra("comment",comment)
            startActivity(plusintent)
            finish()


        }
        bt_addIn.setOnClickListener{
            val intent = Intent (this, Choice_ingredient_Main::class.java)
            startActivityForResult(intent,1)
        }


    }


    override fun onTouchEvent(event: MotionEvent?): Boolean { //바깥 눌러도 Activity 그대로
        if(event?.action == MotionEvent.ACTION_OUTSIDE){
            return false
        }
        return true
    }

    override fun onBackPressed() {  //백버튼은 아무련 변화 x
        super.onBackPressed()
        return
    }
    private fun openActivity ( texthow : String) {
        val howintent = Intent (this, EditActivity::class.java)
        howintent.putExtra("texthow", texthow)
        startActivity(howintent)
    }
    }