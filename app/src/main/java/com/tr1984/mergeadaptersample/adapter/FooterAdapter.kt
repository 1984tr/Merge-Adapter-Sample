package com.tr1984.mergeadaptersample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tr1984.mergeadaptersample.R
import com.tr1984.mergeadaptersample.databinding.ItemFooterBinding

class FooterAdapter(val lifecycleOwner: LifecycleOwner, private val label: String, private val actionAll: () -> Unit) :
    ListAdapter<String, FooterAdapter.Holder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(
            DataBindingUtil.inflate(inflater, R.layout.item_footer, parent, false),
            actionAll
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        holder.binding.lblAction.text = label
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_footer
    }

    override fun getItemCount() = 1

    class Holder(val binding: ItemFooterBinding, actionAll: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.container.setOnClickListener {
                actionAll.invoke()
            }
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