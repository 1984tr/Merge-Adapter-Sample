package com.tr1984.mergeadaptersample.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class People(name: String) {
    private val _name = MutableLiveData(name)
    val liveName: LiveData<String>
        get() {
            return _name
        }

    private val _status = MutableLiveData("")
    val liveStatus: LiveData<String>
        get() {
            return _status
        }

    private var count = 0

    fun increaseCount() {
        count++
        _status.value = "Clicked: $count"
    }
}