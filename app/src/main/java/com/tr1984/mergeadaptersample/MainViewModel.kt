package com.tr1984.mergeadaptersample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import com.tr1984.mergeadaptersample.paging.PeopleDataSourceFactory

class MainViewModel : ViewModel() {

    private val _articleItems = MutableLiveData(listOf<String>())
    val articleItem: LiveData<List<String>>
        get() {
            return _articleItems
        }

    private val _feedItems = MutableLiveData(listOf<String>())
    val feedItem: LiveData<List<String>>
        get() {
            return _feedItems
        }

    val peopleItem by lazy {
        LivePagedListBuilder(PeopleDataSourceFactory(), 20).build()
    }

    fun fetch() {
        _articleItems.value = arrayListOf<String>().apply {
            for (i in 0 until 2) {
                add("Article:$i")
            }
        }

        _feedItems.value = arrayListOf<String>().apply {
            for (i in 0 until 2) {
                add("Feed:$i")
            }
        }
    }

    fun fetchArticle() {
        val items = arrayListOf<String>().apply {
            addAll(_articleItems.value ?: arrayListOf())
        }
        items.add("Article:${items.size}")
        _articleItems.value = items
    }

    fun fetchFeed() {
        val items = arrayListOf<String>().apply {
            addAll(_feedItems.value ?: arrayListOf())
        }
        if (items.isNotEmpty()) {
            items.removeAt(items.size - 1)
            _feedItems.value = items
        }
    }
}