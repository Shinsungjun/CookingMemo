package kau.holyjoon.cookingmemo

import android.os.Parcel
import android.os.Parcelable


//이 클래스는 PlusActivity (plus_popup)에서 지정된 정보, 여러 재료, 방법, 시간을 기록하고 넘겨주기 위한 클래스이다.
//재료의 정보를 넘기기 위해선 Popup_ingredients 에서 재료를 선택을 하고 Intent로 다시 PopupActivity에 넘기면 PopupActivity에서
//해당 클래스의 객체의 ingredients를 초기화시킨다.
//howmake에 해당하는 부분은 우선은 Edittext로, 추후에는 LinearLayout을 이용한 리스트를 제공하여 선택 할 수 있게 할 수 있다.
//cooktime은 timerAcitivity를 따로 만들어, 해당 Activity에서 시간을 지정하고 PlusActivity로 Intent를 넘겨 Recipe에 저장하여
//EditActivity로 넘길것이다.
//EditActivity에서는 조건을 만족하는 정보 (적어도 재료와 방법에 대한 데이터가 존재)가 넘어온다면 Recipe객체를 만들어 해당 데이터를 저장하고
//RecyclerView를 통해 보여준다.
class Recipe(var howmake : String?, var cooktime : String?,var comment : String?):Parcelable{  //시간은 null이 가능하게 아니면 그냥 0 으로 두고 null일 수 없게 만드는 것도 방법
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(howmake)
        parcel.writeString(cooktime)
        parcel.writeString(comment)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }


}