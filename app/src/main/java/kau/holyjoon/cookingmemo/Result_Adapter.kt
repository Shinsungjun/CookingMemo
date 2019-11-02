package kau.holyjoon.cookingmemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import com.bumptech.glide.Glide


class Result_Adapter(val context:Context, val items : ArrayList<Ingredient?>) : BaseAdapter() {  //Fragment내의 GridView Adapter

    override fun getItem(idx: Int): Ingredient? {
        return items[idx] //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(idx: Int): Long {
        return idx.toLong()//To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        return items.size //To change body of created functions use File | Settings | File Templates.
    }

    override fun getView(idx: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView?: LayoutInflater.from(context).inflate(R.layout.ingredient_photo, parent, false) as View
        for(i in 0 until 50) {
            if (items[idx]?.source != null) {
                Glide.with(context).load(items[idx]?.source).into(view.findViewById(R.id.gridImg))
                view.findViewById<TextView>(R.id.gridName).text = items[idx]?.name
            }
        }
        return view
    }
}