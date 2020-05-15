package com.tr1984.mergeadaptersample.paging

import androidx.paging.PageKeyedDataSource
import com.tr1984.mergeadaptersample.model.People

class PeopleDataSource : PageKeyedDataSource<Int, People>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, People>
    ) {
        callback.onResult(fetch(1), null, 2)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, People>) {
        callback.onResult(fetch(params.key), params.key - 1)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, People>) {
        callback.onResult(fetch(params.key), params.key + 1)
    }

    private fun fetch(page: Int): List<People> {
        return arrayListOf<People>().apply {
            if (page <= 3) {
                for (i in 0 until 20) {
                    add(People("Name - ${20 * (page - 1) + i}"))
                }
            }
        }
    }
}