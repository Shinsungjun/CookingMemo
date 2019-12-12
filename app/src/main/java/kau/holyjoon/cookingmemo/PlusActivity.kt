package kau.holyjoon.cookingmemo


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.GridView
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.plus_popup.*


class PlusActivity :AppCompatActivity() {   //+ 버튼 클릭 시 나타나는 popup형식 Activity 여기서 만들어진 Recipe_item객체를 Intent를 통해 EditActivity로 넘겨준다. 그러면 EditActivity에서도 똑같이 객체화!
    //done 버튼을 누르면 지금까지 입력한 재료, 방법, 시간에 대한 정보를 가지고 조건에 맞는다면 Recipe_item객체를 만들어 Intent로 전달.
    //조건에 맞지 않는다면 무엇이 부족한지 알려줌.
    var ingredients = arrayListOf<Ingredient>()
    var msecond : Int? = null
    var mminute :Int? = null
    val popupAdapter = Popup_Adapter(this, ingredients) //Grid View Adapter
    val reitem by lazy{intent?.extras?.get("recipe") as Recipe_item?}
    @SuppressLint("Range")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.plus_popup)
        val gridView = findViewById<GridView>(R.id.grid_image)
        val textminute = findViewById<EditText>(R.id.edit_time_minute)
        val textsecond = findViewById<EditText>(R.id.edit_time_second)
        val comment = findViewById<EditText>(R.id.edit_comment)
        val howmake = findViewById<EditText>(R.id.edit_how)

        if(reitem?.cooktime != null) {  //Edit에서 Longclick으로 수정을 할 때 넘어오는 데이터 받기
            textminute.setText("${reitem?.cooktime?.div(60)}")
            textsecond.setText("${reitem?.cooktime?.rem(60)}")
        }
        comment.setText(reitem?.comment)
        howmake.setText(reitem?.howmake)
        if(reitem?.ingredient != null) {
            ingredients.clear()
            popupAdapter.notifyDataSetInvalidated()
            for (i in 0 until reitem?.ingredient!!.size) {  //ArrayList 넘겨받을때는 이런식으로 받아야함!!!!
                ingredients.add(reitem?.ingredient!![i])
            }
            bt_delete_pop.visibility = View.VISIBLE
            popupAdapter.notifyDataSetChanged()
        }
        bt_delete_pop.setOnClickListener {  //과정 delete intent 넘김
            val plusintent = Intent(this,EditActivity::class.java)
            val delete = "delete"
            plusintent.putExtra("delete",delete)
            setResult(1, plusintent)
            finish()
        }
        gridView.adapter = popupAdapter
        aboutView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(data != null) {
            val intent = data.extras?.getParcelableArrayList<Ingredient>("back")

            if (intent != null) {
                ingredients.clear()
                popupAdapter.notifyDataSetInvalidated()
                for(i in 0 until intent.size) {  //ArrayList 넘겨받을때는 이런식으로 받아야함!!!!
                    ingredients.add(intent[i])
                }
                popupAdapter.notifyDataSetChanged()
            }
        }
    }
    fun aboutView() {
        bt_cancel_pop.setOnClickListener { view ->  // 레시피 추가 없이 취소
            finish()
        }

        bt_done_pop.setOnClickListener {  //필요한 정보를 모두 다 적었고, Edit으로 레시피의 한 단계를 저장.
            val plusintent = Intent(this,EditActivity::class.java)
            mminute = edit_time_minute.text.toString().toInt()
            msecond = edit_time_second.text.toString().toInt()
            val how = edit_how.text.toString()
            val time = mminute!! * 60 + msecond!!
            val comment = edit_comment.text.toString()

            if (how != "" && time != 0) {
                    val recipe = Recipe_item(ingredients,how, time, comment)

                    plusintent.putExtra("recipe", recipe)

                    setResult(1, plusintent)
                    finish()
            }
            else Toast.makeText(this, "적지 않은 방법, 시간이 있습니다!", Toast.LENGTH_SHORT).show()


        }
        bt_addIn.setOnClickListener{  //재료를 선택하기 위한 IngredientMainActivity로 넘어감.
            val intent = Intent(this, ingredientMainActivity::class.java)
            intent.putParcelableArrayListExtra("back",ingredients)
            startActivityForResult(intent, 1)
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
}