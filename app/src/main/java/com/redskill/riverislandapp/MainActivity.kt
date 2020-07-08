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

        // Delete TAG for Debugging
        val TAG = "myActivity"
        // Fetching ProductList from Server
        fetchJson("https://static-r2.ristack-3.nn4maws.net/v1/plp/en_gb/2506/products.json")
        val allProducts : ArrayList<ProductInfo> = ArrayList()
        for (item in productList) {
            allProducts.add(item)
        }
        Log.d(TAG,allProducts[11].name)
        // Getting All Categories available
        val categories = findAllProductCategories(allProducts)
        var i = 0
        var category = ""
        for (i in 0..categories.size-1) {
            category = categories[i]
            Log.i(TAG,category)
        }

    }
    private fun fetchJson(url: String)  {

        val TAG1 = "MyActivity"
        Log.d(TAG1,"Attempting to fetch Json")

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                val jsonObject = JSONObject(body)
                var jsonArray_productList:JSONArray= jsonObject.getJSONArray("Products")
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
            }

            override fun onFailure(call: Call, e: IOException) {
                print("Failed to execute request")
            }
        })
        Thread.sleep(2000)
    }
    private fun makeCurrentFragment(fragment:Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }
    private fun findAllProductCategories (list : ArrayList<ProductInfo>) : ArrayList<String> {
        var categoryList = ArrayList<String>()
        for (i in 0..list.size-1)  {
            if (!categoryList.contains(list[i].category)) {
                categoryList.add(list[i].category)
            }
        }
        return categoryList
    }
    private fun filterProductsByCategory (list : ArrayList<ProductInfo>, category: String) : ArrayList<ProductInfo> {
        var listOfItemsOfTheSameCategory = ArrayList<ProductInfo>()
        for (i in 0.. list.size-1) {
            if (list[i].category == category) {
                listOfItemsOfTheSameCategory.add(list[i])
            }
        }
        return listOfItemsOfTheSameCategory
    }
    private fun numberOfItemsInACategory (list: ArrayList<ProductInfo>, category: String ) : Int {
        var numOfItems = 0
        for (i in 0..list.size-1) {
            if (list[i].category == category) {
                numOfItems++
            }
        }
        return numOfItems
    }
}

