package kau.holyjoon.cookingmemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.simulfragment.*
import kotlinx.android.synthetic.main.simulfragment.view.*

class SimulFragment(val title : String, val recipe : Recipe_item) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.simulfragment, container, false)
        val mrecipe = recipe
        val mAdapter = view_IngreAdapter(context!!, mrecipe.ingredient!!)
        val IngreRecyclerView = view.findViewById<RecyclerView>(R.id.simul_recycler)
        val lm1 = GridLayoutManager(context, 4)
        IngreRecyclerView.adapter = mAdapter
        IngreRecyclerView.layoutManager = lm1
        IngreRecyclerView.setHasFixedSize(true)
        IngreRecyclerView.addItemDecoration(IngredientDecoration())
        view.text_comment.text= recipe.comment
        view.text_howcook.text = recipe.howmake
        view.text_cooktime.text = recipe.cooktime.toString()

        return view
    }
}