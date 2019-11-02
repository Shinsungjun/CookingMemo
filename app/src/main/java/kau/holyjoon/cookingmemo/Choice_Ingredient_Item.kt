package kau.holyjoon.cookingmemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.choice_ingredient_main.*
import kotlinx.android.synthetic.main.plus_popup.*

class Choice_Ingredient_Item : Fragment() {  //item Fragment
    interface update {
        fun updateResultGrid()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var mainActivity = Choice_ingredient_Main()
        var ingredients = (Choice_ingredient_Main).ingredients //Main에 선언해둔 ingredients를 가져와서 씀.
        var resultitem = (Choice_ingredient_Main).resultitems
        val view : View = inflater.inflate(R.layout.choice_ingredient_item, container, false)
        val adapter = Choice_Adapter(requireContext(),(ingredients))
        val gridView = view.findViewById<GridView>(R.id.item_choice_grid)
        var positions : Int?
        gridView.adapter = adapter
        gridView.setOnItemClickListener() {   //gridView 나 ListVew 처럼 한 View에 여러가지 Item이 들어갈때는 setOnItemClickListener을 사용함.
            //각 item마다 매겨진 position으로 해당 position에 있는 item에 변화를 줄 수 있음.
            parent, itemView, position, id ->
            positions = position
            if(ingredients[position].source != "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/%EB%82%98%EB%B9%84.png?alt=media&token=0deb5c70-475a-43e7-a285-e248939a43d8") {
                //Toast.makeText(activity, "${ingredients[position].name}를 선택하였습니다.", Toast.LENGTH_SHORT).show()
                resultitem.add(Ingredient((ingredients[position].name),(ingredients[position].source)))
                Toast.makeText(activity, "${resultitem[0]?.name}를 선택하였습니다.", Toast.LENGTH_SHORT).show()
               /*for(i in 0 until 50) {
                    if(resultitem[i] == null) {
                        resultitem[i] = Ingredient((ingredients[position].name),(ingredients[position].source))
                        break
                    }
                }
                if(resultitem[0] == null) {
                    Toast.makeText(activity, "null를 선택하였습니다.", Toast.LENGTH_SHORT).show()
                }*/
                ingredients[position].source = "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/%EB%82%98%EB%B9%84.png?alt=media&token=0deb5c70-475a-43e7-a285-e248939a43d8"
                // 클릭하면 사진이 사라짐 나중에 체크표시(다른그림) 으로 바꿈 ..
            }
            else {
                Toast.makeText(activity, "이미 선택된 재료입니다!", Toast.LENGTH_SHORT).show()
            }
            adapter.notifyDataSetChanged()
        }
        return view
    }
}