package kau.holyjoon.cookingmemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.famousfragment.*

class FamousFragment : Fragment() {
    var ingredientList = arrayListOf(Ingredient("milk", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/milk.jpg?alt=media&token=61a05de3-aec1-41dd-9697-c3045fbc30dc"),
    Ingredient("bread", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/bread.jpg?alt=media&token=835efe50-ff93-41ef-9177-8518e372dc3c"),
    Ingredient("egg", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/egg.jpg?alt=media&token=99254b1a-aac7-4c66-888e-c5df58d3a706"),
    Ingredient("sugar", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/sugar.jpg?alt=media&token=81401354-4a05-4d6e-86e4-e51f953c94bb"),Ingredient("milk", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/milk.jpg?alt=media&token=61a05de3-aec1-41dd-9697-c3045fbc30dc"),
        Ingredient("bread", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/bread.jpg?alt=media&token=835efe50-ff93-41ef-9177-8518e372dc3c"),
        Ingredient("egg", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/egg.jpg?alt=media&token=99254b1a-aac7-4c66-888e-c5df58d3a706"),
        Ingredient("sugar", "https://firebasestorage.googleapis.com/v0/b/kau-mobile-cookingapp.appspot.com/o/sugar.jpg?alt=media&token=81401354-4a05-4d6e-86e4-e51f953c94bb"))
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.famousfragment, container, false)
        val mAdapter = IngreAdapter(context!!, ingredientList)
        val famousRecyclerView = view.findViewById<RecyclerView>(R.id.famousRecyclerView)
        famousRecyclerView.adapter = mAdapter

        val lm = GridLayoutManager(context,5)
        famousRecyclerView.layoutManager = lm
        famousRecyclerView.setHasFixedSize(true)
        famousRecyclerView.addItemDecoration(IngredientDecoration())
        return view
    }
}