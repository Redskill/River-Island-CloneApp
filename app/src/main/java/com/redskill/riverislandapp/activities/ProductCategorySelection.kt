package com.redskill.riverislandapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.redskill.riverislandapp.R
import com.redskill.riverislandapp.domain.Customer
import com.redskill.riverislandapp.domain.ProductInfo
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class ProductCategorySelection : AppCompatActivity() {

    private val productList = arrayListOf<ProductInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_category_selection)

        val Fabio = Customer("Fabio")
        val categories : ArrayList<String> = Fabio.findAllProductCategories(productList)

        
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
