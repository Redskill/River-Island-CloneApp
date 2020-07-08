package com.redskill.riverislandapp.domain

import com.google.gson.annotations.SerializedName

data class Product(

    @SerializedName("name") val name : String,
    @SerializedName("cost") val cost : Int,
    @SerializedName("wascost") val wascost : String,
    @SerializedName("costEUR") val costEUR : Int,
    @SerializedName("wascostEUR") val wascostEUR : String,
    @SerializedName("costWER") val costWER : Int,
    @SerializedName("wascostWER") val wascostWER : String,
    @SerializedName("costUSD") val costUSD : Int,
    @SerializedName("wascostUSD") val wascostUSD : String,
    @SerializedName("costAUD") val costAUD : Int,
    @SerializedName("wascostAUD") val wascostAUD : String,
    @SerializedName("costSEK") val costSEK : Int,
    @SerializedName("wascostSEK") val wascostSEK : String,
    @SerializedName("costWEK") val costWEK : Int,
    @SerializedName("wascostWEK") val wascostWEK : String,
    @SerializedName("prodid") val prodid : Int,
    @SerializedName("promotionImage") val promotionImage : String,
    @SerializedName("mediaIcon") val mediaIcon : String,
    @SerializedName("colour") val colour : String,
    @SerializedName("sizes") val sizes : String,
    @SerializedName("altImage") val altImage : String,
    @SerializedName("dateSort") val dateSort : Int,
    @SerializedName("allImages") val allImages : List<String>,
    @SerializedName("swatches") val swatches : List<Swatches>,
    @SerializedName("isNewArrival") val isNewArrival : Boolean,
    @SerializedName("isTrending") val isTrending : Boolean,
    @SerializedName("category") val category : String,
    @SerializedName("fit") val fit : String,
    @SerializedName("design") val design : String,
    @SerializedName("collections") val collections : String


)