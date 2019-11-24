package kau.holyjoon.cookingmemo

import android.os.Parcel
import android.os.Parcelable

class Recipe_item(var ingredient: ArrayList<Ingredient>?, var howmake: String?, var cooktime:Int, var comment:String?, var isRunning : Int = 0, var changetime : Int = 0):Parcelable{


    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Ingredient.CREATOR),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(ingredient)
        parcel.writeString(howmake)
        parcel.writeInt(cooktime)
        parcel.writeString(comment)
        parcel.writeInt(isRunning)
        parcel.writeInt(changetime)
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
