package com.example.nasapod.list.ui


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.nasapod.R
import com.example.nasapod.di.Injectable
import com.example.nasapod.commons.data.local.APODObject
import com.example.nasapod.list.viewmodel.APODListViewModel
import com.example.nasapod.navigation.NavigationCallback
import com.example.nasapod.networking.Outcome
import com.example.nasapod.viewmodel.NasaPODViewModelFactory
import com.example.nasapod.viewmodel.NasaPODViewModelFactory_Factory
import kotlinx.android.synthetic.main.fragment_podlist_view.*
import java.io.IOException
import java.lang.ClassCastException
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */

class PODListView : Fragment(), Injectable, APODListAdapter.APODItemClickListener {

    @Inject
    lateinit var adapter: APODListAdapter

    @Inject
    lateinit var viewModelFactory: NasaPODViewModelFactory

    private var callback: NavigationCallback? = null

    private var totalItemCount: Int = 0
    private var lastVisibleItem: Int = 0

    private val viewModel: APODListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(APODListViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            callback = context as NavigationCallback
        } catch (ex: ClassCastException) {
            throw ClassCastException(context.toString().plus("must implement NavigationCallback"))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_podlist_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        apod_list_view.layoutManager = GridLayoutManager(view.context, 2)
        adapter.clickListener = this
        apod_list_view.adapter = adapter
        apod_list_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                totalItemCount = recyclerView.layoutManager?.itemCount ?: 0
                lastVisibleItem = (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()

                if(!refreshLay.isRefreshing && totalItemCount < (lastVisibleItem + 3)) {
                    //loads more 20 records.
                    Log.e("reached", "rock bottom")
                    viewModel.loadMore()
                }
            }
        })
        refreshLay.setOnRefreshListener { viewModel.refreshAPODs() }

        viewModel
        viewModel.getAPODs()
        initiateDataListener()

    }

    override fun onAPODItemClick(obj: APODObject, position: Int) {
       //notify the activity to load the fragment.
        val bundle = Bundle(1)
        bundle.putLong("id", obj.id)
        callback?.onSelect(1, bundle)
    }

    private fun initiateDataListener() {
        //Observe the outcome and update state of the screen  accordingly
        viewModel.apodListFetchOutcome.observe(this, Observer<Outcome<List<APODObject>>> { outcome ->
            Log.d("List View", "initiateDataListener: $outcome")
            when (outcome) {

                is Outcome.Progress -> refreshLay.isRefreshing = outcome.loading

                is Outcome.Success -> {
                    Log.d("ListView", "initiateDataListener: Successfully loaded data")
                    adapter.updateRecords(outcome.data)
                    adapter.notifyDataSetChanged()
                }

                is Outcome.Failure -> {

                    if (outcome.e is IOException)
                        Toast.makeText(
                            context,
                            "Internet Required",
                            Toast.LENGTH_LONG
                        ).show()
                    else
                        Toast.makeText(
                            context,
                            "Something Went Wrong",
                            Toast.LENGTH_LONG
                        ).show()
                }

            }
        })
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }


   /* private fun createMutableList() : MutableList<APODObject> {
        return  mutableListOf(
            APODObject(
                "",
                "",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_3864.jpg"
                ,
                "",
                "",
                "M1: The Crab Nebula from Hubble",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_960.jpg"
            ),
            APODObject(
                "",
                "",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_3864.jpg"
                ,
                "",
                "",
                "M1: The Crab Nebula from Hubble",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_960.jpg"
            ),
            APODObject(
                "",
                "",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_3864.jpg"
                ,
                "",
                "",
                "M1: The Crab Nebula from Hubble",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_960.jpg"
            ),
            APODObject(
                "",
                "",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_3864.jpg"
                ,
                "",
                "",
                "M1: The Crab Nebula from Hubble",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_960.jpg"
            ),
            APODObject(
                "",
                "",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_3864.jpg"
                ,
                "",
                "",
                "M1: The Crab Nebula from Hubble",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_960.jpg"
            ),
            APODObject(
                "",
                "",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_3864.jpg"
                ,
                "",
                "",
                "M1: The Crab Nebula from Hubble",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_960.jpg"
            ),
            APODObject(
                "",
                "",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_3864.jpg"
                ,
                "",
                "",
                "M1: The Crab Nebula from Hubble",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_960.jpg"
            ),
            APODObject(
                "",
                "",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_3864.jpg"
                ,
                "",
                "",
                "M1: The Crab Nebula from Hubble",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_960.jpg"
            ),
            APODObject(
                "",
                "",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_3864.jpg"
                ,
                "",
                "",
                "M1: The Crab Nebula from Hubble",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_960.jpg"
            ),
            APODObject(
                "",
                "",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_3864.jpg"
                ,
                "",
                "",
                "M1: The Crab Nebula from Hubble",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_960.jpg"
            ),
            APODObject(
                "",
                "",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_3864.jpg"
                ,
                "",
                "",
                "M1: The Crab Nebula from Hubble",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_960.jpg"
            ),
            APODObject(
                "",
                "",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_3864.jpg"
                ,
                "",
                "",
                "M1: The Crab Nebula from Hubble",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_960.jpg"
            ),
            APODObject(
                "",
                "",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_3864.jpg"
                ,
                "",
                "",
                "M1: The Crab Nebula from Hubble",
                "https://apod.nasa.gov/apod/image/1809/CrabNebula_Hubble_960.jpg"
            )
        )

    }*/
}
