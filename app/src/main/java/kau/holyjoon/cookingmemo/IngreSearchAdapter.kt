package kau.holyjoon.cookingmemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class IngreSearchAdapter(val context : Context, val ingreList : ArrayList<Ingredient>, val resultList : ArrayList<Ingredient>, val itemClick : (Ingredient)->Unit):
    RecyclerView.Adapter<IngreSearchAdapter.Holder>(), Filterable {  //Recycler의 Search를 도와주는 Adapter이지만 .. 구현 못함

    var searchList : ArrayList<Ingredient>? = null
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charString = charSequence.toString()
                if(charString.isEmpty()) {

                }
                else {
                    val filterList = ArrayList<Ingredient>()
                    for(row in ingreList) {
                        if(row.name?.toLowerCase()?.contains(charString.toLowerCase())!!){
                            filterList.add(row)
                        }
                    }
                    searchList = filterList
                }
                val filterResults = FilterResults()
                filterResults.values = searchList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence?, results: FilterResults?) {
                searchList = results?.values as ArrayList<Ingredient>
                notifyDataSetChanged()
            }
        }
    }

    inner class Holder(itemView : View,itemClick : (Ingredient)->Unit) : RecyclerView.ViewHolder(itemView) {
        val ingredientPhoto = itemView.findViewById<ImageView>(R.id.gridImg)
        val ingredientName = itemView.findViewById<TextView>(R.id.gridName)

        fun bind(ingredient: Ingredient, context: Context) {
            ingredientName.text = ingredient.name
            ingredientPhoto.setOnClickListener {itemClick(ingredient)
                if(ingredient.source != null) {
                    resultList.add(Ingredient(ingredient.name, ingredient.source))
                    ingredient.source = null
                }
                else {
                    for(i in 0 until resultList.size) {
                        if(resultList[i].name == ingredient.name) {
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
        return ingreList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(holder.itemView.context).load(searchList?.get(position)?.source).into(holder.ingredientPhoto)
        searchList?.get(position)?.let { holder.bind(it,context) }
    }
}