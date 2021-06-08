package com.userfaltakas.efoodrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.userfaltakas.efoodrecyclerview.databinding.MenuItemBinding

class MenuAdapter() : RecyclerView.Adapter<MenuAdapter.MenuHolder>() {

    inner class MenuHolder(val binding: MenuItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    private val differCallback = object : DiffUtil.ItemCallback<MenuItem>() {
        override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        return MenuHolder(
            MenuItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        val binding = holder.binding
        val menu = differ.currentList[position]

        holder.itemView.apply {
            binding.textView.text = menu.title
            setOnClickListener {
                onItemClickListener?.let { it(menu) }
            }
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((MenuItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (MenuItem) -> Unit) {
        onItemClickListener = listener
    }
}
