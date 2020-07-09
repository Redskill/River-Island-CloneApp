package com.redskill.riverislandapp

import com.redskill.riverislandapp.domain.Customer
import com.redskill.riverislandapp.domain.Product
import com.redskill.riverislandapp.domain.ProductInfo
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.runners.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @Test
    fun test_filterProductsByCategory_returns_only_Tshirts() {
        val o1 = ProductInfo("Black T-shirt",1111,25,"T-shirt")
        val o2 = ProductInfo("Blue Jeans", 2222,30,"Jeans")
        val o3 = ProductInfo("Pink Dress", 3333, 50,"Dress")
        val o4 = ProductInfo("White T-shirt",1112,25,"T-shirt")
        val category= "T-shirt"
        val myList = ArrayList<ProductInfo>()
        myList.add(o1)
        myList.add(o2)
        myList.add(o3)
        myList.add(o4)
        val user = Customer("User")
        val myFilteredList = ArrayList<ProductInfo>()
        user.filterProductsByCategory(myList,myFilteredList,category)
        assertTrue(myFilteredList.contains(o1))
        assertTrue(myFilteredList.contains(o4))
        assertFalse(myFilteredList.contains(o3))
        assertFalse((myFilteredList.contains(o2)))
    }
    @Test
    fun test_findAllProductCategories_returns_allCategories() {
        val o1 = ProductInfo("Black T-shirt",1111,25,"T-shirt")
        val o2 = ProductInfo("Blue Jeans", 2222,30,"Jeans")
        val o3 = ProductInfo("Pink Dress", 3333, 50,"Dress")
        val user = Customer("User")
        val myList = ArrayList<ProductInfo>()
        myList.add(o1)
        myList.add(o2)
        myList.add(o3)
        val result = user.findAllProductCategories(myList)
        assertTrue(result.contains("T-shirt"))
        assertTrue(result.contains("Jeans"))
        assertTrue(result.contains("Dress"))
        assertFalse(result.contains("Underwear"))
    }

    @Test
    fun test_numberOfItemsInCategory_isCorrect() {
        val o1 = ProductInfo("Black T-shirt",1111,25,"T-shirt")
        val o2 = ProductInfo("Blue Jeans", 2222,30,"Jeans")
        val o3 = ProductInfo("Pink Dress", 3333, 50,"Dress")
        val o4 = ProductInfo("White T-shirt",1111,25,"T-shirt")
        val o5 = ProductInfo("Yellow Jeans", 2222,30,"Jeans")
        val o6 = ProductInfo("Floreal Dress", 3333, 50,"Dress")
        val myList = ArrayList<ProductInfo>()
        myList.add(o1)
        myList.add(o2)
        myList.add(o3)
        myList.add(o4)
        myList.add(o5)
        myList.add(o6)
        val user = Customer("User")
        assertEquals(2,user.numberOfItemsInACategory(myList,"Jeans"))
    }
}
