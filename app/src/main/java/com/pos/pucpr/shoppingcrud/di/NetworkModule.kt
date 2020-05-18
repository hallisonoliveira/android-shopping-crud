package com.pos.pucpr.shoppingcrud.di

import android.content.Context
import com.pos.pucpr.shoppingcrud.network.RetrofitFactory
import com.pos.pucpr.shoppingcrud.remotes.services.ShoppingService
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

const val BASE_URL = "http://192.168.0.12:8080/api/"

val createNetworkModule: Module = module {
    single {
        createRetrofit(
            baseUrl = BASE_URL,
            context = get()
        ).create(ShoppingService::class.java)
    }
}

private fun createRetrofit(
    baseUrl: String,
    context: Context
): Retrofit {
    return RetrofitFactory.createRetrofit(baseUrl, context)
}