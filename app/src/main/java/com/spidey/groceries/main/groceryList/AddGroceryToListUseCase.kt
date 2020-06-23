package com.spidey.groceries.main.groceryList

import com.spidey.groceries.data.Grocery
import com.spidey.groceries.repositories.GroceryRepository
import javax.inject.Inject

class AddGroceryToListUseCase @Inject constructor(
    private val groceryRepository: GroceryRepository
) {
    suspend operator fun invoke(grocery: Grocery) = groceryRepository.insertGrocery(grocery)
}