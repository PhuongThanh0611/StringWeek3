package com.example.week3.stringweek3.view


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.example.week3.stringweek3.R
import kotlinx.android.synthetic.main.activity_notification_location.*

class NotificationLocation : AppCompatActivity(),
    ActivityCompat.OnRequestPermissionsResultCallback {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_location)
        setEvent()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setEvent() {
        buttonLocation.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
            )
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 44)
            }
        txtMaybeLocation.setOnClickListener {
            val intent=Intent(this, Feed::class.java)
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode ==44){
            val intent=Intent(this, Feed::class.java)
            startActivity(intent)
        }
    }

}



