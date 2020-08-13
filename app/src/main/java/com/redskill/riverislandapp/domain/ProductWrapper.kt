package com.redskill.riverislandapp.domain
import com.google.gson.annotations.SerializedName

class ProductWrapper(

    @SerializedName("Products")
    val product : List<Product>)