package com.example.androidfinaltest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.room.Room
import com.example.androidfinaltest.room.AppDatabase
import com.example.androidfinaltest.room.ProductViewModel
import com.example.androidfinaltest.room.StoreProducts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class ChackoutFragment : Fragment() {


    private lateinit var checkoutBtn : Button
    private lateinit var name : EditText
    private lateinit var lastname : EditText
    private lateinit var address : EditText
    private lateinit var headingText : TextView

    private lateinit var productViewModel: ProductViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_chackout, container, false)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)


        checkoutBtn = view.findViewById(R.id.orderBtn)
        name = view.findViewById(R.id.namechk)
        lastname = view.findViewById(R.id.lastnamechk)
        address = view.findViewById(R.id.addresschk)
        headingText = view.findViewById(R.id.headingText)


        if(!storeProductsArray.isEmpty()){
            var totalPrice : Double = 0.0
            for(item in storeProductsArray){
                totalPrice += item.price
            }
            headingText.text= "${storeProductsArray.size} Item(s) | Totaly: $totalPrice $"
        }else{
            headingText.text = "Cart is empty"
        }


        view.findViewById<Button>(R.id.orderBtn).setOnClickListener {
            if(!storeProductsArray.isEmpty()){
                insertDataToDatabase()
                Toast.makeText(requireContext(), "Successfully Added To DB", Toast.LENGTH_SHORT).show()
                storeProductsArray.clear()
                name.setText("")
                lastname.setText("")
                address.setText("")

            }
        }

        return view
    }


    private fun insertDataToDatabase(){
        var productNames :String = ""
        var totalPrice : Double = 0.0

        for (item in storeProductsArray){
            productNames += item.title + " | "
            totalPrice += item.price
        }

        val product  = StoreProducts(null , name.text.toString() , lastname.text.toString() , address.text.toString() ,productNames, totalPrice)
        productViewModel.addProduct(product)
    }






    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }





}