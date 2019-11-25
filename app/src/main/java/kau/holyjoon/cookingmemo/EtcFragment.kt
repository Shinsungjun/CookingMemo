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
import kau.holyjoon.cookingmemo.ingredientMainActivity.Companion.totalList

//import kau.holyjoon.cookingmemo.ingredientMainFragment.Companion.resultList

class EtcFragment : Fragment() {

    var dataload = 0

    var fastfoodList = arrayListOf<Ingredient>()
    var fishcakeList = arrayListOf<Ingredient>()
    var canList = arrayListOf<Ingredient>()
    var breadList = arrayListOf<Ingredient>()
    var ricecakeList = arrayListOf<Ingredient>()
    var liquidList = arrayListOf<Ingredient>()
    var icecreamList = arrayListOf<Ingredient>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.etcfragment, container, false)
        val bt_total_1 = view.findViewById<Button>(R.id.bt_total_etc_1)
        val bt_total_2 = view.findViewById<Button>(R.id.bt_total_etc_2)
        val bt_total_3 = view.findViewById<Button>(R.id.bt_total_etc_3)
        val bt_total_4 = view.findViewById<Button>(R.id.bt_total_etc_4)
        val bt_total_5 = view.findViewById<Button>(R.id.bt_total_etc_5)
        val bt_total_6 = view.findViewById<Button>(R.id.bt_total_etc_6)
        val bt_total_7 = view.findViewById<Button>(R.id.bt_total_etc_7)

        val etcRecyclerView1 = view.findViewById<RecyclerView>(R.id.Recyclerview_etc_1)
        val etcRecyclerView2 = view.findViewById<RecyclerView>(R.id.Recyclerview_etc_2)
        val etcRecyclerView3 = view.findViewById<RecyclerView>(R.id.Recyclerview_etc_3)
        val etcRecyclerView4 = view.findViewById<RecyclerView>(R.id.Recyclerview_etc_4)
        val etcRecyclerView5 = view.findViewById<RecyclerView>(R.id.Recyclerview_etc_5)
        val etcRecyclerView6 = view.findViewById<RecyclerView>(R.id.Recyclerview_etc_6)
        val etcRecyclerView7 = view.findViewById<RecyclerView>(R.id.Recyclerview_etc_7)

        val mAdapter1 = IngreAdapter(context!!, fastfoodList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }
        val mAdapter2 = IngreAdapter(context!!, fishcakeList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }
        val mAdapter3 = IngreAdapter(context!!, canList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }
        val mAdapter4 = IngreAdapter(context!!, breadList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }
        val mAdapter5 = IngreAdapter(context!!, ricecakeList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }
        val mAdapter6 = IngreAdapter(context!!, liquidList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }
        val mAdapter7 = IngreAdapter(context!!, icecreamList,
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

        etcRecyclerView1.adapter = mAdapter1
        etcRecyclerView1.layoutManager = lm1
        etcRecyclerView1.setHasFixedSize(true)
        etcRecyclerView1.addItemDecoration(IngredientDecoration())

        etcRecyclerView2.adapter = mAdapter2
        etcRecyclerView2.layoutManager = lm2
        etcRecyclerView2.setHasFixedSize(true)
        etcRecyclerView2.addItemDecoration(IngredientDecoration())

        etcRecyclerView3.adapter = mAdapter3
        etcRecyclerView3.layoutManager = lm3
        etcRecyclerView3.setHasFixedSize(true)
        etcRecyclerView3.addItemDecoration(IngredientDecoration())

        etcRecyclerView4.adapter = mAdapter4
        etcRecyclerView4.layoutManager = lm4
        etcRecyclerView4.setHasFixedSize(true)
        etcRecyclerView4.addItemDecoration(IngredientDecoration())

        etcRecyclerView5.adapter = mAdapter5
        etcRecyclerView5.layoutManager = lm5
        etcRecyclerView5.setHasFixedSize(true)
        etcRecyclerView5.addItemDecoration(IngredientDecoration())

        etcRecyclerView6.adapter = mAdapter6
        etcRecyclerView6.layoutManager = lm6
        etcRecyclerView6.setHasFixedSize(true)
        etcRecyclerView6.addItemDecoration(IngredientDecoration())

        etcRecyclerView7.adapter = mAdapter7
        etcRecyclerView7.layoutManager = lm7
        etcRecyclerView7.setHasFixedSize(true)
        etcRecyclerView7.addItemDecoration(IngredientDecoration())

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
                            fastfoodList.add(ing)
                            totalList.add(ing)
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
                            fishcakeList.add(ing)
                            totalList.add(ing)
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
                            canList.add(ing)
                            totalList.add(ing)
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
                            breadList.add(ing)
                            totalList.add(ing)

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
                            ricecakeList.add(ing)
                            totalList.add(ing)

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
                            liquidList.add(ing)
                            totalList.add(ing)

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
                            icecreamList.add(ing)
                            totalList.add(ing)

                        }
                    }
                    dataload++
                }
                mAdapter7.notifyDataSetChanged()
            }

        }
        val sort1 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("etc").child("fastfood")
            .addListenerForSingleValueEvent(postListener1)

        val sort2 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("etc").child("fishcake")
            .addListenerForSingleValueEvent(postListener2)

        val sort3 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("etc").child("can")
            .addListenerForSingleValueEvent(postListener3)

        val sort4 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("etc").child("bread")
            .addListenerForSingleValueEvent(postListener4)

        val sort5 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("etc").child("ricecake")
            .addListenerForSingleValueEvent(postListener5)

        val sort6 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("etc").child("liquid")
            .addListenerForSingleValueEvent(postListener6)

        val sort7 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("etc").child("icecream")
            .addListenerForSingleValueEvent(postListener7)


        return view
    }
}