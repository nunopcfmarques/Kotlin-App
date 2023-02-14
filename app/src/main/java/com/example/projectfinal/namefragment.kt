package com.example.projectfinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_namefragment.view.*



class namefragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment que dá display ao nome submitido no profile inicial/settings
        // Aqui é utilizado shared prefs. Foi preciso declarar a variável view e usar um plugin para
        // substituir o findViewById (em fragments não funciona)
        val view = inflater.inflate(R.layout.fragment_namefragment, container, false)
        val preferences = (activity as Facts).getSharedPreferences("prefs",0)
        val age=preferences.getString("name","You are ageless...")
        view.namePage.text ="Your name is $age"
        return view
    }


}