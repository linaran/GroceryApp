package com.spidey.groceries.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

inline fun <reified T> createDiffCallback(
    crossinline areItemsTheSame: ((T, T) -> Boolean),
    crossinline areContentsTheSame: ((T, T) -> Boolean)
): DiffUtil.ItemCallback<T> =
        object : DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
                return areItemsTheSame.invoke(oldItem, newItem)
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
                return areContentsTheSame.invoke(oldItem, newItem)
            }
        }