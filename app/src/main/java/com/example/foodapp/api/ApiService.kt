package com.example.foodapp.api

import com.example.foodapp.database.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MealApiService() {
    private val baseUrl = "https://www.themealdb.com/api/json/v1/1/"
    private val endpointSearchIngredient = "filter.php?i="
    private val endpointSearchById = "lookup.php?i="

    private var id:Int=0
    private lateinit var Meal:String
    private lateinit var DrinkAlternate:String
    private lateinit var Category:String
    private lateinit var Area:String
    private lateinit var Instructions:String
    private lateinit var MealThumb:String
    private lateinit var Tags:String
    private lateinit var YouTube:String
    private lateinit var Ingredients:Array<String?>
    private lateinit var Measure:Array<String?>
    private lateinit var Source:String
    private lateinit var ImageSource:String
    private lateinit var CreativeCommonsConfirmed:String
    private lateinit var dateModified: String

    private lateinit var meal: Meal
    private lateinit var ingredients:Array<String>
    private lateinit var measures:Array<String>
    private lateinit var recievedMeals:Array<Meal>

    suspend fun searchAllMealsByIngredient(searchText:String) {
        val url = URL("$baseUrl$endpointSearchIngredient$searchText")
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection

        withContext(Dispatchers.IO) {
            val bf = BufferedReader(InputStreamReader(con.inputStream))
            val stb = StringBuilder()
            var line: String? = bf.readLine()
            while (line != null) {
                stb.append(line)
                line = bf.readLine()
            }
            val json:String=stb.toString()
            val jsonObject = JSONObject(json)
            sendRecievedData(jsonObject)
        }
    }

    suspend fun getDataByID(id:String):JSONObject {
        val url = URL("$baseUrl$endpointSearchById$id")
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection
        val json:String
        val jsonObject:JSONObject

        withContext(Dispatchers.IO) {
            val bf = BufferedReader(InputStreamReader(con.inputStream))
            val stb = StringBuilder()
            var line: String? = bf.readLine()
            while (line != null) {
                stb.append(line)
                line = bf.readLine()
            }
            json=stb.toString()
            jsonObject = JSONObject(json)
        }
        return jsonObject
    }


    private suspend fun sendRecievedData(jsonObject: JSONObject){
        val mealsjsonArray = jsonObject.getJSONArray("meals")
        var mealjsonArray:JSONArray
        var mealJSONObject:JSONObject
        println(mealsjsonArray)
        for(i in 0..mealsjsonArray.length()-1){
            var jsonObject = mealsjsonArray.getJSONObject(i)
            val idMeal = jsonObject.getString("idMeal")
            mealJSONObject=getDataByID(idMeal)
            mealjsonArray = mealJSONObject.getJSONArray("meals")
            jsonObject = mealjsonArray.getJSONObject(0)
            println(jsonObject)
        }
    }

    private fun setMealObject(jsonObject:JSONObject){

    }
}
