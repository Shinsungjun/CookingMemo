package kau.holyjoon.cookingmemo


import android.content.Context
import android.view.*
import android.view.View.inflate
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kau.holyjoon.cookingmemo.RecipeAdapter.RecipeViewHolder
import kotlinx.android.synthetic.main.edit_main.*


//RecyclerView 어댑터
class RecipeAdapter(val context: Context, var recipeList:ArrayList<Recipe_item>)
    :RecyclerView.Adapter<RecipeViewHolder>(){

    //아이템 뷰를 저장하는 뷰홀더 클래스,Recyclerview.Adapter의 필수요소
    //View.OnCreateContextMenuListener을 implements
    inner class RecipeViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview!!){
//inner class RecipeViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview!!),View.OnCreateContextMenuListener
//        override fun onCreateContextMenu(
//            menu: ContextMenu?,
//            v: View?,
//            menuInfo: ContextMenu.ContextMenuInfo?
//        ) {
//            //super.onCreateContextMenu(menu,v,menuInfo)
//            val Edit: MenuItem = menu!!.add(Menu.NONE,1001,1,"편집")
//            val Delete: MenuItem = menu!!.add(Menu.NONE,1002,2,"삭제")
//
//            Edit.setOnMenuItemClickListener(onMenuItemClickListener)
//            Delete.setOnMenuItemClickListener(onMenuItemClickListener)
//
//        }


//        override fun onCreateContextMenu(menu: ContextMenu, v:View?, menuInfo:ContextMenu.ContextMenuInfo?){
//
//            val Edit: MenuItem = menu.add(Menu.NONE,1001,1,"편집")
//            val Delete: MenuItem = menu.add(Menu.NONE,1002,2,"삭제")
//
//            Edit.setOnMenuItemClickListener(onMenuItemClickListener)
//            Delete.setOnMenuItemClickListener(onMenuItemClickListener)
//
//        }

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



    //편집,삭제부분(구현중)
    //컨텍스트 메뉴를 생성하고 메뉴 선택시 호츨되는 리스너 등록
    //ID 1001 1002로 어떤 메뉴를 선택했는지 리스너에서 구분

    //list menuclick Listener
    //클릭시 동작 설정해준다
/*
    val onMenuItemClickListener = MenuItem.OnMenuItemClickListener{ item: MenuItem? ->
        when (item!!.itemId) {
            1001 -> {//편집 눌리면
                val view: View = LayoutInflater.from(context)
                    .inflate(R.layout.plus_popup, null, false) //plus_popup.xml 파일 사용

                val howID = view.findViewById<EditText>(R.id.edit_how)
                val timeID = view.findViewById<EditText>(R.id.edit_time)
                val commentID = view.findViewById<EditText>(R.id.edit_comment)

                //해당줄에입력되어있던데이터를 불러와서 보여줘야하지만...못해서 일단 그냥


                fun OnClick(v: View):Boolean { //수정버튼 클릭하면 현재 입력되어있는 text내용으로 수정
                    //val howmake_text: String = howID.text().toString()
                    //val time_text: String = timeID.text().toString()
                    //val comment_text: String = commentID.text().toString()

                    //List Array에 있는 데이터 변경
                    recipeList.set(
                        getAdapterPosition(),
                        Recipe_item("NULL", howmake_text, time_text, comment_text)
                    )

                    //어댑터에서 Recyclerview에 반영시킴
                    notifyItemChanged(getAdapterPosition())


                }*/
                /*1002 -> {
                    //삭제눌리면
                    recipeList.remove()

                    //어댑터에서 RecyclerView에 반영하도록
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), recipeList.size())
                }
            }
        }
        true
}
*/


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



