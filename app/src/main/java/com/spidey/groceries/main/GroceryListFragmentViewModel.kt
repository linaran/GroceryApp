package com.spidey.groceries.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.spidey.groceries.data.Grocery
import com.spidey.groceries.main.groceryList.AddGroceryToListUseCase
import com.spidey.groceries.main.groceryList.GetGroceryListUseCase
import com.spidey.groceries.main.groceryList.GroceryListItem
import com.spidey.groceries.main.groceryList.RemoveGroceryFromListUseCase
import kotlinx.coroutines.launch

class GroceryListFragmentViewModel @ViewModelInject constructor(
    groceryListUseCase: GetGroceryListUseCase,
    private val addGroceryToListUseCase: AddGroceryToListUseCase,
    private val removeGroceryFromListUseCase: RemoveGroceryFromListUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val groceryName = MutableLiveData("")

    private val _groceryAmount = MutableLiveData(DEFAULT_AMOUNT)
    val groceryAmount: LiveData<Int> = _groceryAmount

    val groceryList: LiveData<List<GroceryListItem>> = groceryListUseCase().asLiveData()

    fun onIncreaseAmountClicked() {
        _groceryAmount.value = _groceryAmount.value?.plus(1) ?: DEFAULT_AMOUNT
    }

    fun onDecreaseAmountClicked() {
        if (_groceryAmount.value == 1) return
        _groceryAmount.value = _groceryAmount.value?.minus(1) ?: MIN_AMOUNT
    }

    fun onAddGrocery() {
        val newGroceryName = groceryName.value
        val newGroceryAmount = groceryAmount.value

        checkNotNull(newGroceryName)
        checkNotNull(newGroceryAmount)
        check(newGroceryAmount > 0) { "Grocery amount can't be 0 or negative" }

        // TODO: If grocery name is empty, prompt user to input a name!

        viewModelScope.launch {
            addGroceryToListUseCase(Grocery(newGroceryName, newGroceryAmount))
        }

        resetGroceryInputs()
    }

    fun onDeleteGrocery(id: Long) {
        viewModelScope.launch {
            removeGroceryFromListUseCase(id)
        }
    }

    private fun resetGroceryInputs() {
        groceryName.value = ""
        _groceryAmount.value = DEFAULT_AMOUNT
    }

    companion object {
        const val DEFAULT_AMOUNT = 1
        const val MIN_AMOUNT = 1
    }
}