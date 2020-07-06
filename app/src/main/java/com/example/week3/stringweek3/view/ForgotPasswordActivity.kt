package com.example.week3.stringweek3.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.week3.stringweek3.Injection
import com.example.week3.stringweek3.R
import com.example.week3.stringweek3.utils.Status
import com.example.week3.stringweek3.viewmodel.ForgotPasswordViewModel
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var email: String
    private lateinit var forgotpasswordViewModel: ForgotPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        setSupportActionBar(toolbarFogotPassword)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        forgotpasswordViewModel = viewModel()
        setEvent()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(android.R.id.home ==item.itemId){
            super.onBackPressed()
        }
        return true
    }
    private fun setEvent() {
        btnSendEmail.setOnClickListener {
            email = editTextEmail.text.toString()
            forgotpasswordViewModel.postRogetPass(email)
            setupObserver()
        }
    }

    private fun setupObserver() {
        forgotpasswordViewModel.listForgot.observe(this, Observer {
            if (it != null) {
                    if (it.status) {
                        val intent = Intent(this, VerifyYourAccount::class.java)
                        intent.putExtra("email", email)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        return@Observer
                    }
                } else {
                Toast.makeText(this, "Không thể gửi email", Toast.LENGTH_LONG).show()
            }
        })
        forgotpasswordViewModel.networkForgot.observe(this, Observer {
            if (it.status == Status.ERROR) {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun viewModel(): ForgotPasswordViewModel {
        val viewModelFactory = Injection.provideForgotPasswordViewModelFectory()
        return ViewModelProvider(this, viewModelFactory)[ForgotPasswordViewModel::class.java]
    }
}
