package kau.holyjoon.cookingmemo

import android.content.Context
import android.media.Image
import android.net.Uri
import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class Choice_Adapter(val context:Context, val items : Array<Ingredient>) : BaseAdapter() {  //Fragment내의 GridView Adapter

    val griditems : IntArray = intArrayOf(R.drawable.bread,R.drawable.egg,
                                        R.drawable.milk,R.drawable.sugar)
    override fun getItem(idx: Int): Any {
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
        view.findViewById<ImageView>(R.id.gridImg).setImageResource(griditems[idx])
        view.findViewById<TextView>(R.id.gridName).text = items[idx].name

        return view
}
}