package com.redskill.riverislandapp

import com.redskill.riverislandapp.domain.Product
import com.redskill.riverislandapp.domain.ProductInfo
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
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun filterProductsByCategoryIsCorrect() {
        val o1 = ProductInfo("Black T-shirt",1111,25,"T-shirt")
        val o2 = ProductInfo("Blue Jeans", 2222,30,"Jeans")
        val o3 = ProductInfo("Pink Dress", 3333, 50,"Dress")
        val o4 = ProductInfo("White T-shirt",1112,25,"T-shirt")


    }
}
