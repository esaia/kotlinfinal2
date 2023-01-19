package com.example.androidfinaltest

import android.content.Intent
import android.content.IntentFilter
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
    private lateinit var receiver : AirplaneModeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        storeProductsArray = arrayListOf()


//        navigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        val botomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        setupWithNavController(botomNavigationView , navController)

//        Broadcast Receiver

        receiver = AirplaneModeReceiver()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiver, it)
        }

    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }





}
