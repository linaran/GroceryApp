package com.spidey.groceries.data

import androidx.room.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
abstract class GroceryDao {
    @Query("SELECT * FROM grocery_table ORDER BY created_at ASC")
    abstract fun getAll(): Flow<List<Grocery>>

    @Query("SELECT * FROM grocery_table WHERE id = :id")
    abstract fun get(id: Long): Flow<Grocery>

    @ExperimentalCoroutinesApi
    fun getDistinctUntilChanged(id: Long) = get(id).distinctUntilChanged()

    @Query("SELECT * FROM grocery_table WHERE name LIKE :name ORDER BY created_at")
    abstract fun get(name: String): Flow<List<Grocery>>

    @ExperimentalCoroutinesApi
    fun getDistinctUntilChanged(name: String) = get(name).distinctUntilChanged()

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun insert(grocery: Grocery)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun insertAll(vararg groceries: Grocery)

    suspend fun insertWithTimestamp(grocery: Grocery) {
        insert(grocery.apply { createdAt = System.currentTimeMillis() })
    }

    @Update abstract suspend fun update(grocery: Grocery)

    @Delete abstract suspend fun delete(grocery: Grocery)

    @Query("DELETE FROM grocery_table WHERE id = :id")
    abstract suspend fun delete(id: Long)

    @Query("DELETE FROM grocery_table")
    abstract suspend fun deleteAll()
}