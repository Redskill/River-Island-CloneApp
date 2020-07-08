package com.redskill.riverislandapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.redskill.riverislandapp.domain.ProductInfo
import com.redskill.riverislandapp.navbar.fragments.FavoriteFragment
import com.redskill.riverislandapp.navbar.fragments.HomeFragment
import com.redskill.riverislandapp.navbar.fragments.ShoppingFragment
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity()  {
    private val productList = arrayListOf<ProductInfo>()

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




        val TAG = "MyActivity"
        Log.d(TAG,"Inizio")


        var list = fetchJson("https://static-r2.ristack-3.nn4maws.net/v1/plp/en_gb/2506/products.json")
        val productList2 : ArrayList<ProductInfo> = ArrayList()
        for (item in productList) {
            productList2.add(item)
        }

        Log.d(TAG,productList2[1].name)


//        val gson = Gson()
//        var itemType = object : TypeToken<List<Product>>(){}.type
//        var productList : List<Product> = gson.fromJson(list,itemType)
        Log.d(TAG,"Fine")

    }
    fun fetchJson(url: String) : String {
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
                val jsonObject = JSONObject(body)

                Log.d(TAG1,"Attempting 4")
                var jsonArray_productList:JSONArray= jsonObject.getJSONArray("Products")

                Log.d(TAG1,"Attempting to fetch Json 5")

                var i = 0;
                var size = jsonArray_productList.length()
                for (i in 0.. size-1) {

                    var json_objectdetail:JSONObject= jsonArray_productList.getJSONObject(i)
                    var productInfo = ProductInfo(json_objectdetail.getString("name"),
                    json_objectdetail.getInt("prodid"),
                    json_objectdetail.getInt("cost"),
                    json_objectdetail.getString("category"))
                    productList.add(productInfo)


                }

//                val gson = Gson()
//                val list2 = object : TypeToken<List<Product>>(){}.type
//                val realList = gson.fromJson<Product>(jsonArray_productList.toString(),list2)
                Log.d(TAG1,"Attempting to fetch Json 6")


            }

            override fun onFailure(call: Call, e: IOException) {
                print("Failed to execute request")
            }
        })
        Log.d(TAG1,"Attempting to fetch Json 5")
        Thread.sleep(3000)

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
