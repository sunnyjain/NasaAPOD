package com.example.nasapod.apods.list

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.lifecycle.Observer
import com.example.nasapod.commons.data.local.APODObject
import com.example.nasapod.commons.testing.SampleRecords
import com.example.nasapod.list.model.APODListDataContract
import com.example.nasapod.list.viewmodel.APODListViewModel
import com.example.nasapod.networking.FetchIt
import com.example.nasapod.networking.FetchItKeys.LAST_FETCH_DATE
import com.example.nasapod.networking.Outcome
import com.example.nasapod.utils.Constants.PREFS
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


/** Tests for [APODListViewModel]
 * */

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1], manifest = Config.NONE)
class APODListViewModelTest {
    //mocking stuff
    private lateinit var viewModelTest: APODListViewModel
    private val repo: APODListDataContract.Repository = mock()
    private lateinit var prefs: SharedPreferences
    private val outcome: Observer<Outcome<List<APODObject>>> = mock()
    private lateinit var context: Context

    @Before
    fun init() {
        context = RuntimeEnvironment.application as Context
        prefs = context.getSharedPreferences(PREFS, 0)
        viewModelTest = APODListViewModel(repo, prefs, CompositeDisposable())
        whenever(repo.fetchApodListOutCome).doReturn(PublishSubject.create())
        viewModelTest.apodListFetchOutcome.observeForever(outcome)

    }


    /**Test to check [APODListViewModel.getAPODs] triggers the respective
     * Repository [APODListDataContract.Repository.fetchAPODList]
     * & updates the liveData of the outcome [APODListViewModel.apodListFetchOutcome] which is pushed from
     * [APODListDataContract.Repository.fetchApodListOutCome]
     * */
    @Test
    fun testGetApodsSuccess() {
        viewModelTest.getAPODs()
        verify(repo).fetchAPODList()

        repo.fetchApodListOutCome.onNext(Outcome.loading(true))
        verify(outcome).onChanged(Outcome.loading(true))

        repo.fetchApodListOutCome.onNext(Outcome.loading(false))
        verify(outcome).onChanged(Outcome.loading(false))

        val data = listOf(SampleRecords.APODObject(1),SampleRecords.APODObject(2))
        repo.fetchApodListOutCome.onNext(Outcome.success(data))
        verify(outcome).onChanged(Outcome.success(data))
    }

    /**
     * Test that [APODListDataContract.Repository.fetchApodListOutCome] on exception passes exception to
     * live [APODListViewModel.apodListFetchOutcome]
     * */
    @Test
    fun testGetApdsError() {
        val exception = IOException()
        repo.fetchApodListOutCome.onNext(Outcome.failure(exception))
        verify(outcome).onChanged(Outcome.failure(exception))
    }


    /** Test when [FetchIt.shouldFetchAPODList] returns false. which means the [LAST_FETCH_DATE] is todays date or larger. therefore no sync required*/
    @Test
    fun testRefreshListWhenLastFetchDateIsTodaysDate() {
        FetchIt.init(context)
        prefs.edit().putString(LAST_FETCH_DATE, SimpleDateFormat("yyyy-MM-dd").format(Date())).apply()
        viewModelTest.refreshAPODs()
        verify(outcome).onChanged(Outcome.loading(false))
    }

    /** Test when [FetchIt.shouldFetchAPODList] returns true. which means the [LAST_FETCH_DATE] is not todays date.
     * therefore find the difference of the dates and send it into the arguments.
     * */
    @Test
    fun testRefreshListWhenLastFetchDateIsNotTodaysDate() {
        FetchIt.init(context)
        prefs.edit().putString(LAST_FETCH_DATE, "2019-09-20").apply()
        viewModelTest.refreshAPODs()
        verify(repo).refereshAPODList("2019-09-21","2019-10-01")
    }

    /**
     *
     * Verify [APODListViewModel.loadMore] triggers [APODListDataContract.Repository.updateStartAndEndIndexes]
     * */
    @Test
    fun testLoadMore() {
        viewModelTest.loadMore()
        verify(repo).updateStartAndEndIndexes()
    }

}