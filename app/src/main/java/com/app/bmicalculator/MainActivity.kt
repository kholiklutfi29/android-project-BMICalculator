package com.app.bmicalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etWeight: EditText
    private lateinit var etHeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvIndex: TextView
    private lateinit var tvState: TextView
    private lateinit var tvInfo: TextView

    private fun initInputComponent(){
        etWeight = findViewById(R.id.etWeight)
        etHeight = findViewById(R.id.etHeight)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun initShowComponent(){
        tvIndex = findViewById(R.id.tvIndex)
        tvState = findViewById(R.id.tvState)
        tvInfo = findViewById(R.id.tvInfo)
    }

    private fun validationInput(weight: String?, height: String?): Boolean {
        return when{
            weight.isNullOrEmpty() && height.isNullOrEmpty() -> {
                Toast.makeText(this,"You haven't input anything yet", Toast.LENGTH_LONG).show()
                false
            }

            weight.isNullOrEmpty() -> {
                Toast.makeText(this,"Please input weight", Toast.LENGTH_LONG).show()
                false
            }

            height.isNullOrEmpty() -> {
                Toast.makeText(this,"Please input height", Toast.LENGTH_LONG).show()
                false
            }

            else -> true
        }
    }

    private  fun displayResult(bmi: Float){
        tvIndex.text = bmi.toString()
        tvInfo.text = "(Normal range is 18.5 - 24.9)"

        var text = ""
        var color = 0

        when{
            bmi < 18.50 -> {
                text = "Underweight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99 -> {
                text = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99 -> {
                text = "Overweight"
                color = R.color.over_weitght
            }
            bmi > 29.99 -> {
                text = "Obese"
                color = R.color.obese
            }
        }

        tvState.text = text
        tvState.setTextColor(ContextCompat.getColor(this, color))

    }


    private fun startApp(){
        initInputComponent()
        initShowComponent()

        btnCalculate.setOnClickListener {
            val weight = etWeight.text.toString()
            val height = etHeight.text.toString()

            if (validationInput(weight, height)){
                val bmi = weight.toFloat() / ((height.toFloat() / 100 ) * (height.toFloat() / 100 ))

                // get result with two decimal places
                val bmiTwoDigits = String.format("%.2f",bmi).toFloat()
                displayResult(bmiTwoDigits)
            }
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startApp()

    }
}