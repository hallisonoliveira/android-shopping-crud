package com.pos.pucpr.shoppingcrud.domain.usecases

import com.pos.pucpr.shoppingcrud.common.Either
import com.pos.pucpr.shoppingcrud.common.Failure
import com.pos.pucpr.shoppingcrud.common.Success
import com.pos.pucpr.shoppingcrud.repositories.ShoppingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteShopping(private val repository: ShoppingRepository) {

    suspend fun invoke(id: String): Either<Exception, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                repository.deleteById(id = id)
                Success(Unit)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Failure(e)
        }
    }

}