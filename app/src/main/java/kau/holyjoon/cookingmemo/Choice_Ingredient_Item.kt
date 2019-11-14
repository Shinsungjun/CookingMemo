package kau.holyjoon.cookingmemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.choice_ingredient_main.*
import kotlinx.android.synthetic.main.plus_popup.*

class Choice_Ingredient_Item : Fragment() {  //item Fragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var ingredients = ArrayList<Ingredient>()
        val view : View = inflater.inflate(R.layout.choice_ingredient_item, container, false)
        val adapter = Choice_Adapter(requireContext(),(ingredients))
        val gridView = view.findViewById<GridView>(R.id.item_choice_grid)


        val resultAdapter = Result_Adapter(context!!, Choice_ingredient_Main.resultitems)
        val main_gridView : GridView = activity!!.findViewById(R.id.grid_result_inmain)
        main_gridView.adapter = resultAdapter
        //하 ... 됐다 !! Activity에 있는거 가져와서 사용.. 이런식으로 해도 되는지는 모르겠는데 fragment니까 되는거겠지.

        //var positions : Int?
        gridView.adapter = adapter
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(postSnapshot : DataSnapshot in dataSnapshot.children) {
                    val ing = postSnapshot.getValue(Ingredient::class.java)
                    if(ing != null) {
                        ingredients.add(ing)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        val sort = FirebaseDatabase.getInstance().getReference().child("ingredient").child("etc").child("liquid")
            .addListenerForSingleValueEvent(postListener)
        var mainActivity = Choice_ingredient_Main()
        //var ingredients = (Choice_ingredient_Main).ingredients //Main에 선언해둔 ingredients를 가져와서 씀.
        var resultitem = (Choice_ingredient_Main).resultitems

        adapter.notifyDataSetChanged()
        main_gridView.setOnItemClickListener() {
            parent, itemView, position, id ->
            //positions = position
            if(resultitem[position]!=null) {
                Toast.makeText(activity,"${resultitem[position]?.name}을 삭제하였습니다.", Toast.LENGTH_SHORT).show()
                resultitem.removeAt(position)
                resultAdapter.notifyDataSetChanged()
            }
            else {
                Toast.makeText(activity,"null",Toast.LENGTH_SHORT).show()
            }

        }
        gridView.setOnItemClickListener() {   //gridView 나 ListVew 처럼 한 View에 여러가지 Item이 들어갈때는 setOnItemClickListener을 사용함.
            //각 item마다 매겨진 position으로 해당 position에 있는 item에 변화를 줄 수 있음.
            parent, itemView, position, id ->
            //positions = position
            if(ingredients[position].source != "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/%EB%82%98%EB%B9%84.png?alt=media&token=0deb5c70-475a-43e7-a285-e248939a43d8") {
                //Toast.makeText(activity, "${ingredients[position].name}를 선택하였습니다.", Toast.LENGTH_SHORT).show()
                resultitem.add(Ingredient((ingredients[position].name),(ingredients[position].source)))
                //(Choice_ingredient_Main).resultitems = resultitem
                Toast.makeText(activity, "${ingredients[position].name}를 선택하였습니다.", Toast.LENGTH_SHORT).show()


                resultAdapter.notifyDataSetChanged()

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