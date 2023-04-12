package com.example.foodapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.sql.RowId

@Dao
interface MealDao {

    @Insert
    suspend fun addMeal(meal: Meal)

    @Insert
    suspend fun addAll(vararg meal: Meal)

    @Query("SELECT * FROM Meal")
    suspend fun getAll():List<Meal>

    @Query("SELECT * FROM Meal WHERE Meal LIKE '%' || :searchInput || '%' OR Ingredients LIKE '%' || :searchInput || '%'")
    suspend fun getMealByIngredient(searchInput:String):List<Meal>

    @Query("SELECT * FROM Meal WHERE Meal LIKE '%' || :searchInput || '%'")
    suspend fun getMealByName(searchInput:String):List<Meal>
}