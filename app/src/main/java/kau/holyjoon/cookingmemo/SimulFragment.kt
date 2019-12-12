package kau.holyjoon.cookingmemo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kau.holyjoon.cookingmemo.SimulActivity.Companion.timerun
import kotlinx.android.synthetic.main.simulfragment.*
import kotlinx.android.synthetic.main.simulfragment.view.*
import kotlin.concurrent.timer

class SimulFragment(val position : String, val totalsize : String, val recipe : Recipe_item) : Fragment() {  //Simul ViewPager에 찍힐 Fragment
    val mrecipe = recipe
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.simulfragment, container, false)

        val mAdapter = view_IngreAdapter(context!!, mrecipe.ingredient!!)
        val IngreRecyclerView = view.findViewById<RecyclerView>(R.id.simul_recycler)
        val lm1 = GridLayoutManager(context, 4)
        IngreRecyclerView.adapter = mAdapter
        IngreRecyclerView.layoutManager = lm1
        IngreRecyclerView.setHasFixedSize(true)
        IngreRecyclerView.addItemDecoration(IngredientDecoration())
        view.text_comment.text= recipe.comment
        view.text_howcook.text = recipe.howmake
        view.text_cooktime.text = "${(recipe.cooktime?.div(60)).toString()} : ${recipe.cooktime?.rem(60).toString()}"
        view.text_count.text = "${position}/${totalsize}"
        val mHandler = Handler(Looper.getMainLooper())
        if(timerun == position.toInt()) {
            timer(period = 1000) {
                recipe.cooktime = recipe.cooktime?.minus(1)
                recipe.changetime++
                mHandler.postDelayed(
                    { reload(view) },
                    0
                )
                if(recipe.cooktime == 0) {
                    timerun++
                    cancel()
                }
                if(timerun != position.toInt()) {
                    recipe.cooktime = recipe.cooktime?.plus(recipe.changetime)
                    recipe.changetime = 0
                    mHandler.postDelayed(
                        { reload(view) },
                        0
                    )
                    cancel()
                }

            }
        }
        return view
    }
    fun reload(view : View) {
        view.text_cooktime.text = "${(recipe.cooktime?.div(60)).toString()} : ${recipe.cooktime?.rem(60).toString()}"
    }
}