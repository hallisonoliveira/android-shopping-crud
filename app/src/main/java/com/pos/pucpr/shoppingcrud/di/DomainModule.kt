package com.pos.pucpr.shoppingcrud.di

import com.pos.pucpr.shoppingcrud.domain.usecases.GetShopping
import org.koin.core.module.Module
import org.koin.dsl.module

var createDomainModule: Module = module {
    single { GetShopping(repository = get()) }
}