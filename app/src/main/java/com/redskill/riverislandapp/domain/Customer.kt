package com.redskill.riverislandapp.domain

import com.redskill.riverislandapp.utils.customerHelper

data class Customer (val userName : String) : customerHelper {

    override fun findAllProductCategories(list: ArrayList<ProductInfo>): ArrayList<String> {
        var categoryList = ArrayList<String>()
        for (i in 0..list.size - 1) {
            if (!categoryList.contains(list[i].category)) {
                categoryList.add(list[i].category)
            }
        }
        return categoryList
    }

    override fun filterProductsByCategory(list: ArrayList<ProductInfo>, filteredList : ArrayList<ProductInfo>, category: String): ArrayList<ProductInfo> {
        for (i in 0.. list.size-1) {
            if (list[i].category == category) {
                filteredList.add(list[i])
            }
        }
        return filteredList
    }
    override fun numberOfItemsInACategory (list: ArrayList<ProductInfo>, category: String ) : Int {
        var numOfItems = 0
        for (i in 0..list.size-1) {
            if (list[i].category == category) {
                numOfItems++
            }
        }
        return numOfItems
    }
}