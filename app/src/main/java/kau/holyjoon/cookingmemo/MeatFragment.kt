package kau.holyjoon.cookingmemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MeatFragment : Fragment() {
    var cowList = arrayListOf<Ingredient>()
    var porkList = arrayListOf<Ingredient>()
    var chickenList = arrayListOf<Ingredient>()
    var etcList = arrayListOf<Ingredient>()
    var dataload = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.meatfragment, container, false)
        val bt_total_1 = view.findViewById<Button>(R.id.bt_total_meat_1)
        val bt_total_2 = view.findViewById<Button>(R.id.bt_total_meat_2)
        val bt_total_3 = view.findViewById<Button>(R.id.bt_total_meat_3)
        val bt_total_4 = view.findViewById<Button>(R.id.bt_total_meat_4)

        val famousRecyclerView1 = view.findViewById<RecyclerView>(R.id.Recyclerview_meat1)
        val famousRecyclerView2 = view.findViewById<RecyclerView>(R.id.Recyclerview_meat2)
        val famousRecyclerView3 = view.findViewById<RecyclerView>(R.id.Recyclerview_meat3)
        val famousRecyclerView4 = view.findViewById<RecyclerView>(R.id.Recyclerview_meat4)


        val mAdapter1 = IngreAdapter(context!!, cowList,
            ingredientMainActivity.resultList
        ) { ingredient ->
            if (ingredient.source == null) {
                Toast.makeText(context, "이미 선택된 재료입니다.", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
        }
        val mAdapter2 = IngreAdapter(context!!, porkList,
            ingredientMainActivity.resultList
        ) { ingredient ->
            if (ingredient.source == null) {
                Toast.makeText(context, "이미 선택된 재료입니다.", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
        }
        val mAdapter3 = IngreAdapter(context!!, chickenList,
            ingredientMainActivity.resultList
        ) { ingredient ->
            if (ingredient.source == null) {
                Toast.makeText(context, "이미 선택된 재료입니다.", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
        }
        val mAdapter4 = IngreAdapter(context!!, etcList,
            ingredientMainActivity.resultList
        ) { ingredient ->
            if (ingredient.source == null) {
                Toast.makeText(context, "이미 선택된 재료입니다.", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
        }

        val lm1 = GridLayoutManager(context, 5)
        val lm2 = GridLayoutManager(context, 5)
        val lm3 = GridLayoutManager(context, 5)
        val lm4 = GridLayoutManager(context, 5)

        famousRecyclerView1.adapter = mAdapter1
        famousRecyclerView1.layoutManager = lm1
        famousRecyclerView1.setHasFixedSize(true)
        famousRecyclerView1.addItemDecoration(IngredientDecoration())

        famousRecyclerView2.adapter = mAdapter2
        famousRecyclerView2.layoutManager = lm2
        famousRecyclerView2.setHasFixedSize(true)
        famousRecyclerView2.addItemDecoration(IngredientDecoration())

        famousRecyclerView3.adapter = mAdapter3
        famousRecyclerView3.layoutManager = lm3
        famousRecyclerView3.setHasFixedSize(true)
        famousRecyclerView3.addItemDecoration(IngredientDecoration())

        famousRecyclerView4.adapter = mAdapter4
        famousRecyclerView4.layoutManager = lm4
        famousRecyclerView4.setHasFixedSize(true)
        famousRecyclerView4.addItemDecoration(IngredientDecoration())

        bt_total_1.setOnClickListener {
            if (mAdapter1.btSet == 0) {
                //mAdapter = IngreAdapter(context!!, ingredientList,resultList){ingredient -> Toast.makeText(context,"${ingredient.name}",Toast.LENGTH_LONG).show() }
                //famousRecyclerView1.adapter = mAdapter
                mAdapter1.btSet = 1

            } else {
                // mAdapter = IngreAdapter(context!!, ingredientList2,resultList){ingredient -> Toast.makeText(context,"${ingredient.name}",Toast.LENGTH_LONG).show() }
                // famousRecyclerView1.adapter = mAdapter
                mAdapter1.btSet = 0
            }
            mAdapter1.notifyDataSetChanged()
        }
        bt_total_2.setOnClickListener {
            if (mAdapter2.btSet == 0) {
                mAdapter2.btSet = 1

            } else {
                mAdapter2.btSet = 0
            }
            mAdapter2.notifyDataSetChanged()
        }

        bt_total_3.setOnClickListener {
            if (mAdapter3.btSet == 0) {
                mAdapter3.btSet = 1

            } else {
                mAdapter3.btSet = 0
            }
            mAdapter3.notifyDataSetChanged()

        }

        bt_total_4.setOnClickListener {
            if (mAdapter4.btSet == 0) {
                mAdapter4.btSet = 1

            } else {
                mAdapter4.btSet = 0
            }
            mAdapter4.notifyDataSetChanged()
        }

        val postListener1 = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataload < 4) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            cowList.add(ing)
                        }
                    }
                    dataload++
                }
                mAdapter1.notifyDataSetChanged()
            }

        }
        val postListener2 = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataload < 4) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            porkList.add(ing)
                        }
                    }
                    dataload++
                }
                mAdapter2.notifyDataSetChanged()
            }

        }
        val postListener3 = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataload < 4) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            chickenList.add(ing)
                        }
                    }
                    dataload++
                }
                mAdapter3.notifyDataSetChanged()
            }

        }
        val postListener4 = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataload < 4) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            etcList.add(ing)
                        }
                    }
                    dataload++
                }
                mAdapter4.notifyDataSetChanged()
            }

        }
        val sort1 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("meat").child("cow")
            .addListenerForSingleValueEvent(postListener1)
        val sort2 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("meat").child("pork")
            .addListenerForSingleValueEvent(postListener2)
        val sort3 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("meat").child("chicken")
            .addListenerForSingleValueEvent(postListener3)
        val sort4 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("meat").child("etc")
            .addListenerForSingleValueEvent(postListener4)

        return view
    }
}