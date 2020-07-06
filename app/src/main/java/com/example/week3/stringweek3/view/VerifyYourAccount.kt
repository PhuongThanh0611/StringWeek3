package com.example.week3.stringweek3.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.week3.stringweek3.R
import kotlinx.android.synthetic.main.activity_verify_your_account.*

class VerifyYourAccount : AppCompatActivity() {
        var email: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_your_account)
        setSupportActionBar(toolbarVerity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        email= intent.getStringExtra("email")
        setEvent()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(android.R.id.home ==item.itemId){
            super.onBackPressed()
        }
        return true
    }

    private fun setEvent() {
        tvEmail.text= email

        // open mail app
        btnOpen.setOnClickListener {
            val mIntent = Intent(Intent.ACTION_SEND)
            mIntent.data = Uri.parse("mailto:")
            mIntent.type = "text/plain"
            try {
                //start email intent
                startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
            }
            catch (e: Exception){
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
