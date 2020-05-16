package com.pos.pucpr.shoppingcrud.remotes.services

import com.pos.pucpr.shoppingcrud.remotes.dtos.ResponseDTO
import com.pos.pucpr.shoppingcrud.remotes.dtos.ShoppingDTO
import retrofit2.http.*

interface ShoppingService {

    @POST("shopping")
    fun add(@Body shopping: ShoppingDTO): ResponseDTO<ShoppingDTO>

    @GET("shopping")
    fun getAll(): ResponseDTO<List<ShoppingDTO>>

    @GET("shopping")
    fun getById(@Path("id") id: String): ResponseDTO<ShoppingDTO>?

    @DELETE("shopping")
    fun deleteById(id: String)

    @PUT("shopping")
    fun update(@Body shopping: ShoppingDTO): ResponseDTO<ShoppingDTO>

}