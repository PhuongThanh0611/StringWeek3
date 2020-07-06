package com.example.week3.stringweek3.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.week3.stringweek3.R
import com.example.week3.stringweek3.fragment.*
import com.example.week3.stringweek3.utils.getStringPref
import kotlinx.android.synthetic.main.activity_feed.*

class Feed : AppCompatActivity() {
    private var token: String? = null
    private  var fragmentHome : Fragment= HomeFragment()
    private  var fragmentSearch : Fragment= SearchFragment()
    private  var fragmentAdd : Fragment= AddFragment()
    private  var fragmentNotice : Fragment= NoticeFragment()
    private  var fragmentAccount : Fragment= AccountFragment()
    private  var fm : FragmentManager=supportFragmentManager
    private  var fragment:Fragment=fragmentHome
    private lateinit var fragmentTransaction :FragmentTransaction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        // lấy giá trị
         token ="Bearer " +getStringPref("token")
        // khơi tạo fragment
        fm.beginTransaction().add(R.id.fragment,fragmentHome,"1").commit()
        fm.beginTransaction().add(R.id.fragment,fragmentSearch,"2").hide(fragmentSearch).commit()
        fm.beginTransaction().add(R.id.fragment,fragmentAdd,"3").hide(fragmentAdd).commit()
        fm.beginTransaction().add(R.id.fragment,fragmentNotice,"4").hide(fragmentNotice).commit()
        fm.beginTransaction().add(R.id.fragment,fragmentAccount,"5").hide(fragmentAccount).commit()

        navigation.setOnNavigationItemSelectedListener {
                item-> selectItemNavigation(item)
            true
        }
        navigation.setOnNavigationItemReselectedListener {

        }
    }

    private fun selectItemNavigation(item: MenuItem) {
        val b = when(item.itemId){
            R.id.home-> fragmentHome
            R.id.search->fragmentSearch
            R.id.add->fragmentAdd
            R.id.bell->fragmentNotice
            R.id.account->fragmentAccount
            else -> fragmentHome
        }
        fragmentTransaction=supportFragmentManager.beginTransaction()
        fragmentTransaction.hide(fragment).show(b).commit()
        fragment=b
    }

}
