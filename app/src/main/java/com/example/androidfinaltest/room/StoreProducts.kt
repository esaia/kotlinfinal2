package com.example.androidfinaltest.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidfinaltest.Rating

@Entity(tableName = "product_table")
data class StoreProducts (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "lastname") val lastname: String?,
    @ColumnInfo(name = "address") val address: String?,

    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "totalPrice") val totalPrice: Double?,



    )








