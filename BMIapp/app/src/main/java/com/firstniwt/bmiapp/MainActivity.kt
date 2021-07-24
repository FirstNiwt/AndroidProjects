package com.firstniwt.bmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var bmi:Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculated_BMI
        input_height
        input_weight
        body_type_image

    }

    fun calculateBMI(view: View){
        if(input_height.text.isEmpty()  || input_weight.text.isEmpty())
        {
            Toast.makeText(this,"Enter the needed values!", Toast.LENGTH_SHORT).show()
        }

        else{
        val height:Float = input_height.text.toString().toFloat()
        val weight:Float = input_weight.text.toString().toFloat()


                bmi = weight / (height * height)

                calculated_BMI.text = bmi.toString()



        when {
            bmi < 18.5 -> {
                body_type_image.setImageResource(R.drawable.ic_underweight)
                body_type_image.visibility = View.VISIBLE
                calculated_BMI.visibility = View.VISIBLE
            }
            bmi in 18.5..24.9 -> {
                body_type_image.setImageResource(R.drawable.ic_healthy)
                body_type_image.visibility = View.VISIBLE
                calculated_BMI.visibility = View.VISIBLE
            }
            bmi in 25.0..29.9 -> {
                body_type_image.setImageResource(R.drawable.ic_overweight)
                body_type_image.visibility = View.VISIBLE
                calculated_BMI.visibility = View.VISIBLE
            }
            else -> {
                body_type_image.setImageResource(R.drawable.ic_obesity)
                body_type_image.visibility = View.VISIBLE
                calculated_BMI.visibility = View.VISIBLE

            }
        }
        }

    }
}