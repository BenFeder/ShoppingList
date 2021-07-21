package com.benfeder.shoppinglist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.benfeder.shoppinglist.data.repository.ShoppingRepository
import java.lang.IllegalArgumentException

class ShoppingViewModelFactory(
    private val shoppingRepository: ShoppingRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ShoppingViewModel::class.java)) {
            ShoppingViewModel(shoppingRepository) as T
        } else {
            throw IllegalArgumentException("Could not create instance of ShoppingViewModel")
        }
    }

}