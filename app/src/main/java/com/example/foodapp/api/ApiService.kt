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

    private lateinit var id:String
    private lateinit var Name:String
    private var DrinkAlternate:String?= null
    private lateinit var Category:String
    private lateinit var Area:String
    private lateinit var Instructions:String
    private var MealThumb:String? = null
    private var Tags:String?= null
    private var YouTube:String?= null
    private lateinit var Ingredients:ArrayList<String?>
    private lateinit var Measure:ArrayList<String?>
    private var Source:String?= null
    private var ImageSource:String?= null
    private var CreativeCommonsConfirmed:String?= null
    private var dateModified: String?= null

    private lateinit var meal: Meal
    private lateinit var recievedMeals:ArrayList<Meal>

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
            setMealObject(jsonObject)
        }
    }

    private fun setMealObject(jsonObject:JSONObject){
        id=jsonObject.getString("idMeal")
        Name=jsonObject.getString("strMeal")
        DrinkAlternate=jsonObject.getString("strDrinkAlternate")
        Category=jsonObject.getString("strCategory")
        Area=jsonObject.getString("strArea")
        Instructions=jsonObject.getString("strInstructions")
        MealThumb=jsonObject.getString("strMealThumb")
        Tags=jsonObject.getString("strTags")
        YouTube=jsonObject.getString("strYoutube")
        Source=jsonObject.getString("strSource")
        ImageSource=jsonObject.getString("strImageSource")
        CreativeCommonsConfirmed=jsonObject.getString("strCreativeCommonsConfirmed")
        dateModified=jsonObject.getString("dateModified")

        Ingredients = ArrayList<String?>()
        Measure = ArrayList<String?>()
        recievedMeals = ArrayList<Meal>()

        for (j in 1..20){
            Ingredients.add(jsonObject.getString("strIngredient$j"))
        }
        for (j in 1..20){
            Measure.add(jsonObject.getString("strMeasure$j"))
        }

        meal=Meal(Integer.parseInt(id),Name,DrinkAlternate,Category,Area,Instructions,MealThumb,Tags,YouTube,Ingredients,Measure,Source,ImageSource,CreativeCommonsConfirmed,dateModified)
        recievedMeals.add(meal)
        println(recievedMeals.size)
    }
}
