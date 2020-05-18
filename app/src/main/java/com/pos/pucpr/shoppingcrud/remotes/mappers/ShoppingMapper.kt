package com.pos.pucpr.shoppingcrud.remotes.mappers

import com.pos.pucpr.shoppingcrud.domain.models.Shopping
import com.pos.pucpr.shoppingcrud.remotes.dtos.ShoppingDTO

fun ShoppingDTO.toModel() = Shopping(
    id = id,
    name = name,
    amount = amount,
    brand = brand,
    shelfLife = shelfLife
)

fun Shopping.toDto() = ShoppingDTO(
    id = id,
    name = name,
    amount = amount,
    brand = brand,
    shelfLife = shelfLife
)