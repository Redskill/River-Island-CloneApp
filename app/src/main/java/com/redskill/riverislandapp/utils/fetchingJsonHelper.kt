package com.redskill.riverislandapp.utils

import com.redskill.riverislandapp.domain.Product
import com.redskill.riverislandapp.domain.ProductInfo


interface fetchingJsonHelper {

    fun fetchJson(url : String)
    fun fetchJsonProduct(url: String, productList : ArrayList<Product>)
    fun fetchJsonProductInfo(url: String, productList : ArrayList<ProductInfo>)
}