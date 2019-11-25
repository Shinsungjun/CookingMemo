package kau.holyjoon.cookingmemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kau.holyjoon.cookingmemo.ingredientMainActivity.Companion.resultList

//import kau.holyjoon.cookingmemo.ingredientMainFragment.Companion.resultList

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

        val meatRecyclerView1 = view.findViewById<RecyclerView>(R.id.Recyclerview_meat1)
        val meatRecyclerView2 = view.findViewById<RecyclerView>(R.id.Recyclerview_meat2)
        val meatRecyclerView3 = view.findViewById<RecyclerView>(R.id.Recyclerview_meat3)
        val meatRecyclerView4 = view.findViewById<RecyclerView>(R.id.Recyclerview_meat4)


        val mAdapter1 = IngreAdapter(context!!, cowList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }
        val mAdapter2 = IngreAdapter(context!!, porkList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }
        val mAdapter3 = IngreAdapter(context!!, chickenList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }
        val mAdapter4 = IngreAdapter(context!!, etcList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }

        val lm1 = GridLayoutManager(context, 5)
        val lm2 = GridLayoutManager(context, 5)
        val lm3 = GridLayoutManager(context, 5)
        val lm4 = GridLayoutManager(context, 5)

        meatRecyclerView1.adapter = mAdapter1
        meatRecyclerView1.layoutManager = lm1
        meatRecyclerView1.setHasFixedSize(true)
        meatRecyclerView1.addItemDecoration(IngredientDecoration())

        meatRecyclerView2.adapter = mAdapter2
        meatRecyclerView2.layoutManager = lm2
        meatRecyclerView2.setHasFixedSize(true)
        meatRecyclerView2.addItemDecoration(IngredientDecoration())

        meatRecyclerView3.adapter = mAdapter3
        meatRecyclerView3.layoutManager = lm3
        meatRecyclerView3.setHasFixedSize(true)
        meatRecyclerView3.addItemDecoration(IngredientDecoration())

        meatRecyclerView4.adapter = mAdapter4
        meatRecyclerView4.layoutManager = lm4
        meatRecyclerView4.setHasFixedSize(true)
        meatRecyclerView4.addItemDecoration(IngredientDecoration())

        bt_total_1.setOnClickListener {
            if (mAdapter1.btSet == 0) {
                //mAdapter = IngreAdapter(context!!, ingredientList,resultList){ingredient -> Toast.makeText(context,"${ingredient.name}",Toast.LENGTH_LONG).show() }
                //famousRecyclerView1.adapter = mAdapter
                mAdapter1.btSet = 1
                bt_total_1.text = "닫기"
            } else {
                // mAdapter = IngreAdapter(context!!, ingredientList2,resultList){ingredient -> Toast.makeText(context,"${ingredient.name}",Toast.LENGTH_LONG).show() }
                // famousRecyclerView1.adapter = mAdapter
                mAdapter1.btSet = 0
                bt_total_1.text = "더보기"
            }
            mAdapter1.notifyDataSetChanged()
        }
        bt_total_2.setOnClickListener {
            if (mAdapter2.btSet == 0) {
                mAdapter2.btSet = 1
                bt_total_2.text = "닫기"
            } else {
                mAdapter2.btSet = 0
                bt_total_2.text = "더보기"
            }
            mAdapter2.notifyDataSetChanged()
        }

        bt_total_3.setOnClickListener {
            if (mAdapter3.btSet == 0) {
                mAdapter3.btSet = 1
                bt_total_3.text = "닫기"
            } else {
                mAdapter3.btSet = 0
                bt_total_3.text = "더보기"
            }
            mAdapter3.notifyDataSetChanged()

        }

        bt_total_4.setOnClickListener {
            if (mAdapter4.btSet == 0) {
                mAdapter4.btSet = 1
                bt_total_4.text = "닫기"
            } else {
                mAdapter4.btSet = 0
                bt_total_4.text = "더보기"
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