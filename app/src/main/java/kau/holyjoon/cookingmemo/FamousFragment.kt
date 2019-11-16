package kau.holyjoon.cookingmemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kau.holyjoon.cookingmemo.ingredientMainActivity.Companion.resultList

class FamousFragment : Fragment() {

    var ingredientList = arrayListOf(Ingredient("milk", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/milk.jpg?alt=media&token=61a05de3-aec1-41dd-9697-c3045fbc30dc"),
    Ingredient("bread", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/bread.jpg?alt=media&token=835efe50-ff93-41ef-9177-8518e372dc3c"),
    Ingredient("egg", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/egg.jpg?alt=media&token=99254b1a-aac7-4c66-888e-c5df58d3a706"),
    Ingredient("sugar", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/sugar.jpg?alt=media&token=81401354-4a05-4d6e-86e4-e51f953c94bb"),Ingredient("milk", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/milk.jpg?alt=media&token=61a05de3-aec1-41dd-9697-c3045fbc30dc"),
        Ingredient("bread", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/bread.jpg?alt=media&token=835efe50-ff93-41ef-9177-8518e372dc3c"),
        Ingredient("egg", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/egg.jpg?alt=media&token=99254b1a-aac7-4c66-888e-c5df58d3a706"),
        Ingredient("sugar", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/sugar.jpg?alt=media&token=81401354-4a05-4d6e-86e4-e51f953c94bb"),
        Ingredient("milk", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/milk.jpg?alt=media&token=61a05de3-aec1-41dd-9697-c3045fbc30dc"),
        Ingredient("bread", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/bread.jpg?alt=media&token=835efe50-ff93-41ef-9177-8518e372dc3c"),
        Ingredient("egg", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/egg.jpg?alt=media&token=99254b1a-aac7-4c66-888e-c5df58d3a706"),
        Ingredient("sugar", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/sugar.jpg?alt=media&token=81401354-4a05-4d6e-86e4-e51f953c94bb"),
        Ingredient("bread", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/bread.jpg?alt=media&token=835efe50-ff93-41ef-9177-8518e372dc3c"),
        Ingredient("egg", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/egg.jpg?alt=media&token=99254b1a-aac7-4c66-888e-c5df58d3a706"),
        Ingredient("sugar", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/sugar.jpg?alt=media&token=81401354-4a05-4d6e-86e4-e51f953c94bb"),
        Ingredient("bread", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/bread.jpg?alt=media&token=835efe50-ff93-41ef-9177-8518e372dc3c"),
        Ingredient("egg", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/egg.jpg?alt=media&token=99254b1a-aac7-4c66-888e-c5df58d3a706"),
        Ingredient("sugar", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/sugar.jpg?alt=media&token=81401354-4a05-4d6e-86e4-e51f953c94bb"),
        Ingredient("bread", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/bread.jpg?alt=media&token=835efe50-ff93-41ef-9177-8518e372dc3c"),
        Ingredient("egg", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/egg.jpg?alt=media&token=99254b1a-aac7-4c66-888e-c5df58d3a706"),
        Ingredient("sugar", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/sugar.jpg?alt=media&token=81401354-4a05-4d6e-86e4-e51f953c94bb"))
    var ingredientList2 = arrayListOf(Ingredient("milk", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/milk.jpg?alt=media&token=61a05de3-aec1-41dd-9697-c3045fbc30dc"),
        Ingredient("bread", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/bread.jpg?alt=media&token=835efe50-ff93-41ef-9177-8518e372dc3c"),
        Ingredient("egg", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/egg.jpg?alt=media&token=99254b1a-aac7-4c66-888e-c5df58d3a706"),
        Ingredient("sugar", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/sugar.jpg?alt=media&token=81401354-4a05-4d6e-86e4-e51f953c94bb"),Ingredient("milk", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/milk.jpg?alt=media&token=61a05de3-aec1-41dd-9697-c3045fbc30dc"),
        Ingredient("bread", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/bread.jpg?alt=media&token=835efe50-ff93-41ef-9177-8518e372dc3c"),
        Ingredient("egg", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/egg.jpg?alt=media&token=99254b1a-aac7-4c66-888e-c5df58d3a706"),
        Ingredient("sugar", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/sugar.jpg?alt=media&token=81401354-4a05-4d6e-86e4-e51f953c94bb"),
        Ingredient("egg", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/egg.jpg?alt=media&token=99254b1a-aac7-4c66-888e-c5df58d3a706"),
        Ingredient("sugar", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/sugar.jpg?alt=media&token=81401354-4a05-4d6e-86e4-e51f953c94bb"),
        Ingredient("milk", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/milk.jpg?alt=media&token=61a05de3-aec1-41dd-9697-c3045fbc30dc"),
        Ingredient("bread", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/bread.jpg?alt=media&token=835efe50-ff93-41ef-9177-8518e372dc3c"),
        Ingredient("egg", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/egg.jpg?alt=media&token=99254b1a-aac7-4c66-888e-c5df58d3a706"),
        Ingredient("sugar", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/sugar.jpg?alt=media&token=81401354-4a05-4d6e-86e4-e51f953c94bb"),Ingredient("milk", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/milk.jpg?alt=media&token=61a05de3-aec1-41dd-9697-c3045fbc30dc"),
        Ingredient("bread", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/bread.jpg?alt=media&token=835efe50-ff93-41ef-9177-8518e372dc3c"),
        Ingredient("egg", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/egg.jpg?alt=media&token=99254b1a-aac7-4c66-888e-c5df58d3a706"))
    var ingredientList3 = arrayListOf(Ingredient("milk", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/milk.jpg?alt=media&token=61a05de3-aec1-41dd-9697-c3045fbc30dc"),
        Ingredient("bread", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/bread.jpg?alt=media&token=835efe50-ff93-41ef-9177-8518e372dc3c"),
        Ingredient("egg", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/egg.jpg?alt=media&token=99254b1a-aac7-4c66-888e-c5df58d3a706"),
        Ingredient("sugar", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/sugar.jpg?alt=media&token=81401354-4a05-4d6e-86e4-e51f953c94bb"),Ingredient("milk", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/milk.jpg?alt=media&token=61a05de3-aec1-41dd-9697-c3045fbc30dc"),
        Ingredient("bread", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/bread.jpg?alt=media&token=835efe50-ff93-41ef-9177-8518e372dc3c"),
        Ingredient("egg", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/egg.jpg?alt=media&token=99254b1a-aac7-4c66-888e-c5df58d3a706"),
        Ingredient("sugar", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/sugar.jpg?alt=media&token=81401354-4a05-4d6e-86e4-e51f953c94bb"),
        Ingredient("egg", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/egg.jpg?alt=media&token=99254b1a-aac7-4c66-888e-c5df58d3a706"),
        Ingredient("sugar", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/sugar.jpg?alt=media&token=81401354-4a05-4d6e-86e4-e51f953c94bb"),
        Ingredient("sugar", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/sugar.jpg?alt=media&token=81401354-4a05-4d6e-86e4-e51f953c94bb"),Ingredient("milk", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/milk.jpg?alt=media&token=61a05de3-aec1-41dd-9697-c3045fbc30dc"),
        Ingredient("bread", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/bread.jpg?alt=media&token=835efe50-ff93-41ef-9177-8518e372dc3c"),
        Ingredient("egg", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/egg.jpg?alt=media&token=99254b1a-aac7-4c66-888e-c5df58d3a706"),
        Ingredient("sugar", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/sugar.jpg?alt=media&token=81401354-4a05-4d6e-86e4-e51f953c94bb"),
        Ingredient("egg", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/egg.jpg?alt=media&token=99254b1a-aac7-4c66-888e-c5df58d3a706"),
        Ingredient("sugar", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/sugar.jpg?alt=media&token=81401354-4a05-4d6e-86e4-e51f953c94bb"))
    var ingredientList112 = arrayListOf<Ingredient>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.famousfragment, container, false)
        val bt_total_1 = view.findViewById<Button>(R.id.bt_total_1)
        val bt_total_2 = view.findViewById<Button>(R.id.bt_total_2)
        val bt_total_3 = view.findViewById<Button>(R.id.bt_total_3)
        val bt_total_4 = view.findViewById<Button>(R.id.bt_total_4)

        val famousRecyclerView1 = view.findViewById<RecyclerView>(R.id.Recyclerview_ingre1)
        val famousRecyclerView2 = view.findViewById<RecyclerView>(R.id.Recyclerview_ingre2)
        val famousRecyclerView3 = view.findViewById<RecyclerView>(R.id.Recyclerview_ingre3)
        val famousRecyclerView4 = view.findViewById<RecyclerView>(R.id.Recyclerview_ingre4)


        var mAdapter1 = IngreAdapter(context!!, ingredientList112, resultList) { ingredient ->
            if (ingredient.source == null) {
                Toast.makeText(context, "이미 선택된 재료입니다.", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
        }
        var mAdapter2 = IngreAdapter(context!!, ingredientList2, resultList) { ingredient ->
            Toast.makeText(context, "${ingredient.name}", Toast.LENGTH_LONG).show()
        }
        var mAdapter3 = IngreAdapter(context!!, ingredientList3, resultList) { ingredient ->
            Toast.makeText(context, "${ingredient.name}", Toast.LENGTH_LONG).show()
        }
        var mAdapter4 = IngreAdapter(context!!, ingredientList, resultList) { ingredient ->
            Toast.makeText(context, "${ingredient.name}", Toast.LENGTH_LONG).show()
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

        val postListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                    val ing = postSnapshot.getValue(Ingredient::class.java)
                    if (ing != null) {
                        ingredientList112.add(ing)
                    }
                }
                mAdapter1.notifyDataSetChanged()
            }

        }
        val sort = FirebaseDatabase.getInstance().getReference().child("ingredient").child("etc").child("liquid")
            .addListenerForSingleValueEvent(postListener)
        return view
    }
}
