package kau.holyjoon.cookingmemo

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//RecyclerView 어댑터
class listAdapter(val context: Context, val list:ArrayList<String>):RecyclerView.Adapter<listAdapter.ViewHolder>(){

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.test_list_item,parent,false)
        return ViewHolder(view)

    }
    //전체 데이터 갯수 리턴
    override fun getItemCount(): Int {
        return list.size
    }
    //position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var text = list[position]
        holder.textView1.text

    }
    //아이템 뷰를 저장하는 뷰홀더 클래스.
    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        var textView1 = itemView.findViewById<TextView>(R.id.text1)
    }
    //수정필요

}



