package com.spidey.groceries.main.groceryList

import com.spidey.groceries.repositories.GroceryRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetGroceryListUseCase @Inject constructor(
    private val groceryRepository: GroceryRepository
) {
    operator fun invoke() = groceryRepository.getGroceryList().map { list ->
        list.map { GroceryListItem(it.id, it.name, it.amount) }
    }
}