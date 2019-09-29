package com.example.nasapod.list.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nasapod.commons.data.local.APODObject
import com.example.nasapod.extensions.loading
import com.example.nasapod.extensions.toLiveData
import com.example.nasapod.list.model.APODListRepository
import com.example.nasapod.networking.FetchIt
import com.example.nasapod.networking.FetchItKeys.LAST_FETCH_DATE
import com.example.nasapod.networking.Outcome
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class APODListViewModel @Inject constructor(
    private val repository: APODListRepository,
    private val preferences: SharedPreferences,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    val apodListFetchOutcome: LiveData<Outcome<List<APODObject>>> by lazy {
        repository.fetchApodListOutCome.toLiveData(compositeDisposable)
    }

    fun getAPODs() {
        if (apodListFetchOutcome.value == null) {
            repository.setStartAndEndDate()
            repository.fetchAPODList() //first 20 records
        }
    }

    fun refreshAPODs() {
        if (FetchIt.shouldFetchAPODList()) {
            //actually find the date
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, -1)
            val todaysDate =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
            FetchIt.updateLastFetchDate(calendar.time)
            calendar.time = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(
                preferences.getString(
                    LAST_FETCH_DATE, ""
                ) ?: ""
            )!!

            calendar.add(Calendar.DAY_OF_YEAR, -1)
            val lastFetchDate =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)

            //this means there is difference and we will get some updated new records.
            repository.refereshAPODList(todaysDate, lastFetchDate)

        } else {
            repository.fetchApodListOutCome.loading(false)
        }
    }

    fun loadMore() {
        repository.updateStartAndEndIndexes()
    }

    override fun onCleared() {
        super.onCleared()
        //clear the disposables when the viewmodel is cleared
        compositeDisposable.clear()
    }
}