package com.example.appcours2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //showWebPage()
        CallMe()
    }

    fun showWebPage(){
        val chemin = Uri.parse("http://www.google.fr")
        val naviguer: Intent = Intent(Intent.ACTION_VIEW, chemin)
        startActivity(naviguer)
    }

    fun CallMe(){
        val numero = Uri.parse("tel:0123456789")
        val appeler:Intent = Intent(Intent.ACTION_DIAL, numero)
        startActivity(appeler)

    }
}