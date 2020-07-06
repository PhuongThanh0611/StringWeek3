package com.example.week3.stringweek3.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.week3.stringweek3.Injection
import com.example.week3.stringweek3.R
import com.example.week3.stringweek3.adapter.ClickItemIntersets
import com.example.week3.stringweek3.adapter.IntersetsAdapter
import com.example.week3.stringweek3.model.DataIntersets
import com.example.week3.stringweek3.utils.Status
import com.example.week3.stringweek3.utils.getStringPref
import com.example.week3.stringweek3.utils.onScrollToEnd
import com.example.week3.stringweek3.viewmodel.IntersetsViewModel
import kotlinx.android.synthetic.main.activity_select_interests.*

class SelectInterests : AppCompatActivity(), ClickItemIntersets {
    private lateinit var token: String
    private var demitem: Int = 0
    private var currentPage = 1
    private var currentPage1 ="20"
    private var listItem: ArrayList<Int> = arrayListOf()
    private lateinit var intersetsViewModel: IntersetsViewModel
    private lateinit var intersetsAdapter: IntersetsAdapter

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_interests)
        initRecyclerView()
        // lấy giá trị
        token = "Bearer " +getStringPref("token")
        intersetsViewModel = viewModel()
        setEvent()
    }
    //khoi tao recyclerview
    private fun initRecyclerView() {
        intersetsAdapter = IntersetsAdapter(arrayListOf(), this)
        recyclerview.adapter = this.intersetsAdapter
        recyclerview.setHasFixedSize(true)
    }

    private fun setEvent() {
        token.let { intersetsViewModel.getIntersets(currentPage.toString(), currentPage1, it) }
        setupObserver()
        // load more cho recyclerview
        recyclerview.onScrollToEnd() {
            onLoadMore()
        }
        // xét sự kiện click vào button
            btnMore.setOnClickListener {
                val intent = Intent(this, FollowPeople::class.java)
                startActivity(intent)
            }

    }

    private fun setupObserver() {
        intersetsViewModel.resultIntersets.observe(this, Observer {
            if (it != null) {
                // show danh sách ra
                it.data?.let { it1 -> intersetsAdapter.addList(it1) }
                if (it.data?.isEmpty()== true) {
                    Toast.makeText(this, "Data is empty", Toast.LENGTH_SHORT).show()
                }
            }
        })
        intersetsViewModel.networkIntersets.observe(this, Observer {
            if(it.status == Status.ERROR ){
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun viewModel(): IntersetsViewModel {
        val viewModelFactory = Injection.provideIntersetsViewModelFactory()
        return ViewModelProvider(this, viewModelFactory)[IntersetsViewModel::class.java]
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun clickItem(item: DataIntersets, dem: Int) {
        demitem += dem
        when(demitem){
            0->{
                btnMore.isEnabled = false
                btnMore.text = "3 More"
            }
            1->{
                btnMore.isEnabled = false
                btnMore.text = "2 More"
            }
            2->{
                btnMore.isEnabled = false
                btnMore.text = "1 More"
            }
            else->{
                btnMore.isEnabled = true
                btnMore.text = "Next"
            }
        }
        // them id vào ds list
        if (dem == -1) {
            item.id?.let { listItem.remove(it) }
        } else
            item.id?.let { listItem.add(it) }
    }

     fun onLoadMore() {
        currentPage++
        token.let { intersetsViewModel.getIntersets(currentPage.toString(), currentPage1, it) }
    }
}
