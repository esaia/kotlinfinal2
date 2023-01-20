package com.example.androidfinaltest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.room.Room
import androidx.work.*
import com.example.androidfinaltest.room.AppDatabase
import com.example.androidfinaltest.room.ProductViewModel
import com.example.androidfinaltest.room.StoreProducts
import com.example.androidfinaltest.workmanager.MyWorker
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
    private var CHANNEL_ID = "channel_id"
    private var NOTIFICATION_ID = 1

    private lateinit var workmanagerBtn : Button

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

            if( inputchack(name,lastname,address)  &&  !storeProductsArray.isEmpty() ){
                insertDataToDatabase()
                Toast.makeText(requireContext(), "Successfully Added To DB", Toast.LENGTH_SHORT).show()
                storeProductsArray.clear()
                name.setText("")
                lastname.setText("")
                address.setText("")
                showNotification()


            }else{
                Toast.makeText(requireContext(), "Please fill fields!", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }


    private fun insertDataToDatabase(){
        var productNames :String = ""
        var totalPrice : Double = 0.0
        var count : Int = 1

        for (item in storeProductsArray){
            productNames +=  "$count. "+ item.title + "\n"
            totalPrice += item.price
            count++
        }
        count =1

        val product  = StoreProducts(null , name.text.toString() , lastname.text.toString() , address.text.toString() , productNames , totalPrice)
        productViewModel.addProduct(product)
    }

    private fun inputchack(name : EditText , lastname: EditText, address:EditText):Boolean{
        return !(TextUtils.isEmpty(name.text.toString()) && TextUtils.isEmpty(lastname.text.toString())  &&  TextUtils.isEmpty(address.text.toString()) )
    }


    private fun showNotification(){


        var builder = NotificationCompat.Builder(requireContext(), "Channel_id").apply {
            setSmallIcon(androidx.core.R.drawable.notification_bg)
            setContentTitle("Your Order Added")
            setContentText("Thank you for shopping with us. If you want 15% off your next order, leave a review on our website")
            priority = NotificationCompat.PRIORITY_DEFAULT
        }


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("Channel_id", "Test Channel", NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager: NotificationManager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel((channel))
        }

        with(NotificationManagerCompat.from(requireContext())){
            notify(1, builder.build())
        }


    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workmanagerBtn = view.findViewById(R.id.workmanagerBtn)

        workmanagerBtn.setOnClickListener {
            val constraints: Constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresCharging(true)
                .build()


            val myWorkRequest: WorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
        }

    }





}