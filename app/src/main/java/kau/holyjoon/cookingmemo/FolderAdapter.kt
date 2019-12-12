package kau.holyjoon.cookingmemo

import android.content.Context
import android.graphics.Color
import android.view.*
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.folder_item.*

class FolderAdapter(val context: Context, val folderList: ArrayList<Folder>,val itemClick : (Folder?)->Unit)
    : RecyclerView.Adapter<FolderAdapter.FolderViewHolder>(){
    inner class FolderViewHolder(itemview: View?): RecyclerView.ViewHolder(itemview!!){ //뷰홀더

        val Folder_name = itemView?.findViewById<TextView>(R.id.folder_name)
        fun bind(folder:Folder, context:Context) { //recycleview item에 데이터를 붙여주는 작업

            Folder_name?.text = folder.name
            itemView.setOnClickListener { itemClick(folder) }
            if(folder.check==true) //true면 분홍색으로
                Folder_name.setTextColor(Color.parseColor("#FF9999"))
            if(folder.check==false) //false면 회색으로
                Folder_name.setTextColor(Color.parseColor("#FF5E5E5E"))

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



