package com.example.androidfinaltest.room

import androidx.lifecycle.LiveData

class ProductRepository (private val productDao: ProductDao){
    val getAll: LiveData<List<StoreProducts>> = productDao.getAll()

    suspend fun addProduct(product: StoreProducts){
        productDao.insert(product)
    }

    suspend fun deleteAll(){
        productDao.deleteAll()
    }

}