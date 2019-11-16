package kau.holyjoon.cookingmemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast


class ViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)


        val name = findViewById<TextView>(R.id.text_cook)
        var ingredients = findViewById<TextView>(R.id.ingredients)

        var timer = findViewById<TextView>(R.id.text_timer)

        val recipelist: ArrayList<Recipe>? =
            Intent().getParcelableArrayListExtra("recipe")

        val ingredientlist: ArrayList<Ingredient>? =
            Intent().getParcelableArrayListExtra("ingredient")

        if(recipelist!=null) {
            Toast.makeText(this, recipelist[0].howmake, Toast.LENGTH_SHORT)
                .show()
        }

        //Toast.makeText(this, ingredientlist?.get(0)?.name.toString(), Toast.LENGTH_SHORT)
         //   .show()

        name.text = intent.getStringExtra("name")





    }



}
