package com.example.roomapp.data

import androidx.lifecycle.LiveData
import com.example.foodapp.database.Meal
import com.example.foodapp.database.MealDao

class MealRepo(private val mealDao: MealDao) {

    val getAllMeals: LiveData<List<Meal>> = mealDao.getAll()

    fun addMeal(meal: Meal){
        mealDao.addMeal(meal)
    }
    fun addAlll(vararg meal:Meal){
        mealDao.addAll(*meal)
    }
    fun searchMeal(searchText:String):LiveData<List<Meal>>{
        val getSearchedMeals:LiveData<List<Meal>> =mealDao.searchMeal(searchText)
        return getSearchedMeals
    }

}