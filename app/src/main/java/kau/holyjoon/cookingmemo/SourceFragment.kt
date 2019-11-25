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

class SourceFragment : Fragment() {
    var dataload = 0
    var jangList = arrayListOf<Ingredient>()
    var oilList = arrayListOf<Ingredient>()
    var spiceList = arrayListOf<Ingredient>()
    var sourceList = arrayListOf<Ingredient>()
    var saltList = arrayListOf<Ingredient>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.sourcefragment, container, false)
        val bt_total_1 = view.findViewById<Button>(R.id.bt_total_source_1)
        val bt_total_2 = view.findViewById<Button>(R.id.bt_total_source_2)
        val bt_total_3 = view.findViewById<Button>(R.id.bt_total_source_3)
        val bt_total_4 = view.findViewById<Button>(R.id.bt_total_source_4)
        val bt_total_5 = view.findViewById<Button>(R.id.bt_total_source_5)

        val grainRecyclerView1 = view.findViewById<RecyclerView>(R.id.Recyclerview_source_1)
        val grainRecyclerView2 = view.findViewById<RecyclerView>(R.id.Recyclerview_source_2)
        val grainRecyclerView3 = view.findViewById<RecyclerView>(R.id.Recyclerview_source_3)
        val grainRecyclerView4 = view.findViewById<RecyclerView>(R.id.Recyclerview_source_4)
        val grainRecyclerView5 = view.findViewById<RecyclerView>(R.id.Recyclerview_source_5)


        val mAdapter1 = IngreAdapter(context!!, jangList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }
        val mAdapter2 = IngreAdapter(context!!, oilList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }
        val mAdapter3 = IngreAdapter(context!!, spiceList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }
        val mAdapter4 = IngreAdapter(context!!, sourceList,
            resultList) { ingredient ->
                if (ingredient.source == null) {
                    Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
            }
        val mAdapter5 = IngreAdapter(context!!, saltList,
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

        grainRecyclerView4.adapter = mAdapter4
        grainRecyclerView4.layoutManager = lm4
        grainRecyclerView4.setHasFixedSize(true)
        grainRecyclerView4.addItemDecoration(IngredientDecoration())

        grainRecyclerView5.adapter = mAdapter5
        grainRecyclerView5.layoutManager = lm5
        grainRecyclerView5.setHasFixedSize(true)
        grainRecyclerView5.addItemDecoration(IngredientDecoration())

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

        val postListener1 = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataload < 5) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            jangList.add(ing)
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
                if(dataload < 5) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            oilList.add(ing)
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
                if(dataload < 5) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            spiceList.add(ing)
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
                if(dataload < 5) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            sourceList.add(ing)
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
                if(dataload < 5) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            saltList.add(ing)
                        }
                    }
                    dataload++
                }
                mAdapter5.notifyDataSetChanged()
            }

        }

        val sort1 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("source").child("jang")
            .addListenerForSingleValueEvent(postListener1)

        val sort2 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("source").child("oil")
            .addListenerForSingleValueEvent(postListener2)

        val sort3 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("source").child("spice")
            .addListenerForSingleValueEvent(postListener3)

        val sort4 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("source").child("source")
            .addListenerForSingleValueEvent(postListener4)

        val sort5 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("source").child("salts")
            .addListenerForSingleValueEvent(postListener5)



        return view
    }
}