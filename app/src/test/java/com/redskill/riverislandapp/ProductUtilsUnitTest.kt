package com.redskill.riverislandapp

import com.redskill.riverislandapp.domain.ProductInfo
import com.redskill.riverislandapp.utils.filterProductsByCategory
import com.redskill.riverislandapp.utils.findAllProductCategories
import com.redskill.riverislandapp.utils.numberOfItemsInACategory
import org.junit.Test
import org.junit.Assert.*

class ProductUtilsUnitTest {

    @Test
    fun test_filterProductsByCategory_returns_only_Tshirts() {
        val o1 = ProductInfo("Black T-shirt",1111,25,"T-shirt")
        val o2 = ProductInfo("Blue Jeans", 2222,30,"Jeans")
        val o3 = ProductInfo("Pink Dress", 3333, 50,"Dress")
        val o4 = ProductInfo("White T-shirt",1112,25,"T-shirt")
        val category= "T-shirt"
        val list = ArrayList<ProductInfo>()
        list.add(o1)
        list.add(o2)
        list.add(o3)
        list.add(o4)
        val filteredList = filterProductsByCategory(list, category)
        assertTrue(filteredList.contains(o1))
        assertTrue(filteredList.contains(o4))
        assertFalse(filteredList.contains(o3))
        assertFalse((filteredList.contains(o2)))
        assertEquals(2, filteredList.size)
    }

    @Test
    fun test_findAllProductCategories_returns_allCategories() {
        val o1 = ProductInfo("Black T-shirt",1111,25,"T-shirt")
        val o2 = ProductInfo("Blue Jeans", 2222,30,"Jeans")
        val o3 = ProductInfo("Pink Dress", 3333, 50,"Dress")
        val list = ArrayList<ProductInfo>()
        list.add(o1)
        list.add(o2)
        list.add(o3)
        val result = findAllProductCategories(list)
        assertTrue(result.contains("T-shirt"))
        assertTrue(result.contains("Jeans"))
        assertTrue(result.contains("Dress"))
        assertFalse(result.contains("Underwear"))
        assertEquals(3, result.size)
    }

    @Test
    fun test_numberOfItemsInCategory_isCorrect() {
        val o1 = ProductInfo("Black T-shirt",1111,25,"T-shirt")
        val o2 = ProductInfo("Blue Jeans", 2222,30,"Jeans")
        val o3 = ProductInfo("Pink Dress", 3333, 50,"Dress")
        val o4 = ProductInfo("White T-shirt",1111,25,"T-shirt")
        val o5 = ProductInfo("Yellow Jeans", 2222,30,"Jeans")
        val o6 = ProductInfo("Floreal Dress", 3333, 50,"Dress")
        val list = ArrayList<ProductInfo>()
        list.add(o1)
        list.add(o2)
        list.add(o3)
        list.add(o4)
        list.add(o5)
        list.add(o6)

        assertEquals(2, numberOfItemsInACategory(list,"Jeans"))
    }
}
