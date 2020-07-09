package com.redskill.riverislandapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.redskill.riverislandapp.R
import com.redskill.riverislandapp.adapters.CustomListView
import com.redskill.riverislandapp.utils.fetchJsonProductInfo
import com.redskill.riverislandapp.utils.filterProductsByCategory
import kotlinx.android.synthetic.main.activity_product_list.*

class ProductListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        val chosenCategory = intent.getStringExtra("CATEGORY_KEY")

        val productList = fetchJsonProductInfo("https://static-r2.ristack-3.nn4maws.net/v1/plp/en_gb/2506/products.json")

        val filterProductsByCategory = filterProductsByCategory(productList, "$chosenCategory")

        val productListView = findViewById<ListView>(R.id.product_listview)

        product_listview.adapter = CustomListView(this,R.layout.listview_layout,filterProductsByCategory)

        productListView.setOnItemClickListener { adapterView, view : View, position : Int, id:Long ->

            val intent = Intent(this, LargePictureActivity::class.java)
            intent.putExtra("SELECTED_ITEM_PRODID",filterProductsByCategory[productListView.getItemIdAtPosition(position)
                .toInt()].prodId.toString())
            startActivity(intent)
        }
    }
}
