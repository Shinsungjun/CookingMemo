package kau.holyjoon.cookingmemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class IngreAdapter(val context : Context, val ingreList : ArrayList<Ingredient>, val resultList : ArrayList<Ingredient>,val itemClick : (Ingredient)->Unit):
    RecyclerView.Adapter<IngreAdapter.Holder>() {  //Fragment에서 Recylcer View에 재료를 찍어주는 Adapter
    var btSet = 0
    inner class Holder(itemView : View, itemClick : (Ingredient)->Unit) : RecyclerView.ViewHolder(itemView) {
        val ingredientPhoto = itemView.findViewById<ImageView>(R.id.gridImg)
        val ingredientName = itemView.findViewById<TextView>(R.id.gridName)

        fun bind(ingredient: Ingredient, context: Context) {

            ingredientName.text = ingredient.name
            ingredientPhoto.setOnClickListener {itemClick(ingredient)
                if(ingredient.source != null) {   //선택 했을 시, resultList에 데이터를 추가하고 src를 null로 바꾸어 그림을 사라지게 함
                    resultList.add(Ingredient(ingredient.name, ingredient.source))
                    ingredient.source = null
                }
                else {
                    for(i in 0 until resultList.size) {
                        if(resultList[i].name == ingredient.name) {  //이미 선택되어있는 재료일 시 source를 돌려주고 result에서 삭제. 선택 취소 기능
                            ingredient.source = resultList[i].source
                            resultList.remove(resultList[i])
                            break
                        }
                    }
                }
                notifyDataSetChanged()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.ingredient_photo, parent, false)
        return Holder(view, itemClick)
    }
    override fun getItemCount(): Int {
        if(ingreList.size > 10 && btSet == 0) return 10
        return ingreList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if(ingreList.size < 11 && btSet == 0) { //11개보다 작으면 줄일필요 x
            for(i in 0 until resultList.size) {
                if(resultList[i].name == ingreList[position].name) ingreList[position].source = null
            }
            Glide.with(holder.itemView.context).load(ingreList[position].source).into(holder.ingredientPhoto)
            holder.bind(ingreList[position],context)
        }
        else if(ingreList.size > 10 && btSet == 0) { //10개보다 클때는 10개만 출력하도록
            if(position < 10) {      //10개 까지만 표기 버튼 누르면 추가로 늘릴 수 있게 해야함.
                for(i in 0 until resultList.size) {
                    if(resultList[i].name == ingreList[position].name) ingreList[position].source = null
                }
                Glide.with(holder.itemView.context).load(ingreList[position].source).into(holder.ingredientPhoto)
                holder.bind(ingreList[position],context)
            }
        }
        else if(btSet == 1){
            for(i in 0 until resultList.size) {
                if(resultList[i].name == ingreList[position].name) ingreList[position].source = null
            }
            Glide.with(holder.itemView.context).load(ingreList[position].source).into(holder.ingredientPhoto)
            holder.bind(ingreList[position],context)
        }
    }
}