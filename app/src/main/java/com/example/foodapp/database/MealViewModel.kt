package com.example.foodapp.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomapp.data.MealRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MealViewModel(application: Application): AndroidViewModel(application) {

    private val repository: MealRepo
    val readAllData: LiveData<List<Meal>>

    init {
        val mealDao = MealDatabase.getDatabase(application).mealDao()
        repository = MealRepo(mealDao)
        readAllData = repository.getAllMeals
    }

    fun addMeal(meal: Meal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMeal(meal)
        }
    }

    fun addAll(vararg meal: Meal){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAlll(*meal)
        }
    }

    fun getAll():LiveData<List<Meal>>{
        return readAllData
    }


}