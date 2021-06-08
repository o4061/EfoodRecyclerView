package com.userfaltakas.efoodrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.userfaltakas.efoodrecyclerview.databinding.CategoryMenuItemBinding

class CategoryMenuAdapter : RecyclerView.Adapter<CategoryMenuAdapter.FirstMenuHolder>() {

    inner class FirstMenuHolder(val binding: CategoryMenuItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<CategoryMenuItem>() {
        override fun areItemsTheSame(
            oldItem: CategoryMenuItem,
            newItem: CategoryMenuItem
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: CategoryMenuItem,
            newItem: CategoryMenuItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstMenuHolder {
        return FirstMenuHolder(
            CategoryMenuItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FirstMenuHolder, position: Int) {
        val binding = holder.binding
        val menu = differ.currentList[position]

        holder.itemView.apply {
            binding.textView.text = menu.title
            if (menu.checked) {
                binding.textView.setBackgroundResource(R.drawable.rounded_corner_textview)
                binding.textView.setTextColor(ContextCompat.getColor(context, R.color.white))
            } else {
                binding.textView.setBackgroundResource(R.color.transparent)
                binding.textView.setTextColor(ContextCompat.getColor(context, R.color.black))
            }
            setOnClickListener {
                onItemClickListener?.let { it(menu) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((CategoryMenuItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (CategoryMenuItem) -> Unit) {
        onItemClickListener = listener
    }
}