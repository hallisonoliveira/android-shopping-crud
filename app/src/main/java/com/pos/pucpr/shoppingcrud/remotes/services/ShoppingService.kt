package com.pos.pucpr.shoppingcrud.remotes.services

import com.pos.pucpr.shoppingcrud.remotes.dtos.ResponseDTO
import com.pos.pucpr.shoppingcrud.remotes.dtos.ShoppingDTO
import retrofit2.http.*

interface ShoppingService {

    @POST("shopping")
    suspend fun add(@Body shopping: ShoppingDTO): ResponseDTO<ShoppingDTO>

    @GET("shopping")
    suspend fun getAll(): ResponseDTO<List<ShoppingDTO>>

    @GET("shopping/{id}")
    suspend fun getById(@Path("id") id: String): ResponseDTO<ShoppingDTO>?

    @DELETE("shopping")
    suspend fun deleteById(id: String)

    @PUT("shopping")
    suspend fun update(@Body shopping: ShoppingDTO): ResponseDTO<ShoppingDTO>

}