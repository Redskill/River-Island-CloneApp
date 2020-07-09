package com.redskill.riverislandapp.domain

class Customer {

    fun findAllProductCategories(list: ArrayList<ProductInfo>): ArrayList<String> {
        var categoryList = ArrayList<String>()
        for (i in 0 until list.size) {
            if (!categoryList.contains(list[i].category)) {
                categoryList.add(list[i].category)
            }
        }
        return categoryList
    }

    fun filterProductsByCategory(list: ArrayList<ProductInfo>, category: String): ArrayList<ProductInfo> {
        val filteredList = ArrayList<ProductInfo>()
        for (i in 0 until list.size) {
            if (list[i].category == category) {
                filteredList.add(list[i])
            }
        }
        return filteredList
    }

    fun numberOfItemsInACategory (list: ArrayList<ProductInfo>, category: String ) : Int {
        var numOfItems = 0
        for (i in 0 until list.size) {
            if (list[i].category == category) {
                numOfItems++
            }
        }
        return numOfItems
    }
}