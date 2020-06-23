package com.spidey.groceries.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Grocery::class], version = 1, exportSchema = false)
abstract class GroceryDatabase : RoomDatabase() {
    abstract fun groceryDao(): GroceryDao

    companion object {
        const val name = "grocery_database"
    }
}