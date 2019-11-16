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

class FruitFragment : Fragment() {
    var dataload = 0
    var fruitList = arrayListOf<Ingredient>()
    var leafList = arrayListOf<Ingredient>()
    var fvList = arrayListOf<Ingredient>()
    var rootList = arrayListOf<Ingredient>()
    var mushroomList = arrayListOf<Ingredient>()
    var herbList = arrayListOf<Ingredient>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fruitfragment, container, false)
        val bt_total_1 = view.findViewById<Button>(R.id.bt_total_fruit_1)
        val bt_total_2 = view.findViewById<Button>(R.id.bt_total_fruit_2)
        val bt_total_3 = view.findViewById<Button>(R.id.bt_total_fruit_3)
        val bt_total_4 = view.findViewById<Button>(R.id.bt_total_fruit_4)
        val bt_total_5 = view.findViewById<Button>(R.id.bt_total_fruit_5)
        val bt_total_6 = view.findViewById<Button>(R.id.bt_total_fruit_6)

        val fruitRecyclerView1 = view.findViewById<RecyclerView>(R.id.Recyclerview_fruit_1)
        val fruitRecyclerView2 = view.findViewById<RecyclerView>(R.id.Recyclerview_fruit_2)
        val fruitRecyclerView3 = view.findViewById<RecyclerView>(R.id.Recyclerview_fruit_3)
        val fruitRecyclerView4 = view.findViewById<RecyclerView>(R.id.Recyclerview_fruit_4)
        val fruitRecyclerView5 = view.findViewById<RecyclerView>(R.id.Recyclerview_fruit_5)
        val fruitRecyclerView6 = view.findViewById<RecyclerView>(R.id.Recyclerview_fruit_6)


        val mAdapter1 = IngreAdapter(context!!, fruitList,
            ingredientMainActivity.resultList
        ) { ingredient ->
            if (ingredient.source == null) {
                Toast.makeText(context, "이미 선택된 재료입니다.", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
        }
        val mAdapter2 = IngreAdapter(context!!, leafList,
            ingredientMainActivity.resultList
        ) { ingredient ->
            if (ingredient.source == null) {
                Toast.makeText(context, "이미 선택된 재료입니다.", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
        }
        val mAdapter3 = IngreAdapter(context!!, fvList,
            ingredientMainActivity.resultList
        ) { ingredient ->
            if (ingredient.source == null) {
                Toast.makeText(context, "이미 선택된 재료입니다.", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
        }
        val mAdapter4 = IngreAdapter(context!!, rootList,
            ingredientMainActivity.resultList
        ) { ingredient ->
            if (ingredient.source == null) {
                Toast.makeText(context, "이미 선택된 재료입니다.", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
        }
        val mAdapter5 = IngreAdapter(context!!, mushroomList,
            ingredientMainActivity.resultList
        ) { ingredient ->
            if (ingredient.source == null) {
                Toast.makeText(context, "이미 선택된 재료입니다.", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
        }
        val mAdapter6 = IngreAdapter(context!!, herbList,
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
        val lm5 = GridLayoutManager(context, 5)
        val lm6 = GridLayoutManager(context, 5)

        fruitRecyclerView1.adapter = mAdapter1
        fruitRecyclerView1.layoutManager = lm1
        fruitRecyclerView1.setHasFixedSize(true)
        fruitRecyclerView1.addItemDecoration(IngredientDecoration())

        fruitRecyclerView2.adapter = mAdapter2
        fruitRecyclerView2.layoutManager = lm2
        fruitRecyclerView2.setHasFixedSize(true)
        fruitRecyclerView2.addItemDecoration(IngredientDecoration())

        fruitRecyclerView3.adapter = mAdapter3
        fruitRecyclerView3.layoutManager = lm3
        fruitRecyclerView3.setHasFixedSize(true)
        fruitRecyclerView3.addItemDecoration(IngredientDecoration())

        fruitRecyclerView4.adapter = mAdapter4
        fruitRecyclerView4.layoutManager = lm4
        fruitRecyclerView4.setHasFixedSize(true)
        fruitRecyclerView4.addItemDecoration(IngredientDecoration())

        fruitRecyclerView5.adapter = mAdapter5
        fruitRecyclerView5.layoutManager = lm5
        fruitRecyclerView5.setHasFixedSize(true)
        fruitRecyclerView5.addItemDecoration(IngredientDecoration())

        fruitRecyclerView6.adapter = mAdapter6
        fruitRecyclerView6.layoutManager = lm6
        fruitRecyclerView6.setHasFixedSize(true)
        fruitRecyclerView6.addItemDecoration(IngredientDecoration())

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
        bt_total_5.setOnClickListener {
            if (mAdapter5.btSet == 0) {
                mAdapter5.btSet = 1

            } else {
                mAdapter5.btSet = 0
            }
            mAdapter5.notifyDataSetChanged()
        }
        bt_total_6.setOnClickListener {
            if (mAdapter6.btSet == 0) {
                mAdapter6.btSet = 1

            } else {
                mAdapter6.btSet = 0
            }
            mAdapter6.notifyDataSetChanged()
        }

        val postListener1 = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataload < 6) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            fruitList.add(ing)
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
                if(dataload < 6) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            leafList.add(ing)
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
                if(dataload < 6) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            fvList.add(ing)
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
                if(dataload < 6) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            rootList.add(ing)
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
                if(dataload < 6) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            mushroomList.add(ing)
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
                if(dataload < 6) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            herbList.add(ing)
                        }
                    }
                    dataload++
                }
                mAdapter6.notifyDataSetChanged()
            }

        }
        val sort1 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("fresh").child("fruit")
            .addListenerForSingleValueEvent(postListener1)

        val sort2 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("fresh").child("fruitvegi")
            .addListenerForSingleValueEvent(postListener2)

        val sort3 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("fresh").child("herb")
            .addListenerForSingleValueEvent(postListener3)

        val sort4 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("fresh").child("leaf")
            .addListenerForSingleValueEvent(postListener4)

        val sort5 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("fresh").child("mushroom")
            .addListenerForSingleValueEvent(postListener5)

        val sort6 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("fresh").child("root")
            .addListenerForSingleValueEvent(postListener6)


        return view
    }
}