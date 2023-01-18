package com.example.androidfinaltest.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfinaltest.Products
import com.example.androidfinaltest.R
import com.example.androidfinaltest.storeProductsArray
import com.squareup.picasso.Picasso



class MyAdapter (private val products : ArrayList<Products>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = products[position]

        Picasso.get()
            .load(currentItem.image)
            .resize(200, 200)
            .centerCrop()
            .into(holder.listImage)

        holder.listTitle.text = currentItem.title
        holder.listDesc.text = currentItem.description.substring(0, 50) + "..."
        holder.listPrice.text = currentItem.price.toString() + "$"


        holder.listBTN.setOnClickListener {
            if(!storeProductsArray.contains(Products(currentItem.id, currentItem.title, currentItem.price, currentItem.description, currentItem.category, currentItem.image, currentItem.rating))){
                storeProductsArray.add(Products(currentItem.id, currentItem.title, currentItem.price, currentItem.description, currentItem.category, currentItem.image, currentItem.rating))
            }
        }


    }



    override fun getItemCount(): Int {
        return products.size
    }


    class  MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){
        val listImage : ImageView = itemview.findViewById(R.id.list_img)
        val listTitle : TextView = itemview.findViewById(R.id.list_text)
        val listDesc : TextView = itemview.findViewById(R.id.text_desc)
        val listPrice : TextView = itemview.findViewById(R.id.list_price)
        val listBTN : Button = itemview.findViewById(R.id.addToCartBTN)
    }




}