package com.example.week3.stringweek3.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.week3.stringweek3.Injection
import com.example.week3.stringweek3.R
import com.example.week3.stringweek3.adapter.ClickItemFollowPeople
import com.example.week3.stringweek3.adapter.UserFollowAdapter
import com.example.week3.stringweek3.model.DataUserFollow
import com.example.week3.stringweek3.utils.Status
import com.example.week3.stringweek3.utils.getStringPref
import com.example.week3.stringweek3.utils.onScrollToEnd
import com.example.week3.stringweek3.viewmodel.FollowUsersViewModel
import com.example.week3.stringweek3.viewmodel.UserFollowViewModel
import kotlinx.android.synthetic.main.activity_follow_people.*

class FollowPeople : AppCompatActivity(), ClickItemFollowPeople {
    private var token: String? = null
    private var currentPage = 1
    private var currentPage1 = "20"
    private lateinit var userfollowAdapter: UserFollowAdapter
    private lateinit var userfollowViewModel: UserFollowViewModel
    private lateinit var followUsersViewModel: FollowUsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follow_people)
        setSupportActionBar(toolbarFollowPeople)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        // lấy giá trị
        token = "Bearer " + getStringPref("token")
        userfollowViewModel = viewModelUserFollow()
        followUsersViewModel = viewModelFollowUsers()
        initRecyclerView()
        setEvent()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (android.R.id.home == item.itemId) {
            super.onBackPressed()
        }
        return true
    }

    //khoi tao recyclerview
    private fun initRecyclerView() {
        userfollowAdapter = UserFollowAdapter(arrayListOf(), this)
        recyclerFollowPeople.adapter = this.userfollowAdapter
        recyclerFollowPeople.setHasFixedSize(true)
    }

    private fun setEvent() {
        token?.let { userfollowViewModel.getUserFollow(currentPage.toString(), currentPage1, it) }
        setupObserverUserFollow()
        // load more cho recyclerview
        recyclerFollowPeople.onScrollToEnd() {
            onLoadMore()
        }
        // xet su kien chp btn
        btnDone.setOnClickListener {
            val intent = Intent(this, NotificationPush::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
            finish()
        }
    }

    private fun setupObserverUserFollow() {
        // userFollowList
        userfollowViewModel.resultUserFollow.observe(this, Observer {
            if (it != null) {
                // show danh sách ra
                it.data.let { it1 -> userfollowAdapter.addListFollow(it1) }
                if (it.data.isEmpty()) {
                    Toast.makeText(this, "Data is empty", Toast.LENGTH_SHORT).show()
                }
            }
        })
        userfollowViewModel.networkUserFollow.observe(this, Observer {
            if (it.status == Status.ERROR) {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        })

        // followUsers
        followUsersViewModel.resultFollowUsers.observe(this, Observer {
            if (it != null) {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        })

        followUsersViewModel.networkFollowUsers.observe(this, Observer {
            if (it.status == Status.ERROR) {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun clickItem(item: DataUserFollow) {
        // thay đổi của api
        item.id?.let { token?.let { it1 -> followUsersViewModel.postFollowUsers(it, it1) } }
    }

    fun onLoadMore() {
        currentPage++
        token?.let { userfollowViewModel.getUserFollow(currentPage.toString(), currentPage1, it) }
    }

    private fun viewModelUserFollow(): UserFollowViewModel {
        val viewModelFactory = Injection.provideUserFollowViewModelFactory()
        return ViewModelProvider(this, viewModelFactory)[UserFollowViewModel::class.java]
    }

    private fun viewModelFollowUsers(): FollowUsersViewModel {
        val viewModelFactory = Injection.provideFollowUserViewModelFactory()
        return ViewModelProvider(this, viewModelFactory)[FollowUsersViewModel::class.java]
    }


}
