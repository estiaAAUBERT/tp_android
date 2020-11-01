package com.example.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var clickButton: Button
    private lateinit var computeButton: Button
    private var nbClick = 0
    private lateinit var clickText : TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickButton = findViewById(R.id.btn_click_me)
        clickText = findViewById(R.id.textView4)
        computeButton = findViewById(R.id.btn_compute)

        clickButton?.setOnClickListener {
            nbClick++
            val newText = "Vous avez cliqué $nbClick fois"
            clickText.text = newText

            if(nbClick >= 5){
                clickButton.isEnabled = false
            }
        }

        computeButton.setOnClickListener {
            val intent = Intent(baseContext, ComputeActivity::class.java)
            startActivity(intent)
        }
    }
}