package com.pos.pucpr.shoppingcrud.ui.mappers

import com.pos.pucpr.shoppingcrud.domain.models.Shopping
import com.pos.pucpr.shoppingcrud.ui.viewdata.ShoppingViewData

fun ShoppingViewData.toModel() = Shopping(
    id = id,
    name = name,
    amount = amount,
    brand = brand,
    shelfLife = shelfLife
)

fun Shopping.toViewData() = ShoppingViewData(
    id = id,
    name = name,
    amount = amount,
    brand = brand,
    shelfLife = shelfLife
)