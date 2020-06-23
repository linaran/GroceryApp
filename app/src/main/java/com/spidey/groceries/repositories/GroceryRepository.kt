package com.spidey.groceries.repositories

import com.spidey.groceries.data.Grocery
import com.spidey.groceries.data.GroceryDao
import javax.inject.Inject

class GroceryRepository @Inject constructor(
    private val groceryDao: GroceryDao
) {
    fun getGroceryList() = groceryDao.getAll()

    suspend fun insertGrocery(grocery: Grocery) = groceryDao.insertWithTimestamp(grocery)

    suspend fun deleteGrocery(grocery: Grocery) = groceryDao.delete(grocery)

    suspend fun deleteGrocery(id: Long) = groceryDao.delete(id)
}