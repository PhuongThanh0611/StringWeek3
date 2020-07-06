package com.example.week3.stringweek3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.week3.stringweek3.R
import com.example.week3.stringweek3.fragment.HomeFragment
import com.example.week3.stringweek3.model.Itinerary
import kotlinx.android.synthetic.main.recycleview_home_itinerary.view.*

class NavHomeItineraryAdapter(
    private val listItinerary: ArrayList<Itinerary>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            val view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_home_itinerary, parent, false)

           return  ViewHolderHomeFeedItinerary(view)

    }

    override fun getItemCount(): Int {
        return listItinerary.size
    }
    fun addList(listItinarery: List<Itinerary>) {
        this.listItinerary.apply {
            addAll(listItinarery)
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as ViewHolderHomeFeedItinerary).bind(listItinerary[position])
    }

    class ViewHolderHomeFeedItinerary  (private var view: View) : RecyclerView.ViewHolder(view){
        fun bind(
            item: Itinerary
        ) {

            Glide.with(view.context).load(item.photos?.url?.medium)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(20)))
                .error(R.drawable.emty_itinerary)
                .into(itemView.imgItinerary)
            itemView.tvPlace.text=item.title.toString()
        }

    }

}