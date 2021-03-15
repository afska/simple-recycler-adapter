package j3.simple_recycler_adapter

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

interface MutableListAdapter<T> {
    val emptyStateView: View?
    val items: MutableList<T>
    fun notifyItemInserted(position: Int)
    fun notifyItemRemoved(position: Int)
    fun notifyItemRangeInserted(start: Int, end: Int)
    fun notifyItemChanged(position: Int)
    fun notifyDataSetChanged()

    operator fun get(pos: Int): T? = items[pos]

    fun hasItems(): Boolean {
        return items.isNotEmpty()
    }

    fun populate(list: List<T>): MutableListAdapter<T> {
        this.items.clear()
        this.items.addAll(list)
        this.notifyDataSetChanged()
        if (items.isNotEmpty()) emptyStateView?.hide() else emptyStateView?.show()
        return this
    }

    fun addFirst(item: T) {
        this.items.add(0, item)
        notifyItemInserted(0)
    }

    fun addItem(item: T) {
        this.items.add(item)
        notifyItemInserted(items.lastIndex)
        if (items.isNotEmpty()) emptyStateView?.hide()
    }

    fun addItems(newItems: List<T>) {
        val oldSize = this.items.size
        this.items.addAll(newItems)
        this.notifyItemRangeInserted(oldSize, items.lastIndex)
        if (items.isNotEmpty()) emptyStateView?.hide()
    }

    fun removeItem(item: T) {
        val index = items.indexOf(item)
        if (index < 0) return
        items.removeAt(index)
        notifyItemRemoved(index)
        if (items.isEmpty()) emptyStateView?.show()
    }

    fun updateItem(item: T) {
        val index = items.indexOf(item)
        if (index < 0) return
        items.removeAt(index)
        items.add(index, item)
        notifyItemChanged(index)
    }
}

private fun View.show() {
    visibility = VISIBLE
}

private fun View.hide() {
    visibility = GONE
}