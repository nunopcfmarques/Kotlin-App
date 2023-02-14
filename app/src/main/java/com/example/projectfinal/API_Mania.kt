package com.example.projectfinal

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class API_Mania : AppCompatActivity() {
    //Basicamente o que esta activity faz é vai buscar o valor associado a "text" no json associado ao url
    //Quando se clicka no butão GO! é um refresh ao url pelo que os factos tão sempre a ser mudados
    // (Nota engraçada: se se clickar várias vezes no GO! o site vai a baixo)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_mania)
        supportActionBar?.hide()
        val factButton: Button =findViewById(R.id.factbutton)
        val randomFact = findViewById<TextView>(R.id.randomfact)


        //As color prefs são usadas para mudar o background desta activity
        //Dos vários testes que fiz é necessário sempre associar uma ImageView
        // para mudar a cor do background
        val sharedPreferences = getSharedPreferences("colorPrefs", MODE_PRIVATE)
        val savedR=sharedPreferences.getInt("r",255)
        val savedG=sharedPreferences.getInt("g",255)
        val savedB=sharedPreferences.getInt("b",255)

        val backGround: ImageView = findViewById(R.id.apimenu)
        backGround.setBackgroundColor(Color.rgb(savedR,savedG,savedB))

        //Quando se clicka no butão vai buscar o valor da string ''text'' no json do url e adiciona
        //ao queue de requests. Foi usado volley.
        factButton.setOnClickListener {


            val queue = Volley.newRequestQueue(this)

            val url = "https://uselessfacts.jsph.pl/random.json?language=en"

            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->
                randomFact.text = response.getString("text")


            }, {
                randomFact.text = "Error: Website was overloaded / You are offline. Truly Unlucky."
            })
            queue.add(jsonObjectRequest)

        }


    }

    }