package com.redskill.riverislandapp.utils

import com.redskill.riverislandapp.domain.ProductInfo
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

fun fetchJsonProductInfo(url: String): ArrayList<ProductInfo> {
    val resultList = ArrayList<ProductInfo>()
    val request = Request.Builder().url(url).build()
    val client = OkHttpClient()
    client.newCall(request).enqueue(object : Callback {

        override fun onResponse(call: Call, response: Response) {
            val body = response.body?.string()
            val jsonObject = JSONObject(body)
            var jsonArrayProductList: JSONArray = jsonObject.getJSONArray("Products")
            var size = jsonArrayProductList.length()
            for (i in 0 until size) {

                var jsonObjectDetail: JSONObject = jsonArrayProductList.getJSONObject(i)
                var productInfo = ProductInfo(
                    jsonObjectDetail.getString("name"),
                    jsonObjectDetail.getInt("prodid"),
                    jsonObjectDetail.getInt("cost"),
                    jsonObjectDetail.getString("category")
                )
                resultList.add(productInfo)
            }
        }
        override fun onFailure(call: Call, e: IOException) {
            print("Failed to execute request")
        }
    })
    Thread.sleep(1500)
    return resultList
}