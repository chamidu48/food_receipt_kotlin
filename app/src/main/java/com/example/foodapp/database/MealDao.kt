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
    fun getAll():LiveData<List<Meal>>

    @Query("SELECT * FROM recipes WHERE name LIKE '%' || :searchInput || '%' OR ingredient LIKE '%' || :searchInput || '%'")
    fun getMealByIngredient(searchInput:String):LiveData<List<Meal>>
}