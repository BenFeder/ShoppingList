package com.benfeder.shoppinglist.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.benfeder.shoppinglist.data.local.Grocery
import com.benfeder.shoppinglist.databinding.FragmentShoppingBinding
import com.benfeder.shoppinglist.databinding.ShoppingItemBinding
import com.benfeder.shoppinglist.ui.viewmodel.ShoppingViewModel

class ShoppingAdapter(
    private val listener: (grocery: Grocery) -> Unit
) : RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>() {
    private var groceryList = listOf<Grocery>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        return ShoppingViewHolder(
            ShoppingItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { listener.invoke(groceryList[adapterPosition]) }
        }
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        holder.bind(groceryList[position])
    }

    override fun getItemCount() = groceryList.size

    fun loadData(groceries : List<Grocery>) {
        groceryList = groceries
        notifyDataSetChanged()
    }

    class ShoppingViewHolder(private val binding: ShoppingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(grocery: Grocery) = with(binding) {
            priceTv.text = "$" + grocery.price.toString()
            groceryTv.text = grocery.grocery
        }
    }

//    class TotalViewHolder(private val binding: FragmentShoppingBinding) :
//        TextView.ViewHolder(binding.root) {
//        fun bind(price: ShoppingViewModel) = with(binding) {
//            val totalAmount = price.totalAmount
//            totalTv.text = "$" + totalAmount.toString()
//        }
//    }
}