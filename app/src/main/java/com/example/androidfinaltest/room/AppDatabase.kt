package com.example.androidfinaltest.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.androidfinaltest.MainActivity
import java.util.concurrent.Executors


@Database(entities = [StoreProducts :: class], version = 2, exportSchema = false)
abstract class AppDatabase  : RoomDatabase() {
    abstract fun productDao() : ProductDao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

//            val migration = object : Migration(1, 2) {
//                override fun migrate(database: SupportSQLiteDatabase) {
//                    // write the migration logic here
//                }
//            }


            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database_1"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }


        }
    }

}