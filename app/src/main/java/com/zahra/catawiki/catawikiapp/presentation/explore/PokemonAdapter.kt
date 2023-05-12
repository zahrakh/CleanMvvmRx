package com.zahra.catawiki.catawikiapp.presentation.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.zahra.catawiki.R
import com.zahra.catawiki.catawikiapp.domain.model.Pokemon
import com.zahra.catawiki.databinding.ItemErrorBinding
import com.zahra.catawiki.databinding.ItemLoadingBinding
import com.zahra.catawiki.databinding.ItemPokemonBinding


class PokemonAdapter(
    private var onLoadMoreListener: () -> Unit,
    private var showEmptyState: () -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_DATA = 1
        private const val VIEW_TYPE_ERROR = 2
    }

    private val items = mutableListOf<Pokemon>()

    var isLoading = false
        set(value) {
            field = value
            notifyItemChanged(itemCount)
        }

    var isError = false
        set(value) {
            field = value
            notifyItemChanged(itemCount)
        }

    var noMoreData = false

    fun appendData(list: List<Pokemon>, totalPages: Int) {
        if (totalPages == 0) return //todo check condition
        val currentItemsSize = items.size
        if (list.isNotEmpty()) {
            items.addAll(list)
            val newItemsSize = items.size
            notifyItemRangeChanged(currentItemsSize, newItemsSize - currentItemsSize)
        }
        if (itemCount >= totalPages) {
            noMoreData = true
        }
        if (items.isEmpty()) {
            showEmptyState()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            VIEW_TYPE_LOADING -> {
                LoadingViewHolder(
                    ItemLoadingBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_ERROR -> {
                ErrorViewHolder(
                    ItemErrorBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                PokemonViewHolder(
                    ItemPokemonBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PokemonViewHolder) {
            holder.bind(position)
        }
        if (!noMoreData && !isError && !isLoading && position == itemCount - 1) {
            onLoadMoreListener.invoke()
        }
    }


    override fun getItemCount(): Int {
        var dataCount = items.size
        if (isLoading) {
            dataCount++
        }
        if (isError) {//isError and isLoading never be true at the same time
            dataCount++
        }
        return dataCount
    }

    override fun getItemViewType(position: Int): Int {
        val dataCount = items.size

        return if (isLoading) {
            if (position < dataCount) {
                VIEW_TYPE_DATA
            } else {
                VIEW_TYPE_LOADING
            }
        } else if (isError) {
            if (position < dataCount) {
                VIEW_TYPE_DATA
            } else {
                VIEW_TYPE_ERROR
            }
        } else {
            VIEW_TYPE_DATA
        }
    }

    inner class PokemonViewHolder(private val view: ItemPokemonBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(position: Int) {
            with(items[position]) {
                view.tvPokemonName.text = this.name
                view.ivPoster.load(this.imageUrl) {
                    transformations(CircleCropTransformation())
                    error(R.drawable.placeholder)
                    placeholder(R.drawable.placeholder)
                }
            }
        }
    }

    class LoadingViewHolder(view: ItemLoadingBinding) : RecyclerView.ViewHolder(view.root)

    inner class ErrorViewHolder(view: ItemErrorBinding) : RecyclerView.ViewHolder(view.root) {
        init {
            view.btnError.setOnClickListener {
                onLoadMoreListener()
            }
        }
    }

}