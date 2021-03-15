package j3.simple_recycler_adapter

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalMarginDecorator(marginDp: Int) : RecyclerView.ItemDecoration() {
    constructor(marginDp: Float) : this(marginDp.toInt())

    private var margin: Int = marginDp.asDp().toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            val position = parent.getChildAdapterPosition(view)
            left = if (position == 0) left else margin / 2
            right = if (position == parent.childCount) right else margin / 2
        }
    }
}

class VerticalMarginDecorator(marginDp: Int) : RecyclerView.ItemDecoration() {
    constructor(marginDp: Float) : this(marginDp.toInt())

    private var margin: Int = marginDp.asDp().toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            val position = parent.getChildAdapterPosition(view)
            top = if (position == 0) top else margin / 2
            bottom = if (position == parent.childCount) bottom else margin / 2
        }
    }
}

private fun Int.asDp(): Float = this * Resources.getSystem().displayMetrics.density