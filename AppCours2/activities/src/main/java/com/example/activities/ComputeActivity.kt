package com.example.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ComputeActivity: AppCompatActivity(), TextWatcher {

    private lateinit var fieldNumber1: EditText
    private lateinit var fieldNumber2: EditText
    private lateinit var resultText : TextView
    private lateinit var calculButton: Button


    override fun beforeTextChanged(s:   CharSequence?, start: Int, count: Int, after: Int) {
        calculButton.isEnabled = false
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        calculButton.isEnabled = false
        resultText.text=""
        if(fieldNumber1.text.isNotBlank() && fieldNumber2.text.isNotBlank()){
            calculButton.isEnabled = true
        }
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compute_activity)

        fieldNumber1 = findViewById(R.id.field_1)
        fieldNumber2 = findViewById(R.id.field_2)
        resultText = findViewById(R.id.resultat)
        calculButton = findViewById(R.id.btn_calculer)


        fieldNumber1.addTextChangedListener(this)
        fieldNumber2.addTextChangedListener(this)
        calculButton.isEnabled=false

        calculButton.setOnClickListener {
            val resultFloat = CalculSum(fieldNumber1.text.toString().toFloat(), fieldNumber2.text.toString().toFloat())
            resultText.text = "Le résultat est $resultFloat"
        }

    }

    private fun CalculSum(a: Float, b: Float):Float{
        return a.plus(b)
    }
}