package com.example.foodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1:Button=findViewById(R.id.btnsearch)
        button1.setOnClickListener {
            val intent1 = Intent(this, Search::class.java)
            startActivity(intent1)
        }

        val buttton2:Button=findViewById<Button>(R.id.btnsearchingredients)
        buttton2.setOnClickListener {
            val intent2 = Intent(this, SearchIngredients::class.java)
            startActivity(intent2)
        }

        val button3:Button=findViewById<Button>(R.id.btnsearchformeals)
        button3.setOnClickListener {
            val intent3 = Intent(this,SearchMeals::class.java)
            startActivity(intent3)
        }
    }
}