package com.example.nasapod.detail.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.example.nasapod.R
import com.example.nasapod.commons.data.local.APODObject
import com.example.nasapod.detail.viewmodel.APODDetailListViewModel
import com.example.nasapod.di.Injectable
import com.example.nasapod.networking.Outcome
import com.example.nasapod.utils.Constants.BOTH
import com.example.nasapod.utils.Constants.LEFT
import com.example.nasapod.utils.Constants.RIGHT
import com.example.nasapod.viewmodel.NasaPODViewModelFactory
import kotlinx.android.synthetic.main.fragment_apoddetail_view.*
import java.io.IOException
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class APODDetailView : Fragment(), Injectable {


    @Inject
    lateinit var adapter: APODDetailListApdapter

    @Inject
    lateinit var viewModelFactory: NasaPODViewModelFactory


    private val viewModel: APODDetailListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(APODDetailListViewModel::class.java)
    }

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

        apod_detail_list.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (adapter.apods.last() == adapter.apods[position]) {
                    viewModel.getAPODs(adapter.apods[position].date, RIGHT)
                }
                if (adapter.apods[0] == adapter.apods[position]) {
                    viewModel.getAPODs(adapter.apods[position].date, LEFT)
                }
            }
        })
        viewModel.getAPODs(arguments?.getString("date") ?: "")
        initiateDataListener()

    }

    private fun initiateDataListener() {
        //Observe the outcome and update state of the screen  accordingly
        viewModel.apodListFetchOutcome.observe(
            this,
            Observer<Outcome<List<APODObject>>> { outcome ->
                Log.d("List View", "initiateDataListener: $outcome")
                when (outcome) {

                    is Outcome.Progress -> Log.d("Loading", "loading")

                    is Outcome.SuccessWithDirection -> {
                        when (outcome.direction) {
                            BOTH -> {
                                adapter.apods.addAll(outcome.data)
                                apod_detail_list.setCurrentItem(
                                    adapter.apods.indexOf(adapter.apods
                                        .find { it.date == arguments?.getString("date", "") }),
                                    false
                                )
                                adapter.notifyDataSetChanged()
                            }
                            RIGHT -> {
                                adapter.apods.addAll(outcome.data)
                                adapter.notifyDataSetChanged()
                            }
                            LEFT -> {
                                //seems like a hack
                                val firstElementRecord = adapter.apods[0]
                                for (apodObject in outcome.data.reversed()) {
                                    adapter.apods.add(0, apodObject)
                                }
                                //move to the last position you were in.
                                apod_detail_list.setCurrentItem((adapter.apods.indexOf(adapter.apods
                                    .find { it.date == firstElementRecord.date })), false)
                                adapter.notifyDataSetChanged()
                            }
                        }
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
}
