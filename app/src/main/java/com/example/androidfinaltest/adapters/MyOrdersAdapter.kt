package com.example.androidfinaltest.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfinaltest.Products
import com.example.androidfinaltest.R
import com.example.androidfinaltest.room.StoreProducts
import com.example.androidfinaltest.storeProductsArray
import com.squareup.picasso.Picasso



class MyOrdersAdapter (private val products : List<StoreProducts>) : RecyclerView.Adapter<MyOrdersAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.orders_list, parent, false)
        return MyViewHolder(itemView)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = products[position]
        holder.orderTitle.text = currentItem.title
        holder.fullname.text = currentItem.name + " "+ currentItem.lastname
        holder.address.text = currentItem.address
        holder.totalyPrice.text = currentItem.totalPrice.toString() + "$"
    }

    override fun getItemCount(): Int {
        return products.size
    }




    class  MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){
        val fullname : TextView = itemview.findViewById(R.id.orderFullname)
        val address : TextView = itemview.findViewById(R.id.orderAddress)
        val totalyPrice : TextView = itemview.findViewById(R.id.orderPrice)
        val orderTitle : TextView = itemview.findViewById(R.id.productsTxt)


    }




}