package com.redskill.riverislandapp.domain

import com.google.gson.annotations.SerializedName

data class Swatches (

    @SerializedName("productId") val productId : Int?,
    @SerializedName("image") val image : String?

)