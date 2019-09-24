package com.example.nasapod.list.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nasapod.R
import com.example.nasapod.list.vo.APODObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.apod_item_view.view.*
import javax.inject.Inject

class APODListAdapter @Inject constructor(private val picasso: Picasso, private val podListView: PODListView): RecyclerView.Adapter<APODListAdapter.ItemHolder>() {

    var apods: MutableList<APODObject> = mutableListOf()
    var clickListener: APODItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.apod_item_view, parent, false), clickListener)


    override fun getItemCount() = apods.size


    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(apods[position], picasso)
    }

    inner class ItemHolder(itemView: View, private val clickListener: APODItemClickListener?): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        fun bind(item: APODObject, picasso: Picasso) {
            itemView.apod_title.text = item.title
            picasso.load(item.tileImageUrl)
                .centerCrop().fit().into(itemView.apod_img)
        }
        override fun onClick(p0: View?) {
            clickListener?.onAPODItemClick(apods[adapterPosition], adapterPosition)
        }
    }

    interface APODItemClickListener {
        fun onAPODItemClick(obj: APODObject, position: Int)
    }
}