package kau.holyjoon.cookingmemo

import android.os.Parcel
import android.os.Parcelable

//그리드뷰에 보여줄 데이터
class hRecipe(
    var name:String?,
    var img:String?,
   var ingredient: ArrayList<Ingredient?>?,
   var hrecipeList:ArrayList<Recipe>?) //home화면의 Recipe객체라는 뜻
{

}