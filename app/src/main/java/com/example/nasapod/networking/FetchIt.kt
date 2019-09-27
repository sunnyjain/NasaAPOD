package com.example.nasapod.networking

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.nasapod.networking.FetchItKeys.LAST_FETCH_DATE
import com.example.nasapod.utils.Constants.PREFS
import java.text.SimpleDateFormat
import java.util.*


/**
 * A Singleton object to identify if a fetch operation should run or not.
 * */
object FetchIt {
    private var preferences: SharedPreferences? = null
    const val TAG = "FetchIt"

    private const val FETCH_IT = true
    private const val DONT_FETCH_IT = false
    /**
     * Initialize the preference object to read and write fetch operation date-times.
     **/
    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
    }

    fun shouldFetchAPODList(): Boolean {
        performPrefsSanityCheck()

        val currentDate = preferences?.getString(LAST_FETCH_DATE, "")

        val todaysDate = Calendar.getInstance()
        todaysDate.set(Calendar.HOUR_OF_DAY, 0)
        todaysDate.set(Calendar.MINUTE, 0)
        todaysDate.set(Calendar.SECOND, 0)
        todaysDate.set(Calendar.MILLISECOND, 0)
        todaysDate.add(Calendar.DAY_OF_YEAR, -1)

        //if there is not record of current date is found
        if(currentDate.isNullOrEmpty()) {
            //fetch records, that is current_date + 20 days.
            return  fetchIt(todaysDate.time)
        }

        //if todays date is larger than current date.
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        //so it will always be one day before not on the same day.

        if(todaysDate.time > sdf.parse(currentDate)) {
            return FETCH_IT
        }

        return DONT_FETCH_IT
    }

    fun updateLastFetchDate(date: Date) {
        saveCurrenDate(date)
    }

    private fun fetchIt(date: Date): Boolean {
        saveCurrenDate(date)
        return FETCH_IT
    }

    private fun saveCurrenDate(date: Date) {
        preferences
            ?.edit()
            ?.putString(LAST_FETCH_DATE,
                (SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())).format(date))
            ?.apply()
    }

    private fun performPrefsSanityCheck() {
        if (preferences == null)
            throw IllegalStateException("Make sure to init FetchIt")
    }
}