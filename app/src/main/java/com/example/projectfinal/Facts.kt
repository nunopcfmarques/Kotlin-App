package com.example.projectfinal

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

class Facts : AppCompatActivity() {
    //Activity na qual são displayed os vários fragments. Esta activity foi estruturada conforme um tutorial que
    //encontrei online para permitir um cardswipe suave entre os vários fragmentos.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facts)

        supportActionBar?.hide()

        val sharedPreferences = getSharedPreferences("colorPrefs", MODE_PRIVATE)
        val savedR=sharedPreferences.getInt("r",255)
        val savedG=sharedPreferences.getInt("g",255)
        val savedB=sharedPreferences.getInt("b",255)

        val backGround: ImageView = findViewById(R.id.backgroundfacts)
        backGround.setBackgroundColor(Color.rgb(savedR,savedG,savedB))

        val viewPager: ViewPager2 = findViewById(R.id.pager)


        //Os fragments são postos em array
        val fragments: ArrayList<Fragment> = arrayListOf(
            fragmentbirthday(),
            Agefragment(),
            genderfragment(),
            namefragment(),
            coursefragment()

        )

        val adapter = ViewPageAdapter(fragments,this)
        viewPager.adapter = adapter
    }
}