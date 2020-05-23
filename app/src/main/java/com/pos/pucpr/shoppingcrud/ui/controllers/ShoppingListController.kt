package com.pos.pucpr.shoppingcrud.ui.controllers

import com.airbnb.epoxy.TypedEpoxyController
import com.pos.pucpr.shoppingcrud.ui.holders.shoppingViewHolder
import com.pos.pucpr.shoppingcrud.ui.viewdata.ShoppingViewData

class ShoppingListController : TypedEpoxyController<List<ShoppingViewData>>() {

    private var listener: OnClickListener? = null

    override fun buildModels(data: List<ShoppingViewData>?) {
        data?.forEach {
            shoppingViewHolder {
                id(it.id)
                name(it.name)
                amount(it.amount)
                clickListener { _, _, _, _ ->
                    listener?.onClickListener(it)
                }
                deleteListener { _, _, _, _ ->
                    listener?.onDeleteListener(it)
                }
            }
        }
    }

    fun setListener(listener: OnClickListener) {
        this.listener = listener
    }

    interface OnClickListener {
        fun onClickListener(shoppingItem: ShoppingViewData)
        fun onDeleteListener(shoppingItem: ShoppingViewData)
    }

}