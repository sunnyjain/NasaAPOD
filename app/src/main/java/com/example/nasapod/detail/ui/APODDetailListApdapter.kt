package com.example.nasapod.detail.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nasapod.R
import com.example.nasapod.commons.data.local.APODObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_detail_view.view.*
import javax.inject.Inject


class APODDetailListApdapter @Inject constructor(private val picasso: Picasso, private val apodDetailView: APODDetailView)
    :RecyclerView.Adapter<APODDetailListApdapter.APODItemViewHolder>() {

    var apods: MutableList<APODObject> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = APODItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_detail_view, parent, false))

    override fun getItemCount() = apods.size


    override fun onBindViewHolder(holder: APODItemViewHolder, position: Int) {
        holder.bind(apods[position], picasso)
    }


    inner class APODItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: APODObject, picasso: Picasso) {

            picasso.load(item.hdurl)
                .centerCrop().fit()
                .into(itemView.apod_img)
        }
    }
}