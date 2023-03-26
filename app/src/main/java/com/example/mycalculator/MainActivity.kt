package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {


    private lateinit var editTextHeight: EditText
    private lateinit var editTextWeight: EditText
    private lateinit var editTextAge: EditText
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var radioButtonMale: RadioButton
    private lateinit var radioButtonFemale: RadioButton
    private lateinit var spinnerActivityLevel: Spinner
    private lateinit var buttonCalculate: Button
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextHeight = findViewById<EditText>(R.id.editTextHeight)
        editTextWeight = findViewById<EditText>(R.id.editTextWeight)
        editTextAge = findViewById<EditText>(R.id.editTextAge)
        radioGroupGender = findViewById<RadioGroup>(R.id.radioGroupGender)
        radioButtonMale = findViewById<RadioButton>(R.id.radioButtonMale)
        radioButtonFemale = findViewById<RadioButton>(R.id.radioButtonFemale)
        spinnerActivityLevel = findViewById<Spinner>(R.id.spinnerActivityLevel)
        buttonCalculate = findViewById<Button>(R.id.buttonCalculate)
        textViewResult = findViewById<TextView>(R.id.textViewResult)

        buttonCalculate.setOnClickListener {
            calculateBMR()
        }
    }

    private fun calculateBMR() {
        val height = editTextHeight.text.toString().toFloat()
        val weight = editTextWeight.text.toString().toFloat()
        val age = editTextAge.text.toString().toInt()
        val gender = if (radioButtonMale.isChecked) "male" else "female"
        val activityLevel = spinnerActivityLevel.selectedItem.toString()

        // Calculate BMR based on gender, age, weight, height and activity level
        val bmr = when (gender) {
            "Male" -> calculateMaleBMR(weight, height, age, activityLevel)
            "Female" -> calculateFemaleBMR(weight, height, age, activityLevel)
            else -> 0f
        }

        // Display the result
        textViewResult.text = String.format("Your BMR is %.2f", bmr)
    }

    private fun calculateMaleBMR(
        weight: Float,
        height: Float,
        age: Int,
        activityLevel: String
    ): Float {
        // Calculate BMR for males
        val bmr = 10 * weight + 6.25f * height - 5 * age + 5

        // Adjust BMR based on activity level
        return when (activityLevel) {
            "Sedentary" -> bmr * 1.2f
            "Lightly active" -> bmr * 1.375f
            "Moderately active" -> bmr * 1.55f
            "Very active" -> bmr * 1.725f
            "Extra active" -> bmr * 1.9f
            else -> bmr
        }
    }

    private fun calculateFemaleBMR(
        weight: Float,
        height: Float,
        age: Int,
        activityLevel: String
    ): Float {
        // Calculate BMR for females
        val bmr = 10 * weight + 6.25f * height - 5 * age - 161

        // Adjust BMR based on activity level
        return when (activityLevel) {
            "Sedentary" -> bmr * 1.2f
            "Lightly active" -> bmr * 1.375f
            "Moderately active" -> bmr * 1.55f
            else -> bmr
        }
    }
}