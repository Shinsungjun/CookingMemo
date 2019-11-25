package kau.holyjoon.cookingmemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kau.holyjoon.cookingmemo.ingredientMainActivity.Companion.resultList
import kau.holyjoon.cookingmemo.ingredientMainActivity.Companion.totalList

class ingredientSearchFragment : Fragment() {

    var dataload = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.choice_ingredientsearchfragment, container, false)
        val searchRecyclerview = view.findViewById<RecyclerView>(R.id.choice_ingre_search_recycler)
        val mAdapter1 = IngreSearchAdapter(context!!, totalList,
            resultList
        ) { ingredient ->
            if (ingredient.source == null) {
                Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
        }
        val lm1 = GridLayoutManager(context, 5)
        searchRecyclerview.adapter = mAdapter1
        searchRecyclerview.layoutManager = lm1
        searchRecyclerview.setHasFixedSize(true)
        searchRecyclerview.addItemDecoration(IngredientDecoration())
//
//        val postListener1 = object : ValueEventListener {
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                if(dataload < 1) {
//                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
//                        val ing = postSnapshot.getValue(Ingredient::class.java)
//                        if (ing != null) {
//                            totalList.add(ing)
//                        }
//                    }
//                    dataload++
//                }
//                mAdapter1.notifyDataSetChanged()
//            }
//
//        }
//        val sort1 = FirebaseDatabase.getInstance().getReference().child("ingredient").addListenerForSingleValueEvent(postListener1)
        return view
    }

}
