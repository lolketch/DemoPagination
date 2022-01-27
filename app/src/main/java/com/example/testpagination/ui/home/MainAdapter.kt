package com.example.testpagination.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.testpagination.R
import com.example.testpagination.data.remote.model.MyCharacter

class MainAdapter : PagingDataAdapter<MyCharacter, RecyclerView.ViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as DefaultViewHolder
        viewHolder.nameCharacter.text = getItem(position)?.name
        Glide
            .with(viewHolder.itemView)
            .asBitmap()
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .load(getItem(position)?.image)
            .into(viewHolder.imageCharacter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DefaultViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    inner class DefaultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageCharacter: ImageView = itemView.findViewById(R.id.imageCharacter)
        val nameCharacter: TextView = itemView.findViewById(R.id.nameCharacter)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<MyCharacter>() {
            override fun areItemsTheSame(oldItem: MyCharacter, newItem: MyCharacter): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: MyCharacter, newItem: MyCharacter): Boolean =
                oldItem == newItem
        }
    }
}