package com.example.foodapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Meal(
    val Meal:String,
    val DrinkAlternate:String,
    val Category:String,
    val Area:String,
    val Instructions:String,
    val Tags:String,
    val Ingredients:MutableList<String>,
    val Measure:MutableList<String>
){
    @PrimaryKey(autoGenerate = true)
    val id:Int=0

}