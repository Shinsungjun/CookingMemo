package kau.holyjoon.cookingmemo

import android.os.Parcel
import android.os.Parcelable

//그리드뷰에 보여줄 데이터
class hRecipe(
    var name:String?,
    var img:String?,
   var hrecipeList:ArrayList<Recipe_item>?):Parcelable//home화면의 Recipe객체라는 뜻
{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(Recipe_item.CREATOR)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(hrecipeList)
        parcel.writeString(name)
        parcel.writeString(img)
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