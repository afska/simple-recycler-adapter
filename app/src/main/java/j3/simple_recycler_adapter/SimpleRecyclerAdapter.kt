package j3.simple_recycler_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SimpleRecyclerAdapter<T>(
    private val itemLayoutResource: Int,
    override val emptyStateView: View? = null,
    private val itemSetter: (View, T, SimpleRecyclerAdapter<T>) -> Unit
) : RecyclerView.Adapter<SimpleViewHolder<T>>(), MutableListAdapter<T> {
    override var items: MutableList<T> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder<T> {
        return SimpleViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(itemLayoutResource, parent, false), itemSetter, this
        )
    }

    override fun onBindViewHolder(holder: SimpleViewHolder<T>, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    override fun getItemCount(): Int = items.size
}

open class SimpleViewHolder<T>(
    val view: View,
    val itemSetter: (View, T, SimpleRecyclerAdapter<T>) -> Unit,
    private val adapter: SimpleRecyclerAdapter<T>
) : RecyclerView.ViewHolder(view) {
    open fun setItem(item: T) {
        itemSetter(view, item, adapter)
    }
}