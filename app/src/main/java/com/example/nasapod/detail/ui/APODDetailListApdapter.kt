package com.example.nasapod.detail.ui


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nasapod.R
import com.example.nasapod.commons.data.local.APODObject
import com.squareup.picasso.Callback
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


    inner class APODItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
       

        init {
            itemView.apod_img.setOnViewDragListener { dx, dy ->
                Log.e("drag", dx.toString().plus(" ").plus(dy))
            }
            itemView.apod_detail_btn.setOnClickListener(this)
            itemView.apod_detail_btn.tag = "ImageView"
        }

        fun bind(item: APODObject, picasso: Picasso) {
            itemView.progressBar.visibility = View.VISIBLE
            picasso.load(item.hdurl)
                .centerCrop().fit()
                .into(itemView.apod_img, object : Callback {
                    override fun onSuccess() {
                        itemView.progressBar.visibility = View.GONE
                    }
                    override fun onError(e: Exception?) {
                        itemView.progressBar.visibility = View.GONE
                        picasso.load(item.tileImageUrl)
                            .centerCrop()
                            .fit()
                            .into(itemView.apod_img)
                    }

                })

            itemView.title.text = item.title
            itemView.date.text = item.date
            if(item.explanation.isEmpty())
                itemView.lbl_explanantion.visibility = View.GONE
            else {
                itemView.lbl_explanantion.visibility = View.VISIBLE
                itemView.lbl_explanantion.text = itemView.context.getString(R.string.explanation, item.explanation)

            }

        }

        override fun onClick(p0: View?) {
            if(p0?.id == R.id.apod_detail_btn) {
                if(p0.tag == "ImageView") {
                    //show details.
                    itemView.details_view.visibility = View.VISIBLE
                    (p0 as TextView).text = itemView.context.getString(R.string.btn_lbl_close)
                    itemView.apod_detail_btn.tag = "DetailsView"
                } else {
                    itemView.details_view.visibility = View.GONE
                    (p0 as TextView).text = itemView.context.getString(R.string.lbl_details_view)
                    itemView.apod_detail_btn.tag = "ImageView"
                }
            }
        }
            
    }
}
