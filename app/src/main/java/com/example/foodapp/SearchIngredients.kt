package com.example.foodapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.api.MealApiService
import com.example.foodapp.database.Meal
import com.example.foodapp.utils.Loader
import com.example.foodapp.utils.MyAdapter
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SearchIngredients : AppCompatActivity() {

    private lateinit var recyclerView:RecyclerView
    private lateinit var mealList: ArrayList<Meal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_ingredients)


        val resultsTv:TextView=findViewById<TextView>(R.id.resultCount_tv)
        val searchbar:EditText=findViewById(R.id.searchIngre_et)
        val searchButton: Button =findViewById<Button>(R.id.btnsearch)

        val mealApiService:MealApiService= MealApiService()
        val loader=Loader(this)
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        searchButton.setOnClickListener {
            imm.hideSoftInputFromWindow(resultsTv.getWindowToken(), 0)
            var searchedText=searchbar.text.toString()
            if(isAlphabetic(searchedText)){
                runBlocking {
                    loader.startLoader()
                    launch {
                        mealApiService.searchAllMealsByIngredient(searchedText)
                        mealList=mealApiService.getResults()
                        loader.stopLoader()
                        resultsTv.text="${mealList.size} results found"
                        recyclerView.adapter=MyAdapter(mealList)
                    }
                }
            }else{println("No results!")}
        }
    }
    private fun isAlphabetic(str: String): Boolean {
        return str.matches("[a-zA-Z]+".toRegex())
    }
}