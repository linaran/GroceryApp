package com.spidey.groceries.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object GroceryDataModule {

    @Singleton
    @Provides
    fun provideGroceryDatabase(@ApplicationContext context: Context): GroceryDatabase =
        Room.databaseBuilder(
            context,
            GroceryDatabase::class.java,
            GroceryDatabase.name
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideGroceryDao(groceryDatabase: GroceryDatabase): GroceryDao =
        groceryDatabase.groceryDao()

}