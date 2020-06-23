package com.spidey.groceries.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_table")
data class Grocery(
    @NonNull val name: String = "",
    val amount: Int
) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0L
    @ColumnInfo(name = "created_at") var createdAt: Long = 0L
}