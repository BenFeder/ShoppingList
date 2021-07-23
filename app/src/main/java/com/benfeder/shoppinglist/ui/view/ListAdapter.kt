package com.benfeder.shoppinglist.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.benfeder.shoppinglist.data.local.Grocery
import com.benfeder.shoppinglist.databinding.FragmentListBinding
import com.benfeder.shoppinglist.databinding.ListItemBinding
import com.benfeder.shoppinglist.ui.viewmodel.ShoppingViewModel

class ListAdapter (
    private val listener: (grocery: Grocery) -> Unit
) : RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>() {
    private var groceryList = listOf<Grocery>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { listener.invoke(groceryList[adapterPosition]) }
        }
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(groceryList[position])
    }

    override fun getItemCount() = groceryList.size

    fun loadData(groceries: List<Grocery>) {
        groceryList = groceries
        notifyDataSetChanged()
    }

    class ListViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(grocery: Grocery) = with(binding) {
            listPriceTv.text = "$" + grocery.price.toString()
            listGroceryTv.text = grocery.grocery
        }
    }

    class TotalViewHolder(private val binding: FragmentListBinding) :
        TextView.ViewHolder(binding.root) {
        fun bind(price: ShoppingViewModel) = with(binding) {
            val totalAmount = price.totalAmount
            totalTv.text = "$" + totalAmount.toString()
        }
    }
}
