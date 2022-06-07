package com.feelsokman.androidtemplate.ui.activity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    val textData = MutableLiveData<String>().apply { postValue(UUID.randomUUID().toString()) }

    override fun onCleared() {
        Timber.d("MainViewModel cleared")
        super.onCleared()
    }
}
