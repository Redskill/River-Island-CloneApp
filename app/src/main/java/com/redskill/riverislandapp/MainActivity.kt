package com.redskill.riverislandapp

import android.content.Context
import android.graphics.Color
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.redskill.riverislandapp.domain.Product
import com.redskill.riverislandapp.navbar.fragments.FavoriteFragment
import com.redskill.riverislandapp.navbar.fragments.HomeFragment
import com.redskill.riverislandapp.navbar.fragments.ShoppingFragment
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title="River Island"

        val homeFragment = HomeFragment()
        val favoriteFragment = FavoriteFragment()
        val shoppingFragment = ShoppingFragment()

        makeCurrentFragment(homeFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_favorite -> makeCurrentFragment(favoriteFragment)
                R.id.ic_shopping -> makeCurrentFragment(shoppingFragment)
            }
            true
        }



//        search_bar.onActionViewExpanded()



//        search_bar.setBackgroundColor(Color.LTGRAY)



        val TAG = "MyActivity"
        Log.d(TAG,"Inizio")

        val yourCollection = mutableListOf<Product>()
        var list = fetchJson("https://static-r2.ristack-3.nn4maws.net/v1/plp/en_gb/2506/products.json")

        Log.d(TAG,list)
//        val gson = Gson()
//        var itemType = object : TypeToken<List<Product>>(){}.type
//        var productList : List<Product> = gson.fromJson(list,itemType)
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
    private fun makeCurrentFragment(fragment:Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
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
