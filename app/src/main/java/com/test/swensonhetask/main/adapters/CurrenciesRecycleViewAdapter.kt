package com.test.swensonhetask.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.swensonhetask.R
import com.test.swensonhetask.main.models.CurrencyModel


class CurrenciesRecycleViewAdapter(
    private val context: Context,
    private val items: MutableList<CurrencyModel>,
    private val listener: OnItemClickListenerCurrency
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    interface OnItemClickListenerCurrency {
        fun onItemCurrencyClicked(item: CurrencyModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var viewHolder: RecyclerView.ViewHolder? = null
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_currency, parent, false)
        viewHolder = MainBlogviewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items!![position]
        val rfqViewHolder = holder as MainBlogviewHolder
        rfqViewHolder.bindData(item!!, listener)
        rfqViewHolder.txtCurrencyName.text = item.name

        rfqViewHolder.txtCurrencyAmount.text = item.amount
        holder.bindData(item, listener)


    }

    override fun getItemCount(): Int {
        // TODO: 4/10/2021 return it to be items.size
        return items!!.size
    }

    inner class MainBlogviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtCurrencyName: TextView = itemView.findViewById(R.id.txtCurrencyName)
        val txtCurrencyAmount: TextView = itemView.findViewById(R.id.txtCurrencyAmount)
        fun bindData(item: CurrencyModel, listener: OnItemClickListenerCurrency) {
            itemView.setOnClickListener {
                listener.onItemCurrencyClicked(item)
            }
        }
    }

}