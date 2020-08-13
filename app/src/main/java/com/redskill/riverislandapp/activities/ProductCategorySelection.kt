package com.redskill.riverislandapp.activities

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.gson.Gson
import com.redskill.riverislandapp.R
import com.redskill.riverislandapp.adapters.CustomListView
import com.redskill.riverislandapp.domain.Product
import com.redskill.riverislandapp.domain.ProductWrapper
import com.redskill.riverislandapp.utils.fetchJsonProductInfo
import com.redskill.riverislandapp.utils.findAllProductCategories
import kotlinx.android.synthetic.main.activity_product_category_selection.*
import java.net.HttpURLConnection
import java.net.URL

class ProductCategorySelection : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_category_selection)

        AsyncTaskHandleJson().execute("https://static-r2.ristack-3.nn4maws.net/v1/plp/en_gb/2506/products.json")
    }

    inner class AsyncTaskHandleJson : AsyncTask<String, String, String>() {

        override fun doInBackground(vararg url: String?): String {

            val text : String
            val connection = URL(url[0]).openConnection() as HttpURLConnection
            try {
                connection.connect()
                text = connection.inputStream.use {it.reader().use { reader -> reader.readText() }}
            } finally {
                connection.disconnect()
            }
            return text
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            handleJson(result)
        }
    }

    fun handleJson(jsonString : String?)  {

        val gson = Gson()
        val productWrapper = gson.fromJson(jsonString, ProductWrapper::class.java)
        val productList : List<Product> = productWrapper.product
        val distinctCategories = productList.map { it.category }.toMutableList().distinct()
        val distinctCategories2 : ArrayList<String> = ArrayList()
        distinctCategories2.addAll(distinctCategories)
        distinctCategories2.add("All")
//        val jeansList = productList.filter { it.category == "Jeans" }.toList()
        val categoryListView = findViewById<ListView>(R.id.category_listview)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, distinctCategories2)

        categoryListView.adapter= arrayAdapter

        categoryListView.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, ProductListActivity::class.java)
            intent.putExtra("CATEGORY_KEY", adapterView.getItemAtPosition(i).toString())
            startActivity(intent)
        }
    }
}
