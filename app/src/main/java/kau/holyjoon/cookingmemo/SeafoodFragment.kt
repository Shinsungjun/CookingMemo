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

class SeafoodFragment : Fragment() {
    var fishList = arrayListOf<Ingredient>()
    var shellList = arrayListOf<Ingredient>()
    var seeweedList = arrayListOf<Ingredient>()
    var driedList = arrayListOf<Ingredient>()
    var dataload = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.seafoodfragment, container, false)
        val bt_total_1 = view.findViewById<Button>(R.id.bt_total_seafood_1)
        val bt_total_2 = view.findViewById<Button>(R.id.bt_total_seafood_2)
        val bt_total_3 = view.findViewById<Button>(R.id.bt_total_seafood_3)
        val bt_total_4 = view.findViewById<Button>(R.id.bt_total_seafood_4)

        val seafoodRecyclerView1 = view.findViewById<RecyclerView>(R.id.Recyclerview_seafood_1)
        val seafoodRecyclerView2 = view.findViewById<RecyclerView>(R.id.Recyclerview_seafood_2)
        val seafoodRecyclerView3 = view.findViewById<RecyclerView>(R.id.Recyclerview_seafood_3)
        val seafoodRecyclerView4 = view.findViewById<RecyclerView>(R.id.Recyclerview_seafood_4)


        val mAdapter1 = IngreAdapter(
            context!!, fishList,
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
            context!!, shellList,
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
            context!!, seeweedList,
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
        val mAdapter4 = IngreAdapter(
            context!!, driedList,
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
        val lm4 = GridLayoutManager(context, 5)

        seafoodRecyclerView1.adapter = mAdapter1
        seafoodRecyclerView1.layoutManager = lm1
        seafoodRecyclerView1.setHasFixedSize(true)
        seafoodRecyclerView1.addItemDecoration(IngredientDecoration())

        seafoodRecyclerView2.adapter = mAdapter2
        seafoodRecyclerView2.layoutManager = lm2
        seafoodRecyclerView2.setHasFixedSize(true)
        seafoodRecyclerView2.addItemDecoration(IngredientDecoration())

        seafoodRecyclerView3.adapter = mAdapter3
        seafoodRecyclerView3.layoutManager = lm3
        seafoodRecyclerView3.setHasFixedSize(true)
        seafoodRecyclerView3.addItemDecoration(IngredientDecoration())

        seafoodRecyclerView4.adapter = mAdapter4
        seafoodRecyclerView4.layoutManager = lm4
        seafoodRecyclerView4.setHasFixedSize(true)
        seafoodRecyclerView4.addItemDecoration(IngredientDecoration())

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
                if (dataload < 4) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            fishList.add(ing)
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
                if (dataload < 4) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            shellList.add(ing)
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
                if (dataload < 4) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            seeweedList.add(ing)
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
                if (dataload < 4) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            driedList.add(ing)
                        }
                    }
                    dataload++
                }
                mAdapter4.notifyDataSetChanged()
            }

        }
        val sort1 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("seafood")
            .child("fish")
            .addListenerForSingleValueEvent(postListener1)
        val sort2 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("seafood")
            .child("shellfish")
            .addListenerForSingleValueEvent(postListener2)
        val sort3 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("seafood")
            .child("seaweed")
            .addListenerForSingleValueEvent(postListener3)
        val sort4 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("seafood")
            .child("dried")
            .addListenerForSingleValueEvent(postListener4)

        return view
    }
}