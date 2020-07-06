package com.example.week3.stringweek3.adapter

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.week3.stringweek3.R
import com.example.week3.stringweek3.model.DataIntersets
import com.example.week3.stringweek3.view.SelectInterests
import kotlinx.android.synthetic.main.item_intersets.view.*

class IntersetsAdapter(
    private val listIntersets: ArrayList<DataIntersets>,
    private var clickItem: SelectInterests
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_intersets, parent, false)
        return ViewHolderIntersets(view)
    }
    private var listdem= HashMap<Int, Int>()

    inner class ViewHolderIntersets(private val view: View) : RecyclerView.ViewHolder(view) {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun bind(
            position: Int,
            intersets: DataIntersets,
            clickItem: SelectInterests
        ) {

            if (intersets.photo?.url?.original?.isNotEmpty()!!) {
                Glide.with(view.context).load(intersets.photo.url.medium)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(20)))
                    .error(R.drawable.hoa)
                    .into(itemView.iv_interest)
            }
            itemView.tv_title_interest.text = intersets.title
            itemView.im_checkbox.isChecked = intersets.statusItem == true

            itemView.setOnClickListener {
                var dem: Int = 1
                Log.d("data","\n"+intersets.title+" \n"+intersets.id+"\ndem "+dem)
                if (listdem.containsKey(position)){
                    dem = listdem[position]!!
                }
                dem++
                if (dem % 2 == 0) {
                    itemView.im_checkbox.isChecked = true
                    clickItem.clickItem(intersets, 1)
                    intersets.statusItem = true
                } else {
                    itemView.im_checkbox.isChecked = false
                    clickItem.clickItem(intersets, -1)
                    intersets.statusItem = false
                }
                // luu bien dem cho tung item
                listdem[position]=dem
            }
        }
    }

    fun addList(list: List<DataIntersets>) {
        this.listIntersets.apply {
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listIntersets.size
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderIntersets).bind(position,listIntersets[position], clickItem)

    }
}