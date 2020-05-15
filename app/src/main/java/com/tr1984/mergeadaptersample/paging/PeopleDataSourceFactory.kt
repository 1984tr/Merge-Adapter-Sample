package com.tr1984.mergeadaptersample.paging

import androidx.paging.DataSource
import com.tr1984.mergeadaptersample.model.People

class PeopleDataSourceFactory : DataSource.Factory<Int, People>() {

    override fun create(): DataSource<Int, People> {
        return PeopleDataSource()
    }
}