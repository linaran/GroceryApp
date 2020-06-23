package com.spidey.groceries.main.groceryList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.spidey.groceries.databinding.GroceryItemLayoutBinding
import javax.inject.Inject

class GroceryListAdapter @Inject constructor() :
    ListAdapter<GroceryListItem, GroceryListAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: GroceryItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(groceryListItem: GroceryListItem) {
            binding.data = groceryListItem
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = GroceryItemLayoutBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val groceryItem = getItem(position)
        holder.bind(groceryItem)
    }

    companion object {
        val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<GroceryListItem>() {
                override fun areItemsTheSame(
                    oldItem: GroceryListItem,
                    newItem: GroceryListItem
                ): Boolean = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: GroceryListItem,
                    newItem: GroceryListItem
                ): Boolean = oldItem.amount == newItem.amount && oldItem.name == newItem.name
            }
    }
}