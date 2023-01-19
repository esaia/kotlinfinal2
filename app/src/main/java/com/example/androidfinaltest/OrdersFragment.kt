package com.example.androidfinaltest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfinaltest.adapters.MyAdapter
import com.example.androidfinaltest.adapters.MyOrdersAdapter
import com.example.androidfinaltest.room.ProductViewModel
import com.example.androidfinaltest.room.StoreProducts


class OrdersFragment : Fragment() {
    private lateinit var adapter : MyOrdersAdapter
    private lateinit var recyclerView: RecyclerView
//    private lateinit var productsArrayList : ArrayList<Products>


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




        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        val deleteBtn = view.findViewById<Button>(R.id.deleteOrdersBtn)

        deleteBtn.setOnClickListener {
            productViewModel.deleteAll()
        }




        productViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            Log.d("haha",  "${it}")
            adapter = MyOrdersAdapter(it)
            recyclerView.adapter = adapter

        })



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.ordersRecycler)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)


    }


}