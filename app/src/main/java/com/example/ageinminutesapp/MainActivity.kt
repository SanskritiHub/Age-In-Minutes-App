package com.example.ageinminutesapp

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)

    var tvSelectedDate: TextView? = null
    var tvMinutesTillDate: TextView? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val button3: Button = findViewById(R.id.button3)
        tvSelectedDate =  findViewById(R.id.tvSelectedDate)
        tvMinutesTillDate = findViewById(R.id.tvMinutesTillDate)
        button3.setOnClickListener{
            button()

        }

    }

     @RequiresApi(Build.VERSION_CODES.N)
     fun button() {
         val myCalendar = Calendar.getInstance()
         val year = myCalendar.get(Calendar.YEAR)
         val day = myCalendar.get(Calendar.DAY_OF_MONTH)
         val month = myCalendar.get(Calendar.MONTH)
         val dpd = DatePickerDialog( this,
            { _, year, month, dayOfMonth ->

                Toast.makeText(this, "LMAO!", Toast.LENGTH_LONG).show()

                val selectedDate = "$dayOfMonth/ ${month+1}/$year"
                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate.time/60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateInMinutes = currentDate.time/60000

                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                tvMinutesTillDate?.text  = differenceInMinutes.toString()
            },

         year,
         month,
         day)

         dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000

         dpd.show()


    }


}