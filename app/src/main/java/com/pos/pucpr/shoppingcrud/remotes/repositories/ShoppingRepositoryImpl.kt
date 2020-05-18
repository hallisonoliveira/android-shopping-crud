package com.pos.pucpr.shoppingcrud.remotes.repositories

import com.pos.pucpr.shoppingcrud.domain.models.Shopping
import com.pos.pucpr.shoppingcrud.remotes.mappers.toDto
import com.pos.pucpr.shoppingcrud.remotes.mappers.toModel
import com.pos.pucpr.shoppingcrud.remotes.services.ShoppingService
import com.pos.pucpr.shoppingcrud.repositories.ShoppingRepository

class ShoppingRepositoryImpl(private val service: ShoppingService) : ShoppingRepository {

    override suspend fun add(shopping: Shopping) {
        service.add(shopping = shopping.toDto())
    }

    override suspend fun getAll(): List<Shopping> {
        return service.getAll().data.map { it.toModel() }
    }

    override suspend fun getById(id: String): Shopping? {
        return service.getById(id = id)?.data?.toModel()
    }

    override suspend fun deleteById(id: String) {
        service.deleteById(id = id)
    }

    override suspend fun update(shopping: Shopping) {
        service.update(shopping = shopping.toDto())
    }
}