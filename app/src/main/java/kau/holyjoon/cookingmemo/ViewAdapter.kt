package kau.holyjoon.cookingmemo



import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.concurrent.timer


//RecyclerView 어댑터
class ViewAdapter(val context: Context, var recipeList:ArrayList<Recipe_item>, val itemClick : (Recipe_item)->Unit)
    :RecyclerView.Adapter<ViewAdapter.Vholder>(){


    //아이템 뷰를 저장하는 뷰홀더 클래스,Recyclerview.Adapter의 필수요소
    //View.OnCreateContextMenuListener을 implements
    inner class Vholder(itemview: View) : RecyclerView.ViewHolder(itemview){

        //뷰의 이름이 정해지고, layout과 연결됨
        val IngredientRecyclerView = itemview.findViewById<RecyclerView>(R.id.view_recycler)
        val Howmake = itemView.findViewById<TextView>(R.id.howmake_text)
        val Cooktimerun = itemView.findViewById<Button>(R.id.bt_cooktime_run)
        val Cooktimestop = itemView.findViewById<Button>(R.id.bt_cooktime_stop)
        val Comment = itemView.findViewById<TextView>(R.id.comment_text)

        //itemView.setOnCreateContextMenuListener(this);

        fun bind(recipe: Recipe_item,context:Context) { //recycleview item에 데이터를 붙여주는 작업

            Howmake?.text = recipe.howmake
            Cooktimerun.text = recipe.cooktime.toString()
            Cooktimestop.text = "stop"
            Comment?.text = recipe.comment
            Cooktimestop.setOnClickListener { itemClick(recipe)
                Cooktimestop.visibility = View.INVISIBLE
                recipe.isRunning = 0
                recipe.cooktime += recipe.changetime
                recipe.changetime = 0
                notifyDataSetChanged()
            }
            Cooktimerun.setOnClickListener { itemClick(recipe)

                if(recipe.isRunning == 0) {
                    recipe.isRunning = 1
                    Cooktimestop.visibility = View.VISIBLE
                }
                else recipe.isRunning = 0
                val mHandler = Handler(Looper.getMainLooper())
                timer(period = 1000) {
                    //다른 Thread에서 작동
                    if(recipe.isRunning == 0) cancel()
                    if(recipe.isRunning == 1) {
                        recipe.cooktime--
                        recipe.changetime++
                        println("${recipe.cooktime}")
                        mHandler.postDelayed(
                            { notifyDataSetChanged() },
                            0
                        )  //Main Thread를 통해 UI 값 변경
                    }
                }

            }
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
        val ingreAdapter = view_IngreAdapter(context, recipeList[position].ingredient!!)
        val lm1 = GridLayoutManager(context, 3)
        holder.IngredientRecyclerView.adapter = ingreAdapter
        holder.IngredientRecyclerView.layoutManager = lm1
        holder.IngredientRecyclerView.setHasFixedSize(true)
        holder.bind(recipeList[position], context)
    }
}



