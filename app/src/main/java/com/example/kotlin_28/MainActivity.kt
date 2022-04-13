package com.example.kotlin_28

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mathFun = resources.getStringArray(R.array.mathFun)
        val spinner = findViewById<Spinner>(R.id.spinner)

        val but = findViewById<Button>(R.id.getSolution)

        val answerField = findViewById<TextView>(R.id.solution)

        var answer : Int = 0

        but.setOnClickListener {

            val getSideA = findViewById<EditText>(R.id.sideA)
            val getSideB = findViewById<EditText>(R.id.sideB)
            val getSideC = findViewById<EditText>(R.id.sideC)

            val sideAText = getSideA.text.toString()
            val sideBText = getSideB.text.toString()
            val sideCText = getSideC.text.toString()

            var sideA = sideAText.toIntOrNull()
            var sideB = sideBText.toIntOrNull()
            var sideC = sideCText.toIntOrNull()

            if(sideA == null || sideB == null || sideC == null){
                answerField.text = "Ошибка ввода"
                Log.e(TAG, "$sideA, $sideB, $sideC i gde zdes oshibka?")
            }
            else if (spinner != null && getSideA.text != null && getSideB.text != null && getSideC.text != null) {
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mathFun)
                spinner.adapter = adapter

                spinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        when {
                            mathFun[position] == "Сумма длины рёбер" -> answer =
                                (sideA + sideB + sideC) * 4
                            mathFun[position] == "Площадь поверхности" -> answer =
                                2 * (sideA * sideB) + 2 * (sideA * sideC) + 2 * (sideB * sideC)
                            mathFun[position] == "Объём" -> answer = sideA * sideB * sideC
                        }
                        answerField.text = "$answer"
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
            }
        }
    }
}

