package kau.holyjoon.cookingmemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.choice_ingredient_item.*

class Choice_Ingredient_Item : Fragment() {  //item Fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.choice_ingredient_item, container, false)
        val adapter = Choice_Adapter(requireContext(),ingredients)
        view.findViewById<GridView>(R.id.item_choice_grid).adapter = adapter

        return view
    }
    var ingredients = arrayOf(Ingredient("milk","gs://kau-mobile-cookingapp.appspot.com/milk.jpg"),
        Ingredient("bread","gs://kau-mobile-cookingapp.appspot.com/bread.jpg"),
        Ingredient("egg","gs://kau-mobile-cookingapp.appspot.com/egg.jpg"),
        Ingredient("sugar","gs://kau-mobile-cookingapp.appspot.com/sugar.jpg"))


}