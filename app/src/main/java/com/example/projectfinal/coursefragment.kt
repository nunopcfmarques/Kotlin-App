package com.example.projectfinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_coursefragment.view.*



class coursefragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment que dá display ao curso submitido no profile inicial/settings
        // Aqui é utilizado shared prefs. Foi preciso declarar a variável view e usar um plugin para
        // substituir o findViewById (em fragments não funciona)
        val view = inflater.inflate(R.layout.fragment_coursefragment, container, false)
        val preferences = (activity as Facts).getSharedPreferences("prefs",0)
        val course=preferences.getString("course","You are ageless...")
        view.coursePage.text = "You are enrolled in $course"
        return view
    }


}