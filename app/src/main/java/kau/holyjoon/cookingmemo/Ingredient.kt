package kau.holyjoon.cookingmemo

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
class Ingredient(var name: String?, var source: String?) : Parcelable {  //가장 기본이 되는 Class, 재료의 이름과 src의 정보를 가지고 있다. src의 정보는 Firebase에 저장
    constructor() : this("","")
    fun toMap() : HashMap<String, String> {
        val result = HashMap<String,String>()
        result.put("name", name!!)
        result.put("source", source!!)
        return result
    }
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(source)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Ingredient> = object : Parcelable.Creator<Ingredient> {
            override fun createFromParcel(source: Parcel): Ingredient = Ingredient(source)
            override fun newArray(size: Int): Array<Ingredient?> = arrayOfNulls(size)
        }
    }
}