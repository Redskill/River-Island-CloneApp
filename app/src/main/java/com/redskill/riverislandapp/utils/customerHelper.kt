package com.redskill.riverislandapp.utils

import com.redskill.riverislandapp.domain.Product
import com.redskill.riverislandapp.domain.ProductInfo

interface customerHelper {

    fun findAllProductCategories (list : ArrayList<ProductInfo>) : ArrayList<String>
    fun filterProductsByCategory (list : ArrayList<ProductInfo>, filteredList: ArrayList<ProductInfo>, category: String) : ArrayList<ProductInfo>
    fun numberOfItemsInACategory (list: ArrayList<ProductInfo>,  category: String ) : Int
}