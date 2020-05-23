package com.pos.pucpr.shoppingcrud.ui.holders

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.google.android.material.card.MaterialCardView
import com.pos.pucpr.shoppingcrud.R
import kotlinx.android.synthetic.main.list_item_shopping.view.*

@EpoxyModelClass(layout = R.layout.list_item_shopping)
abstract class ShoppingViewHolder : EpoxyModelWithHolder<ShoppingHolder>() {

    @EpoxyAttribute
    lateinit var name: String

    @EpoxyAttribute
    var amount: Int = 0

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var clickListener: View.OnClickListener? = null

    override fun bind(holder: ShoppingHolder) {
        holder.apply {
            textName.text = name
            textAmount.text = amount.toString()
            card.setOnClickListener(clickListener)
        }
    }

}

class ShoppingHolder : EpoxyHolder() {

    lateinit var content: ConstraintLayout
    lateinit var card: MaterialCardView
    lateinit var textName: TextView
    lateinit var textAmount: TextView

    override fun bindView(itemView: View) {
        content = itemView.content
        card = itemView.card
        textName = itemView.text_name
        textAmount = itemView.text_amount
    }
}