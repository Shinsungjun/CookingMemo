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

class YogurtFragment : Fragment() {

    var dataload = 0

    var milksList = arrayListOf<Ingredient>()
    var noodlesList = arrayListOf<Ingredient>()
    var cheeseList = arrayListOf<Ingredient>()
    var kimchiList = arrayListOf<Ingredient>()
    var subdishList = arrayListOf<Ingredient>()
    var dobuList = arrayListOf<Ingredient>()
    var jangajiList = arrayListOf<Ingredient>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.yogurtfragment, container, false)
        val bt_total_1 = view.findViewById<Button>(R.id.bt_total_yogurt_1)
        val bt_total_2 = view.findViewById<Button>(R.id.bt_total_yogurt_2)
        val bt_total_3 = view.findViewById<Button>(R.id.bt_total_yogurt_3)
        val bt_total_4 = view.findViewById<Button>(R.id.bt_total_yogurt_4)
        val bt_total_5 = view.findViewById<Button>(R.id.bt_total_yogurt_5)
        val bt_total_6 = view.findViewById<Button>(R.id.bt_total_yogurt_6)
        val bt_total_7 = view.findViewById<Button>(R.id.bt_total_yogurt_7)

        val yogurtRecyclerView1 = view.findViewById<RecyclerView>(R.id.Recyclerview_yogurt_1)
        val yogurtRecyclerView2 = view.findViewById<RecyclerView>(R.id.Recyclerview_yogurt_2)
        val yogurtRecyclerView3 = view.findViewById<RecyclerView>(R.id.Recyclerview_yogurt_3)
        val yogurtRecyclerView4 = view.findViewById<RecyclerView>(R.id.Recyclerview_yogurt_4)
        val yogurtRecyclerView5 = view.findViewById<RecyclerView>(R.id.Recyclerview_yogurt_5)
        val yogurtRecyclerView6 = view.findViewById<RecyclerView>(R.id.Recyclerview_yogurt_6)
        val yogurtRecyclerView7 = view.findViewById<RecyclerView>(R.id.Recyclerview_yogurt_7)


        val mAdapter1 = IngreAdapter(context!!, noodlesList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }
        val mAdapter2 = IngreAdapter(context!!, milksList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }
        val mAdapter3 = IngreAdapter(context!!, cheeseList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }
        val mAdapter4 = IngreAdapter(context!!, kimchiList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }
        val mAdapter5 = IngreAdapter(context!!, subdishList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }
        val mAdapter6 = IngreAdapter(context!!, dobuList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }
        val mAdapter7 = IngreAdapter(context!!, jangajiList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }

        val lm1 = GridLayoutManager(context, 5)
        val lm2 = GridLayoutManager(context, 5)
        val lm3 = GridLayoutManager(context, 5)
        val lm4 = GridLayoutManager(context, 5)
        val lm5 = GridLayoutManager(context, 5)
        val lm6 = GridLayoutManager(context, 5)
        val lm7 = GridLayoutManager(context, 5)

        yogurtRecyclerView1.adapter = mAdapter1
        yogurtRecyclerView1.layoutManager = lm1
        yogurtRecyclerView1.setHasFixedSize(true)
        yogurtRecyclerView1.addItemDecoration(IngredientDecoration())

        yogurtRecyclerView2.adapter = mAdapter2
        yogurtRecyclerView2.layoutManager = lm2
        yogurtRecyclerView2.setHasFixedSize(true)
        yogurtRecyclerView2.addItemDecoration(IngredientDecoration())

        yogurtRecyclerView3.adapter = mAdapter3
        yogurtRecyclerView3.layoutManager = lm3
        yogurtRecyclerView3.setHasFixedSize(true)
        yogurtRecyclerView3.addItemDecoration(IngredientDecoration())

        yogurtRecyclerView4.adapter = mAdapter4
        yogurtRecyclerView4.layoutManager = lm4
        yogurtRecyclerView4.setHasFixedSize(true)
        yogurtRecyclerView4.addItemDecoration(IngredientDecoration())

        yogurtRecyclerView5.adapter = mAdapter5
        yogurtRecyclerView5.layoutManager = lm5
        yogurtRecyclerView5.setHasFixedSize(true)
        yogurtRecyclerView5.addItemDecoration(IngredientDecoration())

        yogurtRecyclerView6.adapter = mAdapter6
        yogurtRecyclerView6.layoutManager = lm6
        yogurtRecyclerView6.setHasFixedSize(true)
        yogurtRecyclerView6.addItemDecoration(IngredientDecoration())

        yogurtRecyclerView7.adapter = mAdapter7
        yogurtRecyclerView7.layoutManager = lm7
        yogurtRecyclerView7.setHasFixedSize(true)
        yogurtRecyclerView7.addItemDecoration(IngredientDecoration())

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
        bt_total_5.setOnClickListener {
            if (mAdapter5.btSet == 0) {
                mAdapter5.btSet = 1
                bt_total_5.text = "닫기"
            } else {
                mAdapter5.btSet = 0
                bt_total_5.text = "더보기"
            }
            mAdapter5.notifyDataSetChanged()
        }
        bt_total_6.setOnClickListener {
            if (mAdapter6.btSet == 0) {
                mAdapter6.btSet = 1
                bt_total_6.text = "닫기"
            } else {
                mAdapter6.btSet = 0
                bt_total_6.text = "더보기"
            }
            mAdapter6.notifyDataSetChanged()
        }
        bt_total_7.setOnClickListener {
            if (mAdapter7.btSet == 0) {
                mAdapter7.btSet = 1
                bt_total_7.text = "닫기"

            } else {
                mAdapter7.btSet = 0
                bt_total_7.text = "더보기"
            }
            mAdapter7.notifyDataSetChanged()
        }

        val postListener1 = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataload < 7) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            noodlesList.add(ing)
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
                if(dataload < 7) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            milksList.add(ing)
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
                if(dataload < 7) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            cheeseList.add(ing)
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
                if(dataload < 7) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            kimchiList.add(ing)
                        }
                    }
                    dataload++
                }
                mAdapter4.notifyDataSetChanged()
            }

        }
        val postListener5 = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataload < 7) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            subdishList.add(ing)
                        }
                    }
                    dataload++
                }
                mAdapter5.notifyDataSetChanged()
            }

        }
        val postListener6 = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataload < 7) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            dobuList.add(ing)
                        }
                    }
                    dataload++
                }
                mAdapter6.notifyDataSetChanged()
            }

        }
        val postListener7 = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataload < 7) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            jangajiList.add(ing)
                        }
                    }
                    dataload++
                }
                mAdapter7.notifyDataSetChanged()
            }

        }
        val sort1 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("yogurt").child("noodles")
            .addListenerForSingleValueEvent(postListener1)

        val sort2 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("yogurt").child("milks")
            .addListenerForSingleValueEvent(postListener2)

        val sort3 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("yogurt").child("cheese")
            .addListenerForSingleValueEvent(postListener3)

        val sort4 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("yogurt").child("kimchi")
            .addListenerForSingleValueEvent(postListener4)

        val sort5 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("yogurt").child("subdish")
            .addListenerForSingleValueEvent(postListener5)

        val sort6 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("yogurt").child("dobu")
            .addListenerForSingleValueEvent(postListener6)

        val sort7 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("yogurt").child("jangaji")
            .addListenerForSingleValueEvent(postListener7)


        return view
    }
}