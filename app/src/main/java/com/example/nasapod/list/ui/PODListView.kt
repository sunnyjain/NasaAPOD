package com.example.nasapod.list.ui


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.example.nasapod.R
import com.example.nasapod.di.Injectable
import com.example.nasapod.commons.data.local.APODObject
import com.example.nasapod.navigation.NavigationCallback
import kotlinx.android.synthetic.main.fragment_podlist_view.*
import java.lang.ClassCastException
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
/*EVKE5uRkDPRdHIZwSgirofMweG2MaB6YMuWBgGtn*/
class PODListView : Fragment(), Injectable, APODListAdapter.APODItemClickListener {

    @Inject
    lateinit var adapter: APODListAdapter

    private var callback: NavigationCallback? = null

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
        adapter.apods = createMutableList()
        adapter.notifyDataSetChanged()
    }

    override fun onAPODItemClick(obj: APODObject, position: Int) {
       //notify the activity to load the fragment.
        val bundle = Bundle(1)
        bundle.putInt("position", position)
        callback?.onSelect(1, bundle)
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }


    private fun createMutableList() : MutableList<APODObject> {
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

    }
}
