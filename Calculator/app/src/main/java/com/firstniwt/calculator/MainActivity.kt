package com.firstniwt.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var currentOperator: EditText
    var number1:Float = 0.0F
    var operation:String = "NONE"
    var numberofdots:Int = 0
    var result :Boolean = true
    var operatorselected = false
    var secondOperand = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        editText = findViewById(R.id.number_edit_text)
        currentOperator = findViewById(R.id.current_operator)

    }
    fun operationFunction(view: View){


        when(view.id){
            R.id.button_one -> {
                numberClicked(1)
            }
            R.id.button_two -> {
                numberClicked(2)
            }

            R.id.button_three -> {
                numberClicked(3)
            }
            R.id.button_four -> {
                numberClicked(4)
            }
            R.id.button_five -> {
                numberClicked(5)
            }
            R.id.button_six -> {
                numberClicked(6)
            }
            R.id.button_seven -> {
                numberClicked(7)
            }
            R.id.button_eight -> {
                numberClicked(8)
            }
            R.id.button_nine -> {
                numberClicked(9)
            }
            R.id.button_zero -> {
                numberClicked(0)
            }

            R.id.button_dot-> {
                addDot()
            }

            R.id.button_clear-> {
                clearScreen()
            }

            R.id.button_plus-> {
                operation("plus")
            }
            R.id.button_minus-> {
                operation("minus")
            }
            R.id.button_multiply -> {
                operation("multiply")
            }
            R.id.button_division-> {
                operation("division")

            }
            R.id.button_percent-> {
                operation("percent")

            }
            R.id.button_equal-> {
                equals()
            }


        }

    }
    private fun numberClicked(number_clicked: Int){
            if(result)
            {
                result = false
                editText.setText("")
            }
            if(operatorselected)
            {
                editText.setText("")
                operatorselected = false
                secondOperand = true
            }
            val number:String = editText.text.toString() + number_clicked.toString()
            editText.setText(number)
    }
    private fun operation(operation_name: String){
        if(!secondOperand){numberofdots=0}
        if(editText.text.toString() !="NONE" && !secondOperand)
        {
            when(operation_name){
                "plus"-> {
                    currentOperator.setText("+")
                    if(editText.text.toString()!="")
                        number1 = editText.text.toString().toFloat()
                    //editText.setText("")
                    operation = "plus"
                    operatorselected = true
                }
                "minus"->{
                    currentOperator.setText("-")
                    if(editText.text.toString()!="")
                        number1 = editText.text.toString().toFloat()
                    //editText.setText("")
                    operation = "minus"
                    operatorselected = true
                }
                "multiply"->{
                    currentOperator.setText("x")
                    if(editText.text.toString()!="")
                        number1 = editText.text.toString().toFloat()
                    //editText.setText("")
                    operation = "multiply"
                    operatorselected = true
                }
                "division"->{
                    currentOperator.setText("÷")
                    if(editText.text.toString()!="")
                        number1 = editText.text.toString().toFloat()
                    //editText.setText("")
                    operation = "division"
                    operatorselected = true
                }
                "percent"->{
                    currentOperator.setText("%")
                    if(editText.text.toString()!="")
                        number1 = editText.text.toString().toFloat()
                    //editText.setText("")
                    operation = "percent"
                    operatorselected = true
                }
            }
        }

    }
    private fun equals(){
        if(!operatorselected && editText.text.toString()!="" && !result) {
            val number2: Float = editText.text.toString().toFloat()
            when (operation) {
                "plus" -> {
                    val result = number1 + number2
                    editText.setText(result.toString())
                    operation = "NONE"
                }

                "minus" -> {
                    val result = number1 - number2
                    editText.setText(result.toString())
                    operation = "NONE"
                }

                "multiply" -> {
                    val result = number1 * number2
                    editText.setText(result.toString())
                    operation = "NONE"
                }

                "division" -> {
                    val result = number1 / number2
                    editText.setText(result.toString())
                    operation = "NONE"
                }
                "percent" -> {
                    val result = number1 / 100 * number2
                    editText.setText(result.toString())
                    operation = "NONE"
                }

                else -> {
                }

            }
            currentOperator.setText("")
            numberofdots = 1
            result = true
            operatorselected = false
            secondOperand = false
        }
    }
    private fun addDot(){

        if(numberofdots==0 && !operatorselected&& editText.text.toString()!="") {
            val number: String = editText.text.toString() + "."
            editText.setText(number)
            numberofdots += 1

        }

    }
    private fun clearScreen()
    {   numberofdots = 0
        editText.setText("")
        currentOperator.setText("")
        number1 = 0.0F
    }
}