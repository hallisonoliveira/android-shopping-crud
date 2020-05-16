package com.pos.pucpr.shoppingcrud.remotes.dtos


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShoppingDTO(
    val id: String?,
    val name: String,
    val amount: Int,
    val brand: String,
    val shelfLife: String
)