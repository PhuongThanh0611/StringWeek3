package com.example.week3.stringweek3.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.week3.stringweek3.Injection
import com.example.week3.stringweek3.R
import com.example.week3.stringweek3.cons.Email_Address
import com.example.week3.stringweek3.cons.Username
import com.example.week3.stringweek3.utils.Status
import com.example.week3.stringweek3.utils.setStringPref
import com.example.week3.stringweek3.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_create_account.*
import java.util.*

class CreateAccount : AppCompatActivity() {
    private  var email: String=""
    private var name: String? = ""
    private  var date: String=""
    private  var username: String=""
    private  var pass: String=""
    private  var confirmPass: String=""
    private lateinit var registerViewModel: RegisterViewModel
    private var mDay: String? = null
    private var mThang: String? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        setSupportActionBar(toolbarRegister)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        registerViewModel = viewModel()
        setEvent()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(android.R.id.home ==item.itemId){
            super.onBackPressed()
        }
        return true
    }
    // xử lý khi click vào create account
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setEvent() {
        setupObserver()
        // date_time
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        et_date.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in TextView
                    if (dayOfMonth < 10) {
                        mDay = "0$dayOfMonth"
                    }
                    if (monthOfYear < 10) {
                        mThang = "0$monthOfYear"
                    }
                    et_date.setText("$mDay/$mThang/$year")
                },
                year,
                month,
                day
            )
            dpd.show()
        }
        txtLogIn.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
        txtTerms.setOnClickListener {
            val intent = Intent(this, TermsActivity::class.java)
            startActivity(intent)
            finish()
        }
        // email thay đổi
        et_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                setEventButton()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                email = et_email.text.toString()
            }
        })
        //pass thay đổi

        et_pass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                setEventButton()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                pass = et_pass.text.toString()
            }
        })
        name = et_name.text.toString()
        // date thay đổi

        et_date.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                setEventButton()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                date = et_date.text.toString().trim()
            }
        })
        // username thay đổi

        et_username.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                setEventButton()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                username = et_username.text.toString()
            }
        })
        // confirm thay đổi
        et_confirm_pass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                setEventButton()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                confirmPass = et_confirm_pass.text.toString()
            }
        })
        // checkbox
        cb_agress.setOnClickListener {
            setEventButton()
        }

        btnsingup.setOnClickListener {
            if (!Email_Address.matcher(email).matches()) {
                et_email.error = "Email should only contain letters, numbers and underscores"
                et_email.requestFocus()
                return@setOnClickListener
            }
            if (!Username.matcher(username).matches()) {
                et_username.error = "Username should only contain letters, \nnumbers and underscores"
                et_username.requestFocus()
                return@setOnClickListener
            }
            if (confirmPass != pass) {
                et_confirm_pass.error = "Password và ConfirmPassword phải trùng nhau !!"
                et_confirm_pass.requestFocus()
                return@setOnClickListener
            }
            registerViewModel.getRegister(username, name!!, date, email, pass, confirmPass)
        }

    }

    // sự kiện button sing in
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setEventButton() {
        if (email.isNotEmpty() && date.isNotEmpty() && username.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty() && cb_agress.isChecked) {
            btnsingup.background = getDrawable(R.drawable.buttonsingup2)
            btnsingup.isClickable= true
            btnsingup.isEnabled= true
        }
        else{
            btnsingup.isClickable= false
            btnsingup.isEnabled= false
        }
    }

    private fun setupObserver() {
        registerViewModel.listregister.observe(this, Observer {
            if (it != null) {
                it.data?.accessToken?.let { it1 -> setStringPref("token", it1) }
                it.data?.refreshToken?.let { it2 -> setStringPref("tokenrefresh", it2) }
                val intent = Intent(this, VerifyYourAccount::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Đăng kí không thành công", Toast.LENGTH_LONG).show()
            }
        })
        registerViewModel.networkState.observe(this, Observer {
            if (it.status == Status.ERROR) {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun viewModel(): RegisterViewModel {
        val viewModelFactory = Injection.provideRegisterViewModelFactory()
        return ViewModelProvider(this, viewModelFactory)[RegisterViewModel::class.java]
    }

}
