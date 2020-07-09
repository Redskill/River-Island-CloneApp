package com.redskill.riverislandapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.redskill.riverislandapp.R
import com.redskill.riverislandapp.utils.fetchJsonProductInfo
import com.redskill.riverislandapp.utils.findAllProductCategories

class ProductCategorySelection : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_category_selection)

        val productList = fetchJsonProductInfo("https://static-r2.ristack-3.nn4maws.net/v1/plp/en_gb/2506/products.json")

        val categories : ArrayList<String> = findAllProductCategories(productList)

        val categoryListView = findViewById<ListView>(R.id.category_listview)

        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,categories)

        categoryListView.adapter= arrayAdapter
        
        categoryListView.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, ProductListActivity::class.java)
            intent.putExtra("CATEGORY_KEY",adapterView.getItemAtPosition(i).toString())
            startActivity(intent)

        }
    }
}
