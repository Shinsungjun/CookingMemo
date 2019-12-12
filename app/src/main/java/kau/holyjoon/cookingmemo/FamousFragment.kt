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
//import kau.holyjoon.cookingmemo.ingredientMainFragment.Companion.resultList
import kau.holyjoon.cookingmemo.ingredientMainActivity.Companion.resultList

class FamousFragment : Fragment() {

    var freshList = arrayListOf<Ingredient>()
    var meatList = arrayListOf<Ingredient>()
    var seafoodList = arrayListOf<Ingredient>()
    var yogurtList = arrayListOf<Ingredient>()
    var dataload = 0  //Viewpager의 특성상 자신과 좌우의 Fragment만 살리고 나머지는 죽였다가 다시 살리는 식이기 때문에,
    //다시 화면을 불러올 때마다 불렀던 재료들을 다시 로드하는 경우가 생김.
    //이를 dataload를 통해서 방지.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("Famous Fragment dataload : ${dataload}")
        val view: View = inflater.inflate(R.layout.famousfragment, container, false)
        val bt_total_1 = view.findViewById<Button>(R.id.bt_total_1)
        val bt_total_2 = view.findViewById<Button>(R.id.bt_total_2)
        val bt_total_3 = view.findViewById<Button>(R.id.bt_total_3)
        val bt_total_4 = view.findViewById<Button>(R.id.bt_total_4)

        val famousRecyclerView1 = view.findViewById<RecyclerView>(R.id.Recyclerview_ingre1)
        val famousRecyclerView2 = view.findViewById<RecyclerView>(R.id.Recyclerview_ingre2)
        val famousRecyclerView3 = view.findViewById<RecyclerView>(R.id.Recyclerview_ingre3)
        val famousRecyclerView4 = view.findViewById<RecyclerView>(R.id.Recyclerview_ingre4)


        val mAdapter1 = IngreAdapter(context!!, freshList, resultList) { ingredient ->
            if (ingredient.source == null) {
                Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
        }
        val mAdapter2 = IngreAdapter(context!!, meatList, resultList){ ingredient ->
            if (ingredient.source == null) {
                Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
        }
        val mAdapter3 = IngreAdapter(context!!, seafoodList, resultList) { ingredient ->
            if (ingredient.source == null) {
                Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
        }
        val mAdapter4 = IngreAdapter(context!!, yogurtList, resultList) { ingredient ->
            if (ingredient.source == null) {
                Toast.makeText(context, "${ingredient.name} 선택이 취소되었습니다.", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(context, "${ingredient.name} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
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
                mAdapter1.btSet = 1
                bt_total_1.text = "닫기"
            } else {
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
                if(dataload < 4) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            freshList.add(ing)
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
                if(dataload < 4) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            meatList.add(ing)
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
                if(dataload < 4) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            seafoodList.add(ing)
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
                if(dataload <4) {
                    for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                        val ing = postSnapshot.getValue(Ingredient::class.java)
                        if (ing != null) {
                            yogurtList.add(ing)
                        }
                    }
                    dataload++
                }
                mAdapter4.notifyDataSetChanged()
            }

        }
        //Firebase에서 데이터를 가져오는 명령
        val sort1 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("famous").child("fresh")
            .addListenerForSingleValueEvent(postListener1)
        val sort2 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("famous").child("meat")
            .addListenerForSingleValueEvent(postListener2)
        val sort3 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("famous").child("seafood")
            .addListenerForSingleValueEvent(postListener3)
        val sort4 = FirebaseDatabase.getInstance().getReference().child("ingredient").child("famous").child("yogurt")
            .addListenerForSingleValueEvent(postListener4)

        return view
    }
}
