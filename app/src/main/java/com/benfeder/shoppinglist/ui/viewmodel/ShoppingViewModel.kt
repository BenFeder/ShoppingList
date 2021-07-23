package com.benfeder.shoppinglist.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.benfeder.shoppinglist.data.local.Grocery
import com.benfeder.shoppinglist.data.repository.ShoppingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ShoppingViewModel(
    private val shoppingRepository: ShoppingRepository
) : ViewModel() {

    private val groceryList = listOf(
        Grocery(
            grocery = "Milk", price = 3.00
        ),
        Grocery(
            grocery = "Orange juice", price = 2.00
        ),
        Grocery(
            grocery = "Coffee", price = 5.00
        ),
        Grocery(
            grocery = "Bread", price = 2.00
        ),
        Grocery(
            grocery = "Bananas", price = 5.50
        ),
        Grocery(
            grocery = "Turkey bologna", price = 4.20
        ),
        Grocery(
            grocery = "Cheese", price = 6.00
        ),
        Grocery(
            grocery = "Soda", price = 1.00
        ),
        Grocery(
            grocery = "Water bottles", price = 5.50
        ),
        Grocery(
            grocery = "Vitamins", price = 4.00
        ),
        Grocery(
            grocery = "Ground beef", price = 7.00
        ),
        Grocery(
            grocery = "Chicken", price = 6.00
        ),
        Grocery(
            grocery = "Chocolate", price = 2.20
        ),
        Grocery(
            grocery = "Stevia", price = 3.00
        ),
        Grocery(
            grocery = "Batteries", price = 8.00
        ),
        Grocery(
            grocery = "Paper plates", price = 5.60
        ),
        Grocery(
            grocery = "Napkins", price = 4.00
        ),
        Grocery(
            grocery = "Cups", price = 2.00
        ),
        Grocery(
            grocery = "Hot pockets", price = 1.99
        ),
        Grocery(
            grocery = "Pop tarts", price = 5.00
        ),
        Grocery(
            grocery = "Peaches", price = 4.10
        ),
        Grocery(
            grocery = "Avocado", price = 6.00
        ),
        Grocery(
            grocery = "Granola", price = 4.00
        ),
        Grocery(
            grocery = "Gum", price = 4.00
        ),
        Grocery(
            grocery = "Cereal", price = 4.50
        )
    )

    // List to add chosen groceries to when clicked on grocery list
    private var chosenList = mutableListOf<Grocery>()

    // Adds price of all groceries together to get total
    val totalAmount = groceryList.sumByDouble { it.price }

    val groceries = shoppingRepository.getAllGroceries().asLiveData()

    fun loadGroceries() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                shoppingRepository.loadGroceries(groceryList)
                Log.d(TAG, "LoadGroceries called")
            } catch (ex: Exception) {
                Log.d(TAG, ex.toString())
            }
        }
    }

    fun addGrocery(grocery: Grocery) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                shoppingRepository.addGrocery(grocery)
                chosenList.add(grocery)
            } catch (ex: Exception) {
                Log.d(TAG, ex.toString())
            }
        }
    }

    companion object {
        private val TAG = ShoppingViewModel::class.java.name
    }
}