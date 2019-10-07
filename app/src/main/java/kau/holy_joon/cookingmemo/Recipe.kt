package kau.holy_joon.cookingmemo


//이 클래스는 PlusActivity (plus_popup)에서 지정된 정보, 여러 재료, 방법, 시간을 기록하고 넘겨주기 위한 클래스이다.
//재료의 정보를 넘기기 위해선 Popup_ingredients 에서 재료를 선택을 하고 Intent로 다시 PopupActivity에 넘기면 PopupActivity에서
//해당 클래스의 객체의 ingredients를 초기화시킨다.
//howmake에 해당하는 부분은 우선은 Edittext로, 추후에는 LinearLayout을 이용한 리스트를 제공하여 선택 할 수 있게 할 수 있다.
//cooktime은 timerAcitivity를 따로 만들어, 해당 Activity에서 시간을 지정하고 PlusActivity로 Intent를 넘겨 Recipe에 저장하여
//EditActivity로 넘길것이다.
//EditActivity에서는 조건을 만족하는 정보 (적어도 재료와 방법에 대한 데이터가 존재)가 넘어온다면 Recipe객체를 만들어 해당 데이터를 저장하고
//RecyclerView를 통해 보여준다.
class Recipe(ingredients : Array<String>,comment : String, howmake : String, cooktime : Int?) {  //시간은 null이 가능하게 아니면 그냥 0 으로 두고 null일 수 없게 만드는 것도 방법
    var ingredients :Array<String> = ingredients
    var howmake : String = howmake
    var cooktime : Int? = cooktime  //요리에 있어, 재료와 조리법이 존재하지 않을 수 없기 때문에 ingredients와 howmake는 null이 될 수 없다.


}