package com.example.mindmatrixsurayashaktimonitoringsystem

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etGeneration: EditText
    private lateinit var etConsumption: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etGeneration = findViewById(R.id.etGeneration)
        etConsumption = findViewById(R.id.etConsumption)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvResult = findViewById(R.id.tvResult)

        btnCalculate.setOnClickListener {
            calculateEnergy()
        }
    }

    private fun calculateEnergy() {
        val generationText = etGeneration.text.toString()
        val consumptionText = etConsumption.text.toString()

        if (generationText.isEmpty() || consumptionText.isEmpty()) {
            Toast.makeText(
                this,
                "Please enter all values",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        val generation = generationText.toDoubleOrNull()
        val consumption = consumptionText.toDoubleOrNull()

        if (generation == null || consumption == null || consumption == 0.0) {
            Toast.makeText(
                this,
                "Please enter valid values",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        val savings = generation * 8
        val greenScore = ((generation / consumption) * 100).toInt()

        val status = if (generation > consumption) {
            "Over Generation: Exporting Electricity to Grid"
        } else if (generation == consumption) {
            "Balanced Energy Usage"
        } else {
            "Using Grid Electricity"
        }

        tvResult.text =
            "Solar Generation : $generation kWh\n\n" +
                "Consumption : $consumption kWh\n\n" +
                "Savings : Rs. $savings\n\n" +
                "Green Score : $greenScore%\n\n" +
                status
    }
}
