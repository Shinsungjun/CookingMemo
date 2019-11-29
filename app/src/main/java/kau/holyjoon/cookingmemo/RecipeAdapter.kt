package kau.holyjoon.cookingmemo


import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView
import kau.holyjoon.cookingmemo.RecipeAdapter.RecipeViewHolder


//RecyclerView 어댑터
class RecipeAdapter(val context: Context, var recipeList:ArrayList<Recipe_item?>, val itemClick : (Recipe_item?)->Boolean )
    :RecyclerView.Adapter<RecipeViewHolder>(){

    var itemposition = -1
    //아이템 뷰를 저장하는 뷰홀더 클래스,Recyclerview.Adapter의 필수요소
    //View.OnCreateContextMenuListener을 implements
    inner class RecipeViewHolder(itemview: View,itemClick : (Recipe_item)->Boolean) : RecyclerView.ViewHolder(itemview) {


        //뷰의 이름이 정해지고, layout과 연결됨
        val Ingredient = itemview.findViewById<TextView>(R.id.ingredient_text)
        val Howmake = itemview.findViewById<TextView>(R.id.howmake_text)
        val Cooktime = itemview.findViewById<TextView>(R.id.bt_cooktime_run)
        val Comment = itemview.findViewById<TextView>(R.id.comment_text)
        fun bind(recipe: Recipe_item?,context:Context) { //recycleview item에 데이터를 붙여주는 작업

            Ingredient?.text = recipe?.ingredient?.joinToString {"${it.name}"}
            Howmake?.text = recipe?.howmake
            Cooktime?.text = recipe?.cooktime.toString()
            Comment?.text = recipe?.comment

            itemView.setOnLongClickListener { itemClick(recipe)
                true
            }
        }
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.(필수 함수)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent,false)
        return RecipeViewHolder(view, itemClick)

    }
    //전체 item 갯수 리턴(필수 함수)
    override fun getItemCount(): Int {
        return recipeList.size
    }
    //view와 데이터 연결(필수 함수)
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipeList[position], context)
    }
}



