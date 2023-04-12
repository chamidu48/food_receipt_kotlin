package com.example.foodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnsearchdb:Button=findViewById(R.id.btnsearchdb)
        btnsearchdb.setOnClickListener {
            val intent1 = Intent(this, Search::class.java)
            startActivity(intent1)
        }

        val btnadd:Button=findViewById(R.id.btnadd)
        btnadd.setOnClickListener {
            addMealsMannually()
        }

        val btnsearchingredients:Button=findViewById<Button>(R.id.btnsearchingredients)
        btnsearchingredients.setOnClickListener {
            val intent2 = Intent(this, SearchIngredients::class.java)
            startActivity(intent2)
        }

        val btnsearchmeals:Button=findViewById<Button>(R.id.btnsearchformeals)
        btnsearchmeals.setOnClickListener {
            val intent3 = Intent(this,SearchMeals::class.java)
            startActivity(intent3)
        }
    }

    private fun addMealsMannually() {

    }
}