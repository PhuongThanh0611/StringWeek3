package com.example.week3.stringweek3.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.week3.stringweek3.R
import com.example.week3.stringweek3.fragment.FragmentIntro1
import com.example.week3.stringweek3.fragment.FragmentIntro2
import com.example.week3.stringweek3.fragment.FragmentIntro3
import com.example.week3.stringweek3.utils.getStringPref
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , ViewPager.OnPageChangeListener {

    private var  currentPos =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // lấy giá trị
        val token = getStringPref("token")
        if (!token.isNullOrEmpty()) {
            val intent = Intent(this, Feed::class.java)
            startActivity(intent)
            finish()
        }
        addIntro()
        setEvents()
       /* val intent = Intent(this,LoginMain::class.java)
        startActivity(intent)
        finish()*/
    }

    private fun setEvents() {
        btnSkip.setOnClickListener {
            val intent = Intent(this,LoginMain::class.java)
            startActivity(intent)
        }
        btnNext.setOnClickListener {
            if(viewPager.currentItem!=2){
                viewPager.currentItem= currentPos++
            }else{
                val intent = Intent(this,LoginMain::class.java)
                startActivity(intent)
            }
        }
    }

    private fun addIntro(){
        val fragmentAdapter = IntroAdapter(supportFragmentManager)
        viewPager.adapter= fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
    override fun onPageSelected(position: Int) {
        currentPos = position
    }
    class IntroAdapter(fm : FragmentManager): FragmentPagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return  when(position){
                0->{
                    FragmentIntro1()
                }
                1->{
                    FragmentIntro2()
                }
                else->{
                    return FragmentIntro3()
                }
            }
        }

        override fun getCount(): Int {
            return 3
        }

    }
}
