package kau.holyjoon.cookingmemo


import android.content.Context
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView


//RecyclerView_Grid 어댑터
class MainAdapter(val context: MainActivity, var hRecipeList:ArrayList<hRecipe>?,val itemClick : (hRecipe?)->Unit)
    :RecyclerView.Adapter<MainAdapter.hRecipeViewHolder>(){

    //아이템 뷰를 저장하는 뷰홀더 클래스,Recyclerview.Adapter의 필수요소
    inner class hRecipeViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){
        //뷰의 이름이 정해지고, layout과 연결됨
        val recipe_name = itemView.findViewById<TextView>(R.id.recipe_name)
        val recipe_image = itemView.findViewById<ImageView>(R.id.recipe_image)

        fun bind(recipe: hRecipe,context:Context) { //recycleview item에 데이터를 붙여주는 작업

            itemView.setOnClickListener { itemClick(recipe) //클릭이벤트
                true
            }
            if (recipe.img!= null) {
                recipe_image?.setImageURI(recipe.img!!.toUri())
            } else {
                recipe_image?.setImageResource(R.drawable.bob)
            }
            recipe_name?.text = recipe.name
        }

    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.(필수 함수)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): hRecipeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.home_item, parent,false)
        return hRecipeViewHolder(view)

    }
    //전체 item 갯수 리턴(필수 함수)
    override fun getItemCount(): Int {
        if(hRecipeList != null)
        return hRecipeList!!.size
        return 0
    }
    //view와 데이터 연결(필수 함수)
    override fun onBindViewHolder(holder: hRecipeViewHolder, position: Int) {
        if(hRecipeList != null)
        holder.bind(hRecipeList!![position], context)
    }

}