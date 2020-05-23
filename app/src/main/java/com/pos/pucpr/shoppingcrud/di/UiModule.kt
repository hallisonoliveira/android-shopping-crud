package com.pos.pucpr.shoppingcrud.di

import com.pos.pucpr.shoppingcrud.ui.controllers.ShoppingListController
import com.pos.pucpr.shoppingcrud.ui.viewModels.ShoppingListViewModel
import com.pos.pucpr.shoppingcrud.ui.viewModels.ShoppingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

var createUiModule: Module = module {
    viewModel { ShoppingListViewModel(getAllShoppingUseCase = get()) }
    viewModel { ShoppingViewModel(getShopping = get(), saveShopping = get()) }
    factory { ShoppingListController() }
}