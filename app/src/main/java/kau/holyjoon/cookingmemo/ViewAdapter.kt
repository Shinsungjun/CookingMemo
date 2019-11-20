package kau.holyjoon.cookingmemo



import android.content.Context
import android.view.*
import android.view.View.inflate
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.core.net.toUri
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kau.holyjoon.cookingmemo.RecipeAdapter.RecipeViewHolder
import kotlinx.android.synthetic.main.edit_main.*


//RecyclerView 어댑터
class ViewAdapter(val context: Context, var recipeList:ArrayList<Recipe_item>)
    :RecyclerView.Adapter<ViewAdapter.Vholder>(){

    //아이템 뷰를 저장하는 뷰홀더 클래스,Recyclerview.Adapter의 필수요소
    //View.OnCreateContextMenuListener을 implements
    inner class Vholder(itemview: View) : RecyclerView.ViewHolder(itemview!!){

        //뷰의 이름이 정해지고, layout과 연결됨
        val Ingredient = itemView?.findViewById<TextView>(R.id.ingredient_text)
        val Howmake = itemView?.findViewById<TextView>(R.id.howmake_text)
        val Cooktime = itemView?.findViewById<TextView>(R.id.cooktime_text)
        val Comment = itemView?.findViewById<TextView>(R.id.comment_text)

        //itemView.setOnCreateContextMenuListener(this);

        fun bind(recipe: Recipe_item,context:Context) { //recycleview item에 데이터를 붙여주는 작업


            Ingredient?.text = recipe.ingredient?.get(0)?.name.toString()
            Howmake?.text = recipe.howmake
            Cooktime?.text = recipe.cooktime
            Comment?.text = recipe.comment

        }

    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.(필수 함수)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vholder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewlist_item, parent,false)
        return Vholder(view)

    }
    //전체 item 갯수 리턴(필수 함수)
    override fun getItemCount(): Int {
        return recipeList.size
    }
    //view와 데이터 연결(필수 함수)
    override fun onBindViewHolder(holder: Vholder, position: Int) {
        holder?.bind(recipeList[position], context)




    }




}



