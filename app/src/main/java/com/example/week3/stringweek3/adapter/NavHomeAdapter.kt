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
import com.example.week3.stringweek3.model.FeedItem
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.item_home_itinerary.view.*
import kotlinx.android.synthetic.main.item_home_poi.view.*
import kotlinx.android.synthetic.main.item_home_post.view.*

class NavHomeAdapter(
    private val listHomeFeed: ArrayList<FeedItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_home_post, parent, false)
                ViewHolderHomeFeedPost(view)
            }
            2 -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_home_poi, parent, false)
                ViewHolderHomeFeedPoi(view)
            }
            else -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_home_itinerary, parent, false)
                ViewHolderHomeFeedItinery(view)
            }
        }

    }

    override fun getItemCount(): Int {
        return listHomeFeed.size

    }

    fun addList(list: List<FeedItem>?) {

        if (list == null) return
        this.listHomeFeed.apply {
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (listHomeFeed[position].type) {
            "post" -> {
                0
            }
            "itinerary" -> {
                1
            }
            else -> {
                2
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            getItemViewType(position) == 0 -> {
                (holder as ViewHolderHomeFeedPost).bind(listHomeFeed[position])
            }
            getItemViewType(position) == 2 -> {
                (holder as ViewHolderHomeFeedPoi).bind(listHomeFeed[position])
            }
            else -> {
                (holder as ViewHolderHomeFeedItinery).bind(listHomeFeed[position])
            }
        }

    }

    class ViewHolderHomeFeedPost(private var view: View) : RecyclerView.ViewHolder(view) {

        fun bind(homeFeed: FeedItem) {
            Glide.with(view.context).load(homeFeed.user?.profilePhoto)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(100)))
                .error(R.drawable.nguoi)
                .into(itemView.imgAvatar)
            Glide.with(view.context).load(homeFeed.photos?.get(0)?.url?.original)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(20)))
                .into(itemView.imgFeed)
            // remove để load lại chip
            itemView.chipGroud.removeAllViews()
            // check hien thi hinh ngoi sao
            if(homeFeed.user?.isSuperUser == false){
                itemView.appCompatImageView4.visibility=View.GONE
            }
            for (i in 0 until (homeFeed.tags?.size ?: 0)) {
                val itemChip: Chip = LayoutInflater.from(view.context)
                    .inflate(R.layout.chip_feed_item, view as ViewGroup, false) as Chip
                itemChip.text = homeFeed.tags?.get(i)?.title ?: "abc"
                itemView.chipGroud.addView(itemChip)
            }
            itemView.tvplace_post.text=homeFeed.place?.title.toString()
            itemView.tvStrung.text=homeFeed.strungCounter.toString()
            itemView.txtName.text = homeFeed.user?.username
            itemView.txttitle.text = homeFeed.description
            itemView.txtLike.text = homeFeed.likeCounter.toString()
            itemView.txtConment.text = homeFeed.commentCounter.toString()
        }
    }

    class ViewHolderHomeFeedPoi(private var view: View) : RecyclerView.ViewHolder(view) {
        fun bind(homeFeed: FeedItem) {
            // check hien thi hinh ngoi sao
            if(homeFeed.user?.isSuperUser == false){
                itemView.appCompatImageView5.visibility=View.GONE
            }
            Glide.with(view.context).load(homeFeed.photos?.get(0)?.url?.original)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(20)))
                .into(itemView.imgPlace)
            Glide.with(view.context).load(homeFeed.user?.profilePhoto)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(itemView.imguser)
            itemView.txtString.text=homeFeed.strungCounter.toString()
            itemView.tv_username.text=homeFeed.user?.username
            itemView.txtTitle.text = homeFeed.title.toString()
            itemView.txtPlace.text = homeFeed.address.toString()
            itemView.txtlikePlace.text = homeFeed.likeCounter.toString()
            itemView.txtconmentPlace.text = homeFeed.commentCounter.toString()
        }
    }

    class ViewHolderHomeFeedItinery( view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var homeItineraryAdapter: NavHomeItineraryAdapter

        fun bind(homeFeed: FeedItem) {
            initRecyclerView()
            if(homeFeed.itineraries?.size == null){
                itemView.recyclerHomeItinery.visibility=View.GONE
            }
            // check hien thi hinh ngoi sao
            if(homeFeed.user?.isSuperUser == false){
                itemView.imageView3.visibility=View.GONE
            }
            homeFeed.itineraries?.let { homeItineraryAdapter.addList(it) }
            itemView.tvString.text=homeFeed.strungCounter.toString()
            itemView.tvTitle.text = homeFeed.title.toString()
            itemView.tvUsername.text = homeFeed.user?.username
            itemView.txtDescription.text = homeFeed.description
            itemView.txtlikeItinery.text = homeFeed.likeCounter.toString()
            itemView.txtconmentItinery.text = homeFeed.commentCounter.toString()
        }
        //khoi tao recyclerview
        private fun initRecyclerView() {
            homeItineraryAdapter = NavHomeItineraryAdapter(arrayListOf())
            itemView.recyclerHomeItinery.adapter= this.homeItineraryAdapter
            itemView.recyclerHomeItinery.setHasFixedSize(true)
        }
    }

}

