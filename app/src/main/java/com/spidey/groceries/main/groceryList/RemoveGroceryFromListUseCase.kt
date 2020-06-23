package com.spidey.groceries.main.groceryList

import com.spidey.groceries.data.Grocery
import com.spidey.groceries.repositories.GroceryRepository
import javax.inject.Inject

class RemoveGroceryFromListUseCase @Inject constructor(
    private val groceryRepository: GroceryRepository
) {
    suspend operator fun invoke(id: Long) = groceryRepository.deleteGrocery(id)

    suspend operator fun invoke(grocery: Grocery) = groceryRepository.deleteGrocery(grocery)
}