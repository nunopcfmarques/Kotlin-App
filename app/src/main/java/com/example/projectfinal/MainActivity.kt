package com.example.projectfinal

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()


        //Butão que leva à actividade Theme
        val colorChange: Button = findViewById(R.id.colorchange)
        val intent = Intent(this,ColorChanger::class.java )
        colorChange.setOnClickListener{
            startActivity(intent)


        }

        //Para mudar a cor do background
        val sharedPreferences = getSharedPreferences("colorPrefs", MODE_PRIVATE)
        val savedR=sharedPreferences.getInt("r",255)
        val savedG=sharedPreferences.getInt("g",255)
        val savedB=sharedPreferences.getInt("b",255)

        val backGround: ImageView = findViewById(R.id.backgroundmenu)
        backGround.setBackgroundColor(Color.rgb(savedR,savedG,savedB))


        //Aqui inicializo os vários butões para as várias activities
        val facts: Button= findViewById(R.id.profilefacts)
        val goFacts = Intent(this,Facts::class.java)
        facts.setOnClickListener{
            startActivity(goFacts)
        }

        val settings: Button=findViewById(R.id.profilechange)
        val goSettings = Intent(this,Settings::class.java)
        settings.setOnClickListener{
            startActivity(goSettings)
        }

        val api: Button=findViewById(R.id.apimania)
        val goApi = Intent(this,API_Mania::class.java)
        api.setOnClickListener{
            startActivity(goApi)
        }

    }

    override fun onBackPressed() {

    }
}