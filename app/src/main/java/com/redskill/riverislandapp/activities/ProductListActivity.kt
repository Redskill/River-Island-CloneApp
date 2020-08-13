package com.redskill.riverislandapp.activities

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.gson.Gson
import com.redskill.riverislandapp.R
import com.redskill.riverislandapp.adapters.CustomListView
import com.redskill.riverislandapp.domain.Product
import com.redskill.riverislandapp.domain.ProductWrapper
import com.redskill.riverislandapp.utils.fetchJsonProductInfo
import com.redskill.riverislandapp.utils.filterProductsByCategory
import kotlinx.android.synthetic.main.activity_product_list.*
import java.net.HttpURLConnection
import java.net.URL

class ProductListActivity : AppCompatActivity() {

    var  chosenCategory = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        chosenCategory = intent.getStringExtra("CATEGORY_KEY")

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
        val productListView = findViewById<ListView>(R.id.product_listview)
        val productList : List<Product> = productWrapper.product
        val chosenCategoryList = productList.filter { it.category == "$chosenCategory" }.toList()


        product_listview.adapter = CustomListView(this,R.layout.listview_layout,productList)

        productListView.setOnItemClickListener { adapterView, view : View, position : Int, id:Long ->

            val intent = Intent(this, LargePictureActivity::class.java)
            intent.putExtra("SELECTED_ITEM_PRODID",productList[productListView.getItemIdAtPosition(position)
                .toInt()].prodId.toString())
            startActivity(intent)
        }

    }
}
