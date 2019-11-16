package kau.holyjoon.cookingmemo

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

class Recipe_item(var ingredient: ArrayList<Ingredient?>?, var howmake: String?, var cooktime:String?, var comment:String?) {}
//var ingredient:ArrayList<Ingredient?> 으로 해야함
