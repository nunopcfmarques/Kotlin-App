package com.example.projectfinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast

class Splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        val sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
        val isFilled = sharedPreferences.getBoolean("filled",false)
        //Esta condição define qual a atividade para o qual avançamos, se o utilizador já tiver preenchido uma vez
        //a página de set-up então avança para o menu. CC, avança para o set-up.
        if (isFilled==true){
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@Splashscreen, MainActivity::class.java)
            startActivity(intent)
            //Toast que inicia quando se abre o menu.
            //Foi necessário usar um handler porque não se pode mudar a duração do toast manualmente.
            val text = "Welcome to the menu!!"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
            Handler(Looper.getMainLooper()).postDelayed({
                toast.cancel()
            },2000)
            finish()
        }, 2000)}

        else{
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@Splashscreen, SharedPreferences::class.java)
                startActivity(intent)
                finish()
            }, 2000)}}
        }