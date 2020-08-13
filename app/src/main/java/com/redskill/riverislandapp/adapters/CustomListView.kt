package com.redskill.riverislandapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.redskill.riverislandapp.R
import com.redskill.riverislandapp.domain.Product
import com.redskill.riverislandapp.domain.ProductInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.listview_layout.view.*

class CustomListView(context : Context,var resources : Int, var productList : List<Product>) : ArrayAdapter<Product>(context, resources,productList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater = LayoutInflater.from(context)
        val view:View = layoutInflater.inflate(resources,null)

        val imageView:ImageView = view.findViewById(R.id.imageView)
        val productNameTextView :TextView = view.findViewById(R.id.product_textView)
        val costTextView: TextView = view.findViewById(R.id.price_textView)

        var mItem : Product = productList[position]


        Picasso.get().load("http://riverisland.scene7.com/is/image/RiverIsland/${mItem.prodId}_main").into(imageView);
        productNameTextView.text = mItem.name
        costTextView.text = "£ " + mItem.cost.toString()

        return view
    }






}

