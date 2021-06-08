package com.userfaltakas.efoodrecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.userfaltakas.efoodrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryMenuAdapter: CategoryMenuAdapter
    private lateinit var menuAdapter: MenuAdapter

    private val burger = ArrayList<MenuItem>()
    private val sandwich = ArrayList<MenuItem>()
    private val pizza = ArrayList<MenuItem>()
    private val cafe = ArrayList<MenuItem>()
    private val milkshake = ArrayList<MenuItem>()
    private val cakes = ArrayList<MenuItem>()
    private val iceCream = ArrayList<MenuItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        
        setCategoryMenuAdapter()
        setProducts()
        setAdapter()
        menuAdapter.differ.submitList(burger)



        categoryMenuAdapter.setOnItemClickListener { firstMenu ->
            categoryMenuAdapter.differ.currentList.forEach {
                it.checked = false
            }
            categoryMenuAdapter.differ.currentList[firstMenu.id].checked =
                !categoryMenuAdapter.differ.currentList[firstMenu.id].checked
            categoryMenuAdapter.notifyDataSetChanged()

            binding.menuCategoryTextView.text = firstMenu.title
            when (firstMenu.title) {
                "Burger" -> menuAdapter.differ.submitList(burger)
                "Sandwich" -> menuAdapter.differ.submitList(sandwich)
                "Pizza" -> menuAdapter.differ.submitList(pizza)
                "Cafe" -> menuAdapter.differ.submitList(cafe)
                "Milkshake" -> menuAdapter.differ.submitList(milkshake)
                "Cakes" -> menuAdapter.differ.submitList(cakes)
                "IceCream" -> menuAdapter.differ.submitList(iceCream)
            }
        }

    }

    private fun setProducts() {
        for (i in 0..20) {
            burger.add(MenuItem(i, "Burger", true))
            sandwich.add(MenuItem(i, "Sandwich", true))
            pizza.add(MenuItem(i, "Pizza", true))
            cafe.add(MenuItem(i, "Cafe", true))
            milkshake.add(MenuItem(i, "Milkshake", true))
            cakes.add(MenuItem(i, "Cake", true))
            iceCream.add(MenuItem(i, "Ice-cream", true))
        }
    }

    private fun setAdapter() {
        menuAdapter = MenuAdapter()
        binding.menuRecyclerView.apply {
            adapter = menuAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setCategoryMenuAdapter() {
        categoryMenuAdapter = CategoryMenuAdapter()
        binding.categoryRecyclerView.apply {
            adapter = categoryMenuAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        val menuList = ArrayList<CategoryMenuItem>()
        menuList.add(CategoryMenuItem(0, "Burger", true))
        menuList.add(CategoryMenuItem(1, "Sandwich", false))
        menuList.add(CategoryMenuItem(2, "Pizza", false))
        menuList.add(CategoryMenuItem(3, "Cafe", false))
        menuList.add(CategoryMenuItem(4, "Milkshake", false))
        menuList.add(CategoryMenuItem(5, "Cakes", false))
        menuList.add(CategoryMenuItem(6, "Ice-cream", false))

        categoryMenuAdapter.differ.submitList(menuList)
    }
}