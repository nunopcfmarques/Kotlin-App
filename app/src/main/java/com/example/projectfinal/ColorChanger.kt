package com.example.projectfinal

import android.content.Intent
import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.slider.Slider

class ColorChanger : AppCompatActivity() {
    //Esta função permite mudar a cor dos backgrounds na APP usando shared prefs dos valores de r,g e b
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_changer)

        supportActionBar?.hide()


        //Inicialização dos valores. Decidi por no (0,0,0) porque o preto é sempre noticeable
        var r=0
        var g=0
        var b=0

        //Esta imagem é o quadrado, os sliders vão mudar a cor de background do quadrado,
        //mudando a sua cor.
        //Também muda o número associado ao textview dos rgb conforme o valor do slider.
        val image: ImageView = findViewById(R.id.square)

        val sliderR: Slider = findViewById(R.id.R)
        val sliderG: Slider = findViewById(R.id.G)
        val sliderB: Slider = findViewById(R.id.B)

        sliderR.addOnChangeListener{ _, value, _ ->
            r=value.toInt()
            image.setBackgroundColor(Color.rgb(r,g,b))
            val redId=findViewById<TextView>(R.id.redid)
            redId.text="R: $r"
        }


        sliderG.addOnChangeListener{ _, value, _ ->
            g=value.toInt()
            image.setBackgroundColor(Color.rgb(r,g,b))
            val greenId=findViewById<TextView>(R.id.greenid)
            greenId.text="G: $g"
        }


        sliderB.addOnChangeListener{ _, value, _ ->
            b=value.toInt()
            image.setBackgroundColor(Color.rgb(r,g,b))
            val blueId=findViewById<TextView>(R.id.blueid)
            blueId.text="B: $b"
        }


        val saveButton:Button = findViewById(R.id.savecolor)


        //Aqui são iniciadas as colorPrefs para mudar o background das restantes activities
        val preferences = getSharedPreferences("colorPrefs", MODE_PRIVATE)
        val editor = preferences.edit()

        saveButton.setOnClickListener{
            editor.apply {
                putInt("r", r)
                putInt("g", g)
                putInt("b", b)
            }.apply()
            val intent = Intent(this@ColorChanger, MainActivity::class.java)
            startActivity(intent)
            finish()
            }



        }













    }