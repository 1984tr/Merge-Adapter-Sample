package com.tr1984.mergeadaptersample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tr1984.mergeadaptersample.model.People

class PeopleViewModel(val people: People) {

    private val _name = MutableLiveData("")
    val name: LiveData<String>
        get() {
            return _name
        }

    private val _status = MutableLiveData("")
    val status: LiveData<String>
        get() {
            return _status
        }

    private var count = 0

    init {
        _name.value = people.name
        _status.value = "Clicked: $count"
    }

    fun increaseCount() {
        count++
        _status.value = "Clicked: $count"
    }
}