package com.pos.pucpr.shoppingcrud.domain.usecases

import com.pos.pucpr.shoppingcrud.common.Either
import com.pos.pucpr.shoppingcrud.common.Failure
import com.pos.pucpr.shoppingcrud.common.Success
import com.pos.pucpr.shoppingcrud.domain.models.Shopping
import com.pos.pucpr.shoppingcrud.repositories.ShoppingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAllShopping(private val repository: ShoppingRepository) {

    suspend fun invoke(): Either<Exception, List<Shopping>> {
        return try {
            withContext(Dispatchers.IO) {
                val response = repository.getAll()
                Success(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Failure(e)
        }
    }

}