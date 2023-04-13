package com.example.foodapp.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.database.Meal

class MyAdapter(private val mealList:ArrayList<Meal>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.search_item_card,
        parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=mealList[position]
        holder.cardTitle.text=currentItem.Meal
        holder.cardSubtitle.text=currentItem.Category
    }

    override fun getItemCount(): Int {
        return mealList.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cardTitle:TextView=itemView.findViewById(R.id.cardTitle)
        val cardSubtitle:TextView=itemView.findViewById(R.id.cardSubtitle)
        val cardIcon:ImageButton=itemView.findViewById(R.id.cardIcon)
    }

}