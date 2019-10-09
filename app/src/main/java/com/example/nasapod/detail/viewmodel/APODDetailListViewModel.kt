package com.example.nasapod.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nasapod.commons.data.local.APODObject
import com.example.nasapod.detail.model.APODDEtailListDataContract
import com.example.nasapod.detail.model.APODDetailListRepository
import com.example.nasapod.extensions.toLiveData
import com.example.nasapod.networking.Outcome
import com.example.nasapod.utils.Constants.BOTH
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class APODDetailListViewModel @Inject constructor(
    private val repository: APODDEtailListDataContract.Repository,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    val apodListFetchOutcome: LiveData<Outcome<List<APODObject>>> by lazy {
        repository.fetchAPODDetailListOutcome.toLiveData(compositeDisposable)
    }

    fun getAPODs(date: String, dir: Int = BOTH) {
        if (apodListFetchOutcome.value == null) {
            repository.fetchAPODDetailList(date, dir)
        } else {
            appendRecords(date, dir)
        }
    }

    private fun appendRecords(selectedDate: String, direction: Int) {
        repository.fetchAPODDetailList(selectedDate, direction)
    }

    override fun onCleared() {
        super.onCleared()
        //clear the disposables when the viewmodel is cleared
        compositeDisposable.clear()
    }

}