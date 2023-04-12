package com.example.foodapp.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.roomapp.data.MealRepo


class MealViewModel(application: Application): AndroidViewModel(application) {

    private val repository: MealRepo
    val readAllData: LiveData<List<Meal>>

    init {
        val mealDao = MealDatabase.getDatabase(application).mealDao()
        repository = MealRepo(mealDao)
        readAllData = repository.getAllMeals
    }

    fun addMeal(meal: Meal) {
        repository.addMeal(meal)
    }

}