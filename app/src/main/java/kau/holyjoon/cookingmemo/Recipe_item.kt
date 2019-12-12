package kau.holyjoon.cookingmemo

import android.os.Parcel
import android.os.Parcelable
//popup 창에서 만들어진 하나의 단계를 저장하는 class.
//재료는 있을수도 없을수도 있기에 ArrayList? 꼴. 나머지는 intent관련 문제로 null이 가능하게 해야 했기에 ? 를 붙임.
class Recipe_item(var ingredient: ArrayList<Ingredient>?, var howmake: String?, var cooktime:Int?, var comment:String?,
                  var isRunning : Int = 0, var changetime : Int = 0):Parcelable{
    //isRunning과 changetime 은 View에서 타이머 기능을 도와주는 역할을 함.


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
        cooktime?.let { parcel.writeInt(it) }
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
