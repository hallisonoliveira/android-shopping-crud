package com.pos.pucpr.shoppingcrud.di

import com.pos.pucpr.shoppingcrud.remotes.repositories.ShoppingRepositoryImpl
import com.pos.pucpr.shoppingcrud.repositories.ShoppingRepository
import org.koin.core.module.Module
import org.koin.dsl.module

var createRepositoryModule: Module = module {
    factory<ShoppingRepository> { ShoppingRepositoryImpl(get()) }
}