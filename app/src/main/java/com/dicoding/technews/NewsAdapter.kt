package com.dicoding.technews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dicoding.technews.databinding.ItemNewsBinding

class NewsAdapter(private val listener: OnItemClickCallback) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(holder: NewsViewHolder, item: News) {
            holder.itemView.setOnClickListener { listener.onItemClicked(item) }
            binding.apply {
                sivThumbnail.load(item.thumbnail)
                tvTitle.text = item.title
                tvHeadline.text = item.headline
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
            (oldItem.title == newItem.title)

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
            (oldItem == newItem)
    }

    private val asyncDiffer = AsyncListDiffer(this, diffCallback)
    var newsItem: List<News>
        get() = asyncDiffer.currentList
        set(value) = asyncDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(holder, newsItem[position])
    }

    override fun getItemCount(): Int = newsItem.size

    interface OnItemClickCallback {
        fun onItemClicked(item: News)
    }
}