package kau.holyjoon.cookingmemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class view_IngreAdapter(val context : Context, val ingreList : ArrayList<Ingredient>):  //Recycler내의 Recycler에 찍힐 아이들
    RecyclerView.Adapter<view_IngreAdapter.Holder>() {
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val ingredientPhoto = itemView.findViewById<ImageView>(R.id.gridImg)
        val ingredientName = itemView.findViewById<TextView>(R.id.gridName)

        fun bind(ingredient: Ingredient, context: Context) {
            ingredientName.text = ingredient.name
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.ingredient_photo, parent, false)
        return Holder(view)
    }
    override fun getItemCount(): Int {
        return ingreList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(holder.itemView.context).load(ingreList[position].source).into(holder.ingredientPhoto)
        holder.bind(ingreList[position],context)
    }

}