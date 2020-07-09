package com.redskill.riverislandapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.redskill.riverislandapp.R
import com.redskill.riverislandapp.domain.Customer
import com.redskill.riverislandapp.domain.ModelJsonData
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

        val modelJsonData= ModelJsonData()
        modelJsonData.fetchJsonProductInfo("https://static-r2.ristack-3.nn4maws.net/v1/plp/en_gb/2506/products.json",productList)


        val Fabio = Customer("Fabio")
        val categories : ArrayList<String> = Fabio.findAllProductCategories(productList)

        val category_list_view = findViewById<ListView>(R.id.category_listview)

        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,categories)

        category_list_view.adapter= arrayAdapter
        
        category_list_view.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, ProductListActivity::class.java)
            intent.putExtra("CATEGORY_KEY",adapterView.getItemAtPosition(i).toString())
            startActivity(intent)

        }
    }
}
