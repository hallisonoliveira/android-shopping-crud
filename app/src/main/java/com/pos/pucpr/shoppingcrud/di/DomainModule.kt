package com.pos.pucpr.shoppingcrud.di

import com.pos.pucpr.shoppingcrud.domain.usecases.DeleteShopping
import com.pos.pucpr.shoppingcrud.domain.usecases.GetAllShopping
import com.pos.pucpr.shoppingcrud.domain.usecases.GetShopping
import com.pos.pucpr.shoppingcrud.domain.usecases.SaveShopping
import org.koin.core.module.Module
import org.koin.dsl.module

var createDomainModule: Module = module {
    single { GetShopping(repository = get()) }
    single { GetAllShopping(repository = get()) }
    single { SaveShopping(repository = get()) }
    single { DeleteShopping(repository = get()) }
}