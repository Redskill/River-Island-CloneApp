package com.redskill.riverislandapp.domain

import android.util.Log
import com.redskill.riverislandapp.utils.fetchingJsonHelper
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class ModelJsonData() : fetchingJsonHelper {

    override fun fetchJson(url : String) {

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
            }
            override fun onFailure(call: Call, e: IOException) {
                print("Failed to execute request")
            }
        })
        Thread.sleep(2000)
    }

    override fun fetchJsonProduct(url: String, productList: ArrayList<Product>) {
        TODO("Not yet implemented")
    }

    override fun fetchJsonProductInfo(url: String, productList: ArrayList<ProductInfo>) {

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