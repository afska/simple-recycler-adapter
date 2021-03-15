package j3.simple_recycler_adapter


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun <T> RecyclerView.setup(
    adapter: SimpleRecyclerAdapter<T>,
    direction: RecyclerDirection = RecyclerDirection.VERTICAL,
    marginBetweenItems: Int = 0
): SimpleRecyclerAdapter<T> {
    if (itemDecorationCount > 0) removeItemDecorationAt(0)

    addItemDecoration(direction.decorator(marginBetweenItems))
    this.adapter = adapter
    this.layoutManager = LinearLayoutManager(context, direction.orientation(), false)

    return adapter
}

enum class RecyclerDirection {
    VERTICAL {
        override fun decorator(margin: Int) = VerticalMarginDecorator(margin)
        override fun orientation() = LinearLayoutManager.VERTICAL
    },
    HORIZONTAL {
        override fun decorator(margin: Int) = HorizontalMarginDecorator(margin)
        override fun orientation() = LinearLayoutManager.HORIZONTAL
    };

    abstract fun decorator(margin: Int): RecyclerView.ItemDecoration
    abstract fun orientation(): Int
}