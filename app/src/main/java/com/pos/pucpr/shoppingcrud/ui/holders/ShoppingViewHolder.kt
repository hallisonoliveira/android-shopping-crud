package com.pos.pucpr.shoppingcrud.ui.holders

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.pos.pucpr.shoppingcrud.R
import kotlinx.android.synthetic.main.list_item_shopping.view.*

@EpoxyModelClass(layout = R.layout.list_item_shopping)
abstract class ShoppingViewHolder : EpoxyModelWithHolder<ShoppingHolder>() {

    @EpoxyAttribute
    lateinit var name: String

    @EpoxyAttribute
    var amount: Int = 0

    override fun bind(holder: ShoppingHolder) {
        holder.apply {
            textName.text = name
            textAmount.text = amount.toString()
        }
    }

}

class ShoppingHolder : EpoxyHolder() {

    lateinit var textName: TextView
    lateinit var textAmount: TextView

    override fun bindView(itemView: View) {
        textName = itemView.text_name
        textAmount = itemView.text_amount
    }
}