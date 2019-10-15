package kau.holyjoon.cookingmemo


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


//RecyclerView 어댑터
class RecipeAdapter(val context: Context, val recipeList:ArrayList<Recipe_item>)
    :RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>(){

    //아이템 뷰를 저장하는 뷰홀더 클래스,Recyclerview.Adapter의 필수요소
    inner class RecipeViewHolder(itemview: View?) : RecyclerView.ViewHolder(itemview!!) {

        //뷰의 이름이 정해지고, layout과 연결됨
        val Ingredient = itemView?.findViewById<TextView>(R.id.ingredient_text)
        val Howmake = itemView?.findViewById<TextView>(R.id.howmake_text)
        val Cooktime = itemView?.findViewById<TextView>(R.id.cooktime_text)
        val Comment = itemView?.findViewById<TextView>(R.id.comment_text)

        fun bind(recipe: Recipe_item,context:Context) { //recycleview item에 데이터를 붙여주는 작업

            Ingredient?.text = recipe.ingredient
            Howmake?.text = recipe.howmake
            Cooktime?.text = recipe.cooktime
            Comment?.text = recipe.comment
        }
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.(필수 함수)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent,false)
        return RecipeViewHolder(view)

    }
    //전체 item 갯수 리턴(필수 함수)
    override fun getItemCount(): Int {
        return recipeList.size
    }
    //view와 데이터 연결(필수 함수)
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder?.bind(recipeList[position], context)

    }



}



