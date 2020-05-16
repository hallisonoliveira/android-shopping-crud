package com.pos.pucpr.shoppingcrud.remotes.dtos

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseDTO<T>(
    val data: T,
    val errors: List<String>?
)