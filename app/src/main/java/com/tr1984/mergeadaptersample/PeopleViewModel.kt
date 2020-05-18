package com.tr1984.mergeadaptersample

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import com.tr1984.mergeadaptersample.model.People
import com.tr1984.mergeadaptersample.paging.PeopleDataSourceFactory

class PeopleViewModel : ViewModel() {

    val peopleItem by lazy {
        LivePagedListBuilder(PeopleDataSourceFactory(), 20).build()
    }

    fun increaseCount(people: People) {
        people.increaseCount()
    }
}