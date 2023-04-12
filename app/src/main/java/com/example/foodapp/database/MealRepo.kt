package com.example.roomapp.data

import androidx.lifecycle.LiveData
import com.example.foodapp.database.Meal
import com.example.foodapp.database.MealDao

class MealRepo(private val mealDao: MealDao) {

    val readAllData: LiveData<List<Meal>> = mealDao.getAll()

    suspend fun addMeal(meal: Meal){
        mealDao.addMeal(meal)
    }

}