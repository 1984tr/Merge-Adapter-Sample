package com.tr1984.mergeadaptersample.adapter.horizontal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tr1984.mergeadaptersample.R
import com.tr1984.mergeadaptersample.databinding.ItemHorizontalContainerBinding

class HorizontalContainerAdapter(private val lifecycleOwner: LifecycleOwner, val adapter : ListAdapter<*, *>) :
    ListAdapter<String, HorizontalContainerAdapter.Holder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(DataBindingUtil.inflate(inflater, R.layout.item_horizontal_container, parent, false), adapter)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_horizontal_container
    }

    override fun getItemCount(): Int {
         return 1
    }

    class Holder(val binding: ItemHorizontalContainerBinding, adapter: ListAdapter<*, *>) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.recyclerView.layoutManager = LinearLayoutManager(binding.recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerView.adapter = adapter
        }
    }

    companion object {
        val diffCallback by lazy {
            object :
                DiffUtil.ItemCallback<String>() {

                override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                    return oldItem == newItem
                }
            }
        }
    }
}