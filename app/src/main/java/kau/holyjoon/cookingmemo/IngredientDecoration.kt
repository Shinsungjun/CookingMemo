package kau.holyjoon.cookingmemo

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class IngredientDecoration() : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        var position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        if(position==0 || position == 1) {
            outRect.top = 3
            outRect.bottom = 3
        }
        else outRect.bottom= 3
    }
}