package com.benfeder.shoppinglist.data.repository

import android.app.Application
import com.benfeder.shoppinglist.data.local.Grocery
import com.benfeder.shoppinglist.data.local.ShoppingDatabase
import kotlinx.coroutines.flow.Flow

class ShoppingRepository(private val application: Application) {

    private val shoppingDao by lazy {
        ShoppingDatabase.getDatabase(application).shoppingDao()
    }

    fun getAllGroceries(): Flow<List<Grocery>> = shoppingDao.getAllGroceries()

    suspend fun getGroceryByName(grocery: String): List<Grocery> = shoppingDao.getGroceryByName(grocery)

    suspend fun loadGroceries(groceries: List<Grocery>) = shoppingDao.loadGroceries(groceries)

    suspend fun deleteAllGroceries() = shoppingDao.deleteAllGroceries()

    suspend fun deleteGrocery(grocery: Grocery) = shoppingDao.deleteGrocery(grocery)

    suspend fun updateGrocery(grocery: Grocery) = shoppingDao.updateGrocery(grocery)
}
