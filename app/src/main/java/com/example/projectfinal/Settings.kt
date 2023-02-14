package com.example.projectfinal

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.*
import kotlinx.android.synthetic.main.activity_settings.*
import java.util.*

class Settings : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    //Aqui são inicializadas as variáveis para o calendário
    private var day = 0
    private var month= 0
    private var year = 0

    private var savedDay=""
    private var savedMonth=""
    private var savedYear=""

    //Função que futuramente ao se clickar no seu butão vai dar display ao calendário para escolher uma data
    override fun onDateSet(view: DatePicker?, dayOfMonth: Int, month: Int, year: Int) {
        savedDay=dayOfMonth.toString()
        savedMonth=(month+1).toString()
        savedYear=year.toString()

        val birthDate = findViewById<TextView>(R.id.birthdayidset)

        birthDate.text = "$savedYear/$savedMonth/$savedDay"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        pickDate()

        supportActionBar?.hide()


        //Novamente shared prefs para a cor do background
        val sharedPreferences = getSharedPreferences("colorPrefs", MODE_PRIVATE)
        val savedR=sharedPreferences.getInt("r",255)
        val savedG=sharedPreferences.getInt("g",255)
        val savedB=sharedPreferences.getInt("b",255)

        val backGround: ImageView = findViewById(R.id.settingsmenu)
        backGround.setBackgroundColor(Color.rgb(savedR,savedG,savedB))

        bt_saveset.setOnClickListener{
            saveData()
        }

        //Esta inicialização vai dar display as variáveis que já tinhamos posto inicialmente
        val sharedPref=getSharedPreferences("prefs",0)
        val namePref=sharedPref.getString("name","")
        val agePref=sharedPref.getString("age","")
        val coursePref=sharedPref.getString("course","")
        val birthdayPref=sharedPref.getString("birthday","")
        val genderPref=sharedPref.getString("gender","")
        val nameSet = findViewById<TextView>(R.id.nameidset)
        nameSet.text=namePref
        val ageSet = findViewById<TextView>(R.id.ageidset)
        ageSet.text=agePref
        val courseSet = findViewById<TextView>(R.id.courseidset)
        courseSet.text=coursePref
        val birthdaySet = findViewById<TextView>(R.id.birthdayidset)
        birthdaySet.text=birthdayPref
        val genderSet = findViewById<TextView>(R.id.genderidset)
        genderSet.text=genderPref

        //Vai dar display a um menu de opções (usei duas vezes o mesmo findView para não gerar confusões)
        val pickGender = findViewById<TextView>(R.id.genderidset)
        pickGender.setOnClickListener{
            val popupMenu: PopupMenu = PopupMenu(this,pickGender)
            popupMenu.menuInflater.inflate(R.menu.menu,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.Male ->
                        pickGender.text =item.title
                    R.id.Female ->
                        pickGender.text=item.title
                    R.id.Other ->
                        pickGender.text=item.title


                }
                true

            })
            popupMenu.show()

        }

    }
    //Função que traduz o que escolhemos no calendário
    private fun getDate(){
        val cal:Calendar = Calendar.getInstance()
        day=cal.get(Calendar.DAY_OF_MONTH)
        month=cal.get(Calendar.MONTH)
        year=cal.get(Calendar.YEAR)
    }

    //Vai mostrar o calendário para escolhermos a data
    private fun pickDate(){
        val pickDate = findViewById<TextView>(R.id.birthdayidset)

        pickDate.setOnClickListener{
            getDate()
            DatePickerDialog(this,R.style.customDatePickerStyle, this, year, month, day).show()

        }

    }

    //Função que vai guardar as nossas alterações nas shared prefs alterando as anteriores
    private fun saveData(){
        val preferences = getSharedPreferences("prefs", MODE_PRIVATE)
        val editor  = preferences.edit()

        val name = nameidset.text.toString()
        val age  = ageidset.text.toString()
        val birthDay = birthdayidset.text.toString()
        val course = courseidset.text.toString()

        editor.apply {
            putString("name",name)
            putString("age", age)
            putString("course", course)
            putString("birthday", birthDay)
            putString("gender", genderidset.text.toString())
        }.apply()

        val list=listOf(name,age,birthDay,course,genderidset.text.toString())
        var filled = true
        //Esta condição impede o utilizador de avançar caso não tenha preenchido tudo
        //Se está tudo preenchido filled = true e volta ao menu. CC mostra um toast e não avança.
        for (id in list){
            if (id=="") {
                filled = false
            }
        }
        if (filled){
            val intent = Intent(this,MainActivity::class.java )
            startActivity(intent)
            finish()
        }
        else{
            val text = "You need to fill everything in!"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
            Handler(Looper.getMainLooper()).postDelayed({
                toast.cancel()
            },1000)

        }

    }
}
