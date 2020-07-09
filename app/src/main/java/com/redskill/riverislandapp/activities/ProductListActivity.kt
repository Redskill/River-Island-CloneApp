package com.redskill.riverislandapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.redskill.riverislandapp.R
import com.redskill.riverislandapp.adapters.CustomListView
import com.redskill.riverislandapp.domain.Customer
import com.redskill.riverislandapp.domain.ProductInfo
import kotlinx.android.synthetic.main.activity_product_list.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class ProductListActivity : AppCompatActivity() {

    private val productList = arrayListOf<ProductInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)




        val chosen_category = intent.getStringExtra("CATEGORY_KEY")

        fetchJson("https://static-r2.ristack-3.nn4maws.net/v1/plp/en_gb/2506/products.json")

        val Fabio = Customer("Fabio")
        val itemsFilteredByCategory : ArrayList<ProductInfo> = ArrayList()
        Fabio.filterProductsByCategory(productList,itemsFilteredByCategory,"$chosen_category")

        val productListView = findViewById<ListView>(R.id.product_listview)

        product_listview.adapter = CustomListView(this,R.layout.listview_layout,itemsFilteredByCategory)



        
        productListView.setOnItemClickListener { adapterView, view : View, position : Int, id:Long ->

            val intent = Intent(this, LargePictureActivity::class.java)
            intent.putExtra("SELECTED_ITEM_PRODID",itemsFilteredByCategory[productListView.getItemIdAtPosition(position)
                .toInt()].prodid.toString())
            startActivity(intent)
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
                var jsonArray_productList: JSONArray = jsonObject.getJSONArray("Products")
                var i = 0;
                var size = jsonArray_productList.length()
                for (i in 0.. size-1) {

                    var json_objectdetail: JSONObject = jsonArray_productList.getJSONObject(i)
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
}
