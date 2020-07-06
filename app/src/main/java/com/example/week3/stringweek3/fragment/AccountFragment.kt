package com.example.week3.stringweek3.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.week3.stringweek3.Injection
import com.example.week3.stringweek3.R
import com.example.week3.stringweek3.utils.Status
import com.example.week3.stringweek3.utils.getStringPref
import com.example.week3.stringweek3.utils.removeValueSharePrefs
import com.example.week3.stringweek3.view.LoginMain
import com.example.week3.stringweek3.viewmodel.LogOutViewModel
import kotlinx.android.synthetic.main.fragment_account.*
/**
 * A simple [Fragment] subclass.
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class AccountFragment : Fragment() {
    private lateinit var token: String
    private lateinit var viewModel: LogOutViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_account, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=viewModel()
        token = "Bearer " + requireActivity().getStringPref("token")
        setEvent()
    }

    private fun setEvent() {
        button2.setOnClickListener {
            setuoObserver()
            viewModel.getIntersets(token)
            requireActivity().removeValueSharePrefs("token")
            val intent = Intent(context, LoginMain::class.java)
            startActivity(intent)
        }
    }

    private fun setuoObserver() {
        viewModel.resultLogOut.observe(this, Observer {
            Toast.makeText(context,"LogOut successful",Toast.LENGTH_SHORT).show()
        })
        viewModel.networkIntersets.observe(this, Observer {
            if (it.status == Status.ERROR) {
                Log.d("abc", it.message)
            }
        })
    }

    private fun viewModel(): LogOutViewModel {
        val viewModelFactory = Injection.provideLogOutViewModelFactory()
        return ViewModelProvider(this, viewModelFactory)[LogOutViewModel::class.java]
    }

}
