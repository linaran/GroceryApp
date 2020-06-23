package com.spidey.groceries.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.spidey.groceries.databinding.GroceryListFragmentBinding
import com.spidey.groceries.main.groceryList.GroceryListAdapter
import com.spidey.groceries.utils.RecyclerViewSwipeCallback
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GroceryListFragment : Fragment() {

    private lateinit var binding: GroceryListFragmentBinding
    private val viewModel by viewModels<GroceryListFragmentViewModel>()

    @Inject lateinit var groceryListAdapter: GroceryListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutInflater = LayoutInflater.from(context)
        binding = GroceryListFragmentBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel

        val groceryListView = binding.groceryList
        groceryListView.adapter = groceryListAdapter
        viewModel.groceryList.observe(viewLifecycleOwner, Observer(groceryListAdapter::submitList))

        val swipeCallback = createSwipeCallback()
        ItemTouchHelper(swipeCallback).attachToRecyclerView(groceryListView)

        groceryListAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                groceryListView.scrollToPosition(positionStart)
            }
        })

        return binding.root
    }

    private fun createSwipeCallback(): RecyclerViewSwipeCallback {
        val swipeDirections = ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        return RecyclerViewSwipeCallback(swipeDirections) { viewHolder: RecyclerView.ViewHolder, _ ->
            val position = viewHolder.adapterPosition
            val groceryItemId = groceryListAdapter.currentList[position].id
            viewModel.onDeleteGrocery(groceryItemId)
        }
    }
}