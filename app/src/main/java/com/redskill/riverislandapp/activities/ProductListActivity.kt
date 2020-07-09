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
import com.redskill.riverislandapp.domain.ModelJsonData
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

        val modelJsonData= ModelJsonData()
        modelJsonData.fetchJsonProductInfo("https://static-r2.ristack-3.nn4maws.net/v1/plp/en_gb/2506/products.json",productList)

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
}
