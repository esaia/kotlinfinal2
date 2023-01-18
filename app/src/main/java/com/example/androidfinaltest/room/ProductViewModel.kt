package com.example.androidfinaltest.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductViewModel(application: Application):AndroidViewModel(application){

    val readAllData: LiveData<List<StoreProducts>>
    private val repository: ProductRepository

    init{
        val productDao = AppDatabase.getDatabase(application).productDao()
        repository = ProductRepository(productDao)
        readAllData = repository.getAll
    }

    fun addProduct(product: StoreProducts){
        GlobalScope.launch(Dispatchers.IO) {
            repository.addProduct(product)
        }

    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

}