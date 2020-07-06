package com.example.week3.stringweek3.fragment

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
import com.example.week3.stringweek3.adapter.NavHomeAdapter
import com.example.week3.stringweek3.utils.Status
import com.example.week3.stringweek3.utils.getStringPref
import com.example.week3.stringweek3.utils.onScrollToEnd
import com.example.week3.stringweek3.viewmodel.NavHomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class HomeFragment : Fragment(){
    private var currentPage1 = "20"
    private var currentPage = 1
    private lateinit var token: String
    private lateinit var navHomeViewModel: NavHomeViewModel
    private lateinit var navHomeAdapter: NavHomeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navHomeViewModel = viewModel()
        token = "Bearer " + requireActivity().getStringPref("token")
        initRecyclerView()
        setEvent()
    }

    private fun setEvent() {
        setuoObserver()
        navHomeViewModel.getHomeFeed(currentPage.toString(), currentPage1, token)
        // load more cho recyclerview
        recyclerViewNavHome.onScrollToEnd() {
            onLoadMore()
        }
    }

    private fun setuoObserver() {
        navHomeViewModel.resultHomeFeed.observe(this, Observer {
            if (it != null) {
                // show danh sách ra
                it.data.let { it1 -> navHomeAdapter.addList(it1) }
                if (it.data!!.isEmpty()) {
                    Toast.makeText(context, "Data is empty", Toast.LENGTH_SHORT).show()
                }
            }else{
                recyclerViewNavHome.visibility=View.GONE
                constraintHomeFeed.visibility=View.VISIBLE

            }
        })
        navHomeViewModel.networkHomeFeed.observe(this, Observer {
            if (it.status == Status.ERROR) {
            Log.d("abc", it.message)
            }
        })
    }

    //khoi tao recyclerview
    private fun initRecyclerView() {
        navHomeAdapter = NavHomeAdapter(arrayListOf())
        recyclerViewNavHome.adapter = this.navHomeAdapter
        recyclerViewNavHome.setHasFixedSize(true)
    }

    private fun viewModel(): NavHomeViewModel {
        val viewModelFactory = Injection.provideHomeFeedViewModelFactory()
        return ViewModelProvider(this, viewModelFactory)[NavHomeViewModel::class.java]
    }

    fun onLoadMore() {
        currentPage++
        navHomeViewModel.getHomeFeed(currentPage.toString(), currentPage1, token)
    }

}
