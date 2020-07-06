package com.example.week3.stringweek3.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.week3.stringweek3.Injection
import com.example.week3.stringweek3.R
import com.example.week3.stringweek3.cons.Email_Address
import com.example.week3.stringweek3.utils.Status
import com.example.week3.stringweek3.utils.getStringPref
import com.example.week3.stringweek3.utils.setStringPref
import com.example.week3.stringweek3.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    private lateinit var email:String
    private lateinit var  password: String
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var refreshViewModel: RegisterViewModel
    private lateinit var tokenRefesh:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbarLogIn)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        // lấy giá trị
        val token = getStringPref("token")
        tokenRefesh ="Bearer " + getStringPref("tokenrefresh")
        if (!token.isNullOrEmpty()) {
            val intent = Intent(this, SelectInterests::class.java)
            startActivity(intent)
            finish()
        }
        registerViewModel=viewModel()
        refreshViewModel=viewModelRefreshToken()
        setEvent()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(android.R.id.home ==item.itemId){
            super.onBackPressed()
        }
        return true
    }

    fun setEvent(){
        setupObserver()
        btnLogin.setOnClickListener {
            email = etEmail.text.toString()
            password = etPass.text.toString()

            if (email.isEmpty()) {
                etEmail.error = "Chưa nhập Email !!"
                etEmail.requestFocus()
                return@setOnClickListener
            }else if(!Email_Address.matcher(email).matches()){
                etEmail.error = "Email chưa hợp lệ!!"
                etEmail.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                etPass.error = "Chưa nhập Password !!"
                etPass.requestFocus()
                return@setOnClickListener
            }
            registerViewModel.postLogin(email, password)

        }
        tvForgot.setOnClickListener {
            val intent=Intent(this,ForgotPasswordActivity::class .java)
            startActivity(intent)
        }
    }
    private fun setupObserver(){
        registerViewModel.listregister.observe(this, Observer {
            if(it != null ){
                // lưu tài khoản lại loca
                setStringPref("email",email )
                setStringPref("pass",password )
                it.data?.accessToken?.let { it1 -> setStringPref("token", it1) }
                it.data?.refreshToken?.let { it2 -> setStringPref("tokenrefresh", it2) }
                val intent = Intent(this, SelectInterests::class.java)
                startActivity(intent)
            }
        })
        registerViewModel.networkState.observe(this, Observer {
            if(it.status == Status.ERROR ){
                tvError.visibility= View.VISIBLE
                tvError.text=it.message
                txtError.visibility=View.VISIBLE
                etEmail.text=null
                etPass.text=null
                etEmail.requestFocus()
            }
        })
    }
    private fun viewModel(): RegisterViewModel{
        val viewModelFactory = Injection.provideRegisterViewModelFactory()
        return ViewModelProvider(this,viewModelFactory)[RegisterViewModel::class.java]
    }
    private fun viewModelRefreshToken(): RegisterViewModel{
        val viewModelFactory = Injection.provideRefreshTokenViewModelFactory()
        return ViewModelProvider(this,viewModelFactory)[RegisterViewModel::class.java]
    }
}
