package com.tr1984.mergeadaptersample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tr1984.mergeadaptersample.MainViewModel
import com.tr1984.mergeadaptersample.PeopleViewModel
import com.tr1984.mergeadaptersample.R
import com.tr1984.mergeadaptersample.databinding.ItemPeopleBinding
import com.tr1984.mergeadaptersample.model.People

class PeopleAdapter(private val lifecycleOwner: LifecycleOwner, val viewModel: PeopleViewModel) :
    PagedListAdapter<People, PeopleAdapter.Holder>(diffCallback) {

    init {
        viewModel.peopleItem.observe(lifecycleOwner, Observer {
            submitList(it)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(DataBindingUtil.inflate(inflater, R.layout.item_people, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        getItem(position)?.run {
            holder.binding.viewModel = viewModel
            holder.binding.people = this
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_people
    }

    class Holder(val binding: ItemPeopleBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<People>() {

            override fun areItemsTheSame(oldItem: People, newItem: People): Boolean {
                return oldItem.liveName.value == newItem.liveName.value
            }

            override fun areContentsTheSame(oldItem: People, newItem: People): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }
}