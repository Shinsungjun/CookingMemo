package kau.holyjoon.cookingmemo

import android.os.Parcel
import android.os.Parcelable

class Recipe_item(var ingredient: ArrayList<Ingredient?>?, var howmake: String?, var cooktime:String?, var comment:String?):Parcelable{


    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Ingredient.CREATOR),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(ingredient)
        parcel.writeString(howmake)
        parcel.writeString(cooktime)
        parcel.writeString(comment)

    }

    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<Recipe_item> {
        override fun createFromParcel(parcel: Parcel): Recipe_item {
            return Recipe_item(parcel)
        }



        override fun newArray(size: Int): Array<Recipe_item?> {
            return arrayOfNulls(size)
        }
    }
}
//var ingredient:ArrayList<Ingredient?> 으로 해야함
