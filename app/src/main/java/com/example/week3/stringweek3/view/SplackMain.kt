package com.example.week3.stringweek3.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplackMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
