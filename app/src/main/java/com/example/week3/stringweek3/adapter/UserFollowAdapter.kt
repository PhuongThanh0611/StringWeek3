package com.example.week3.stringweek3.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.week3.stringweek3.R
import com.example.week3.stringweek3.model.DataUserFollow
import com.example.week3.stringweek3.view.FollowPeople
import kotlinx.android.synthetic.main.item_follow_people.view.*

class UserFollowAdapter(private val listUserFollow: ArrayList<DataUserFollow> ,private var clickItemFollowPeople: FollowPeople) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_follow_people, parent, false)
        return ViewHolderUserFollow(view)

    }
    fun addListFollow(list: List<DataUserFollow>) {

        this.listUserFollow.apply {
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listUserFollow.size
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        if (position == listUserFollow.size - 2) clickItemFollowPeople.onLoadMore()
        (holder as ViewHolderUserFollow).bind(position, listUserFollow[position],clickItemFollowPeople)
    }

   inner class ViewHolderUserFollow (private var view: View) : RecyclerView.ViewHolder(view){
        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun bind(
            position: Int,
            userFollow: DataUserFollow,
            clickItemFollowPeople: FollowPeople
        ){
            itemView.txtUsername.text= userFollow.username
            // check hien thi hinh ngoi sao
            if(userFollow.isSuperUser == false){
                itemView.appCompatImageView4.visibility=View.GONE
            }
            // check follow people
            when(userFollow.checkfollow){
                true->{
                    itemView.linerBtn.isSelected=true
                    itemView.tvFollow.text="Unfollow"
                    itemView.tvFollow.setTextColor(ContextCompat.getColor(itemView.context, R.color.purple))
                }
                false->{
                    itemView.linerBtn.isSelected=false
                    itemView.tvFollow.text="Follow"
                    itemView.tvFollow.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                }
            }
// check hien thi hinh anh
            when(userFollow.photos?.size ?: 0){
                 0 ->{
                    itemView.imgUser1.setImageResource(R.drawable.emty_itinerary)
                    itemView.imgUser2.visibility =View.GONE
                    itemView.imgUser3.visibility =View.GONE
                }
                1->{
                    if(userFollow.photos?.get(0)?.url?.thumb?.isNotEmpty()!!){
                        Glide.with(view.context).load(userFollow.photos[0].url?.medium)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(20)))
                            .into(itemView.imgUser1)
                    }
                    itemView.imgUser2.visibility =View.GONE
                    itemView.imgUser3.visibility =View.GONE
                }
                2->{
                    if(userFollow.photos?.get(0)?.url?.thumb?.isNotEmpty()!!){
                        Glide.with(view.context).load(userFollow.photos[0].url?.medium)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(5)))
                            .into(itemView.imgUser1)
                    }
                    if(userFollow.photos[1].url?.thumb?.isNotEmpty()!!){
                        Glide.with(view.context).load(userFollow.photos[0].url?.original)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(5)))
                            .into(itemView.imgUser2)
                    }
                    itemView.imgUser3.visibility =View.GONE
                }
                else->{
                    if (userFollow.photos?.get(0)?.url?.thumb?.isNotEmpty()!!) {
                        Glide.with(view.context).load(userFollow.photos[0].url?.medium)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(5)))
                            .into(itemView.imgUser1)
                    }
                    if (userFollow.photos[1].url?.thumb?.isNotEmpty()!!) {
                        Glide.with(view.context).load(userFollow.photos[0].url?.original)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(5)))
                            .into(itemView.imgUser2)
                    }
                    if (userFollow.photos[2].url?.thumb?.isNotEmpty()!!) {
                        Glide.with(view.context).load(userFollow.photos[0].url?.thumb)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(5)))
                            .into(itemView.imgUser2)
                    }
                }
            }
           // check click vào item
            itemView.linerBtn.setOnClickListener {
                clickItemFollowPeople.clickItem(userFollow)
                userFollow.checkfollow = !userFollow.checkfollow!!
                listUserFollow[position].checkfollow =userFollow.checkfollow
//                notifyItemChanged(position)
                when(userFollow.checkfollow){
                    true->{
                        itemView.linerBtn.isSelected=true
                        itemView.tvFollow.text="Unfollow"
                        itemView.tvFollow.setTextColor(ContextCompat.getColor(itemView.context, R.color.purple))
                    }
                    false->{
                        itemView.linerBtn.isSelected=false
                        itemView.tvFollow.text="Follow"
                        itemView.tvFollow.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                    }
                }
            }
        }
    }
}