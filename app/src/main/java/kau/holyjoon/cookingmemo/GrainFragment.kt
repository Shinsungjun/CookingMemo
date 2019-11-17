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

class GrainFragment : Fragment() {
    var nutsList = arrayListOf<Ingredient>()
    var cerealList = arrayListOf<Ingredient>()
    var wheatList = arrayListOf<Ingredient>()
    var dataload = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.grainfragment, container, false)
        val bt_total_1 = view.findViewById<Button>(R.id.bt_total_grain_1)
        val bt_total_2 = view.findViewById<Button>(R.id.bt_total_grain_2)
        val bt_total_3 = view.findViewById<Button>(R.id.bt_total_grain_3)

        val grainRecyclerView1 = view.findViewById<RecyclerView>(R.id.Recyclerview_grain_1)
        val grainRecyclerView2 = view.findViewById<RecyclerView>(R.id.Recyclerview_grain_2)
        val grainRecyclerView3 = view.findViewById<RecyclerView>(R.id.Recyclerview_grain_3)


        val mAdapter1 = IngreAdapter(
            context!!, nutsList,
            resultList, { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "이미 선택된 재료입니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }, {
                    ingredient ->
                for(i in 0 until resultList.size) {
                    if(resultList[i].name == ingredient.name) {
                        ingredient.source = resultList[i].source
                        Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT)
                            .show()
                        resultList.remove(resultList[i])
                        break
                    }
                }
            })
        val mAdapter2 = IngreAdapter(
            context!!, cerealList,
            resultList, { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "이미 선택된 재료입니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }, {
                    ingredient ->
                for(i in 0 until resultList.size) {
                    if(resultList[i].name == ingredient.name) {
                        ingredient.source = resultList[i].source
                        Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT)
                            .show()
                        resultList.remove(resultList[i])
                        break
                    }
                }
            })
        val mAdapter3 = IngreAdapter(
            context!!, wheatList,
            resultList, { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "이미 선택된 재료입니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }, {
                    ingredient ->
                for(i in 0 until resultList.size) {
                    if(resultList[i].name == ingredient.name) {
                        ingredient.source = resultList[i].source
                        Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT)
                            .show()
                        resultList.remove(resultList[i])
                        break
                    }
                }
            })

        val lm1 = GridLayoutManager(context, 5)
        val lm2 = GridLayoutManager(context, 5)
        val lm3 = GridLayoutManager(context, 5)

        grainRecyclerView1.adapter = mAdapter1
        grainRecyclerView1.layoutManager = lm1
        grainRecyclerView1.setHasFixedSize(true)
        grainRecyclerView1.addItemDecoration(IngredientDecoration())

        grainRecyclerView2.adapter = mAdapter2
        grainRecyclerView2.layoutManager = lm2
        grainRecyclerView2.setHasFixedSize(true)
        grainRecyclerView2.addItemDecoration(IngredientDecoration())

        grainRecyclerView3.adapter = mAdapter3
        grainRecyclerView3.layoutManager = lm3
        grainRecyclerView3.setHasFixedSize(true)
        grainRecyclerView3.addItemDecoration(IngredientDecoration())

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


        val postListener1 = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataload < 3) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            nutsList.add(ing)
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
                if (dataload < 3) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            cerealList.add(ing)
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
                if (dataload < 3) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            wheatList.add(ing)
                        }
                    }
                    dataload++
                }
                mAdapter3.notifyDataSetChanged()
            }

        }
        val sort1 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("grain")
            .child("nuts")
            .addListenerForSingleValueEvent(postListener1)
        val sort2 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("grain")
            .child("cereal")
            .addListenerForSingleValueEvent(postListener2)
        val sort3 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("grain")
            .child("wheat")
            .addListenerForSingleValueEvent(postListener3)

        return view
    }
}