package com.example.androidfinaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.androidfinaltest.room.AppDatabase
import com.example.androidfinaltest.room.StoreProducts
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


const val BASE_URL = "https://fakestoreapi.com/"
lateinit var storeProductsArray : ArrayList<Products>

class MainActivity : AppCompatActivity(){

    private lateinit var navController: NavController
//    private lateinit var appDb : AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        storeProductsArray = arrayListOf()

//        appDb = AppDatabase.getDatabase(this)

//        navigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        val botomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        setupWithNavController(botomNavigationView , navController)


    }



//
//    private fun writeData(){
//        GlobalScope.launch(Dispatchers.IO){
//            appDb.productDao().insert(StoreProducts(null,"user", "user2", "address", "product", 233.2))
//        }
//
//        Toast.makeText(this@MainActivity, "Successfully written",  Toast.LENGTH_SHORT).show()
//    }




//    fun readData(a:Int){
//        var product : List<StoreProducts>
//
//
//        GlobalScope.launch {
//            product = appDb.productDao().getAll()
//            Log.d("product",  "${product}")
////            appDb.productDao().delete(StoreProducts(1,"hello1", 2.53, "description"))
//        }
//
//        Log.d("loggerr", "hello wolrd")
//
//
//
//    }


}
