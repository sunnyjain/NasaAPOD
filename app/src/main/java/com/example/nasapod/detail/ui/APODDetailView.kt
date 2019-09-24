package com.example.nasapod.detail.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nasapod.R
import com.example.nasapod.di.Injectable
import com.example.nasapod.commons.data.local.APODObject
import kotlinx.android.synthetic.main.fragment_apoddetail_view.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class APODDetailView : Fragment(), Injectable {

    @Inject
    lateinit var adapter: APODDetailListApdapter

    companion object {
        fun newInstance(bundle: Bundle): APODDetailView {
            val frag = APODDetailView()
            frag.arguments = bundle
            return frag
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_apoddetail_view, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        apod_detail_list.adapter = adapter
        adapter.apods = createMutableList()

        apod_detail_list.setCurrentItem(arguments?.getInt("position") ?: 0, false)
        adapter.notifyDataSetChanged()
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
