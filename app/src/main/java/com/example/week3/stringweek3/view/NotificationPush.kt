package com.example.week3.stringweek3.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.week3.stringweek3.Injection
import com.example.week3.stringweek3.R
import com.example.week3.stringweek3.utils.Status
import com.example.week3.stringweek3.viewmodel.UserNotificationViewModel
import kotlinx.android.synthetic.main.activity_notification_push.*

class NotificationPush : AppCompatActivity() {
    private lateinit var userNotificationViewModel :UserNotificationViewModel
    private var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_push)
        token = intent.getStringExtra("token")
        userNotificationViewModel= viewModel()
        setEvent()
    }

    private fun setEvent() {
        btnOkey.setOnClickListener {
            token?.let { it1 ->
                userNotificationViewModel.putUserNotification(
                    likes = true,
                    comments = true,
                    new_followes = true,
                    post_saves = true,
                    string = true,
                    token = it1
                )
            }
            setupObserver()
        }
        txtMaybePlush.setOnClickListener {
            val intent = Intent(this,NotificationLocation::class.java )
            startActivity(intent)
        }
    }

    private fun setupObserver() {
        userNotificationViewModel.resultUserFollow.observe(this, Observer {
            if(it!= null){
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                val intent = Intent(this,NotificationLocation::class.java )
                startActivity(intent)
            }
        })
        userNotificationViewModel.networkUserFollow.observe(this, Observer {
            if(it.status == Status.ERROR ){
                Toast.makeText(this,"Lỗi :"+it.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun viewModel(): UserNotificationViewModel {
        val viewModelFactory = Injection.provideUserNotificationViewModelFactory()
        return ViewModelProvider(this, viewModelFactory)[UserNotificationViewModel::class.java]
    }
}
