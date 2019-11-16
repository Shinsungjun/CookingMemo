package kau.holyjoon.cookingmemo

import android.content.Context
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FolderAdapter(val context: Context, var folderList: ArrayList<String>)
    : RecyclerView.Adapter<FolderAdapter.FolderViewHolder>(){

    inner class FolderViewHolder(itemview: View?): RecyclerView.ViewHolder(itemview!!){

        val Folder_name = itemView?.findViewById<TextView>(R.id.folder_name)
        fun bind(folder: String, context:Context) { //recycleview item에 데이터를 붙여주는 작업

            Folder_name?.text = "folder"

        }
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.(필수 함수)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.folder_item, parent,false)
        return FolderViewHolder(view)

    }
    //전체 item 갯수 리턴(필수 함수)
    override fun getItemCount(): Int {
        return folderList.size
    }
    //view와 데이터 연결(필수 함수)
    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        holder?.bind(folderList[position], context)

    }
}



