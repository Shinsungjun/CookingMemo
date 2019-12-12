package kau.holyjoon.cookingmemo

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

//홈화면에 보여줄 데이터
class hRecipe(
    var name:String?,
    var img:String?,
    var folder:String?,
   var hrecipeList:ArrayList<Recipe_item>?):Parcelable//plus액티비티에서 받아온 각 요리단계들의 배열
{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(Recipe_item.CREATOR)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(img)
        parcel.writeString(folder)
        parcel.writeTypedList(hrecipeList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<hRecipe> {
        override fun createFromParcel(parcel: Parcel): hRecipe {
            return hRecipe(parcel)
        }

        override fun newArray(size: Int): Array<hRecipe?> {
            return arrayOfNulls(size)
        }
    }

}