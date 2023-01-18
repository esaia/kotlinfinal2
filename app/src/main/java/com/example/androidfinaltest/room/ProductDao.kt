package com.example.androidfinaltest.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ProductDao {

    @Query("SELECT * FROM product_table")
    fun getAll(): LiveData<List<StoreProducts>>

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun  insert(product: StoreProducts)

    @Insert
    fun insert(product: StoreProducts?): Long

    @Delete
    fun delete(product: StoreProducts)


    @Query("DELETE FROM product_table")
    fun deleteAll()

}


