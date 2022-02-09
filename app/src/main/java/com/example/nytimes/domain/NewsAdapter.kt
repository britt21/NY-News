package com.example.nytimes.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.nytimes.data.remote.Result
import com.example.nytimes.databinding.ItemListBinding
import com.example.nytimes.presentation.NewsHomeFragmentDirections
import kotlinx.android.synthetic.main.item_list.view.*

class NewsAdapter : ListAdapter<Result, NewsAdapter.NyViewHolder>(DifferCallback) {

    companion object DifferCallback : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
           return oldItem.id === newItem.id
        }

    }
    class NyViewHolder(private val binding : ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result) {
            binding.result = result
            binding.newsImg.load(result.media[0].mediaMetadata[1].url)
        }


        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NyViewHolder {
        return NyViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context)))

    }

    override fun onBindViewHolder(holder: NyViewHolder, position: Int) {
        val curList = getItem(position)
        holder.bind(curList)
        holder.itemView.item_container.setOnClickListener {
        val action = NewsHomeFragmentDirections.actionNYHomeFragmentToNYDetailsFragment(curList)
            holder.itemView.findNavController().navigate(action)
        }

    }
}