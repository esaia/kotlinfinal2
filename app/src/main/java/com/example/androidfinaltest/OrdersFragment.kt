package com.example.androidfinaltest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidfinaltest.room.ProductViewModel
import com.example.androidfinaltest.room.StoreProducts


class OrdersFragment : Fragment() {

    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view = inflater.inflate(R.layout.fragment_orders, container, false)

        var product : LiveData<List<StoreProducts>>
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
//        productViewModel.deleteAll()
        productViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            Log.d("haha",  "${it}")
        })





        return view
    }


}