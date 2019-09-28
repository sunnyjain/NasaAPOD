package com.example.nasapod.detail.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nasapod.commons.data.local.APODObject
import com.example.nasapod.detail.model.APODDEtailListDataContract
import com.example.nasapod.detail.model.APODDetailListRepository
import com.example.nasapod.extensions.toLiveData
import com.example.nasapod.list.model.APODListRepository
import com.example.nasapod.networking.Outcome
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class APODDetailListViewModel @Inject constructor(
    private val repository: APODDetailListRepository,
    private val preferences: SharedPreferences,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    val apodListFetchOutcome: LiveData<Outcome<List<APODObject>>> by lazy {
        repository.fetchAPODDetailListOutcome.toLiveData(compositeDisposable)
    }

    fun getAPODs(id: Long) {
        if (apodListFetchOutcome.value == null) {
            repository.fetchAPODDetailList(id)
        }
    }

    override fun onCleared() {
        super.onCleared()
        //clear the disposables when the viewmodel is cleared
        compositeDisposable.clear()
    }

}