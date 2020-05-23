package com.pos.pucpr.shoppingcrud.domain.usecases

import com.pos.pucpr.shoppingcrud.common.Either
import com.pos.pucpr.shoppingcrud.common.Failure
import com.pos.pucpr.shoppingcrud.common.Success
import com.pos.pucpr.shoppingcrud.domain.models.Shopping
import com.pos.pucpr.shoppingcrud.repositories.ShoppingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveShopping(private val repository: ShoppingRepository) {

    suspend fun invoke(shopping: Shopping): Either<Exception, Unit> {
        return try {
            withContext(Dispatchers.IO) {
                val response =
                    if (shopping.id.isNullOrEmpty()) {
                        repository.update(shopping = shopping)
                    } else {
                        repository.add(shopping = shopping)
                    }
                Success(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Failure(e)
        }
    }

}