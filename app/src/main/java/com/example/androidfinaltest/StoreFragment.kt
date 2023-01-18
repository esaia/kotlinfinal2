package com.example.androidfinaltest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfinaltest.adapters.MyAdapterStore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class StoreFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null




    private lateinit var storeadapter : MyAdapterStore
    private lateinit var storerecyclerView: RecyclerView
    private lateinit var productsArrayList : ArrayList<Products>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store, container, false)
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StoreFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)



        val myTextView: TextView = view.findViewById(R.id.headingText) as TextView
        if(!storeProductsArray.isEmpty()){
            var totalPrice : Double = 0.0
            for(item in storeProductsArray){
                totalPrice += item.price
            }
            myTextView.text = "${storeProductsArray.size} Item(s) | Totaly: $totalPrice $"
        }else{
            myTextView.text = "Cart is empty"
        }


        storerecyclerView = view.findViewById(R.id.store_recycler_view)
        storerecyclerView.layoutManager = layoutManager
        storerecyclerView.setHasFixedSize(true)
        storeadapter = MyAdapterStore(storeProductsArray,myTextView)
        storerecyclerView.adapter = storeadapter


    }



}

