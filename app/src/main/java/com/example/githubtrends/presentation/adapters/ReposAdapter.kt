package com.example.githubtrends.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtrends.databinding.RepoListItemBinding
import com.example.githubtrends.presentation.model.UiItem

class ReposAdapter : ListAdapter<UiItem, ReposViewHolder>(ItemDiffCallBack()) {
    private fun getList(): List<UiItem> = currentList
    fun setList(list: MutableList<UiItem>) {
        submitList(list)
    }


    private var onItemClickListener: ((UiItem) -> Unit)? = null
    fun setOnItemClickListener(listener: (UiItem) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        return ReposViewHolder(
            RepoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemId(position: Int): Long {
        return getList()[position].hashCode().toLong()
    }

    override fun getItemCount(): Int = getList().size


    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.bind(currentList[position], onItemClickListener)
    }
}

class ReposViewHolder(private val binding: RepoListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: UiItem, onItemClickListener: ((UiItem) -> Unit)?) {
        binding.item = item
        binding.executePendingBindings()

        binding.root.setOnClickListener {
            onItemClickListener?.invoke(item)
        }
    }
}


private class ItemDiffCallBack : DiffUtil.ItemCallback<UiItem>() {
    override fun areItemsTheSame(oldItem: UiItem, newItem: UiItem): Boolean =
        oldItem.hashCode() == newItem.hashCode()

    override fun areContentsTheSame(oldItem: UiItem, newItem: UiItem): Boolean =
        oldItem == newItem
}