package com.benfeder.shoppinglist.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.benfeder.shoppinglist.data.local.Grocery
import com.benfeder.shoppinglist.data.repository.ShoppingRepository
import com.benfeder.shoppinglist.databinding.FragmentShoppingBinding
import com.benfeder.shoppinglist.ui.viewmodel.ShoppingViewModel
import com.benfeder.shoppinglist.ui.viewmodel.ShoppingViewModelFactory

class ShoppingFragment : Fragment() {

    private var _binding: FragmentShoppingBinding? = null
    private val binding get() = _binding!!
    private lateinit var shoppingViewModel: ShoppingViewModel

    private val shoppingAdapter: ShoppingAdapter by lazy {
        ShoppingAdapter(this::onItemClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shoppingViewModel =
            ViewModelProvider(
                requireActivity(),
                ShoppingViewModelFactory(
                    ShoppingRepository(requireActivity().application)
                )
            ).get(
                ShoppingViewModel::class.java
            )

        shoppingViewModel.loadGroceries()
        with(binding) {
            shoppingRv.adapter = shoppingAdapter
            shoppingViewModel.groceries.observe(viewLifecycleOwner) {
                shoppingAdapter.loadData(it)
            }
        }
    }

    private fun onItemClick(grocery: Grocery) {
        shoppingViewModel.deleteGrocery(grocery)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}