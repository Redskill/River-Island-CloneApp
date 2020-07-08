package com.redskill.riverislandapp

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.redskill.riverislandapp.domain.Product
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val TAG = "MyActivity"
        Log.d(TAG,"Inizio")

        val yourCollection = mutableListOf<Product>()
        var list = fetchJson("https://static-r2.ristack-3.nn4maws.net/v1/plp/en_gb/2506/products.json")

        Log.d(TAG,list)
        val gson = Gson()
        var itemType = object : TypeToken<List<Product>>(){}.type
        var productList : List<Product> = gson.fromJson(list,itemType)
        Log.d(TAG,"Fine")

    }
    fun fetchJson(url: String) : String {
        val oroductList = mutableListOf<Product>()
        var myString = "Empty"
        val TAG1 = "MyActivity"
        Log.d(TAG1,"Attempting to fetch Json")

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {

            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG1,"Attempting to fetch Json 2")
                val body = response.body?.string()
                myString = body.toString()
                Log.d(TAG1,"Attempting to fetch Json 3")

            }

            override fun onFailure(call: Call, e: IOException) {
                print("Failed to execute request")
            }
        })
        Log.d(TAG1,"Attempting to fetch Json 5")
        Thread.sleep(1000)

        return myString
    }

}

/*
fun fetchSeatById(seatIDList: List<Int>) : MutableList<String> {
    val seatList = mutableListOf<String>()
    var counter = 0
    for (e in seatIDList) {
        var id = seatIDList[counter].toString()
        val url  = "http://10.0.2.2:8080/seat/$id"
        counter++
        val request = Request.Builder().url(url).build()
        var myJsonSeat = "Seat"
        val response = client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                seatList.add(body.toString())

            }

            override fun onFailure(call: Call, e: IOException) {
                print("Error")
            }
        })


    }
    Thread.sleep(1000)
    return seatList
}
        val customer = Customer(customer_id,customer_name,customer_surname,customer_email)
        val seats = fetchSeatById(selected_seats).toString()

        // TO DO: Fix line below
        val itemTypeSeat = object : TypeToken<ArrayList<Seat>>() {}.type
        val userSeats  = gson.fromJson<ArrayList<Seat>>(seats,itemTypeSeat)*/
