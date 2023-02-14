package com.example.projectfinal

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.*
import kotlinx.android.synthetic.main.activity_shared_preferences.*
import java.util.*

class SharedPreferences : AppCompatActivity(), DatePickerDialog.OnDateSetListener {


    //Aqui são inicializadas as variáveis do calendário
    //Faz a mesma coisa que as settings, a única nova particularidade é o booleano filled
    private var day = 0
    private var month= 0
    private var year = 0

    private var savedDay=""
    private var savedMonth=""
    private var savedYear=""

    override fun onDateSet(view: DatePicker?, dayOfMonth: Int, month: Int, year: Int) {
        savedDay=dayOfMonth.toString()
        savedMonth=(month+1).toString()
        savedYear=year.toString()

        val birthDate = findViewById<TextView>(R.id.birthdayid)

        birthDate.text = "$savedYear/$savedMonth/$savedDay"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)

        pickDate()

        supportActionBar?.hide()

        bt_save.setOnClickListener{
            saveData()
        }


        val pickGender = findViewById<TextView>(R.id.genderid)
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

    private fun getDate(){
        val cal:Calendar = Calendar.getInstance()
        day=cal.get(Calendar.DAY_OF_MONTH)
        month=cal.get(Calendar.MONTH)
        year=cal.get(Calendar.YEAR)
    }

    private fun pickDate(){
        val pickDate = findViewById<TextView>(R.id.birthdayid)

        pickDate.setOnClickListener{
            getDate()
            DatePickerDialog(this,R.style.customDatePickerStyle, this, year, month, day).show()

        }

    }

    private fun saveData(){
        val preferences = getSharedPreferences("prefs", MODE_PRIVATE)
        val editor  = preferences.edit()

        val name = nameid.text.toString()
        val age  = ageid.text.toString()
        val birthDay = birthdayid.text.toString()
        val course = courseid.text.toString()

        editor.apply {
            putString("name",name)
            putString("age", age)
            putString("course", course)
            putString("birthday", birthDay)
            putString("gender", genderid.text.toString())
            }.apply()

        val list=listOf(name,age,birthDay,course,genderid.text.toString())
        var filled = true
        for (id in list){
            if (id=="") {
                filled = false
            }
        }
        if (filled){
            val intent = Intent(this,MainActivity::class.java )
            //O booleano filled vai ser usado futuramente no splashscreen para dizer se ora avança
            //para o set-up de definições ora para o menu (foi incluído para não estar sempre a pedir um set-up
            //novo cada vez que se abria a app
            //Também mostra um toast ao se abrir o menu.
            editor.apply{
                putBoolean("filled",true)
            }.apply()
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
