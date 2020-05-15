package com.tr1984.mergeadaptersample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tr1984.mergeadaptersample.MainViewModel
import com.tr1984.mergeadaptersample.R
import com.tr1984.mergeadaptersample.databinding.ItemFeedBinding

class FeedAdapter(private val lifecycleOwner: LifecycleOwner, viewModel: MainViewModel) :
    ListAdapter<String, FeedAdapter.Holder>(diffCallback) {

    init {
        viewModel.feedItem.observe(lifecycleOwner, Observer {
            submitList(it)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(DataBindingUtil.inflate(inflater, R.layout.item_feed, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
    }

    class Holder(val binding: ItemFeedBinding) : RecyclerView.ViewHolder(binding.root)

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