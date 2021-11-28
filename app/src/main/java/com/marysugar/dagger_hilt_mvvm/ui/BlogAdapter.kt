package com.marysugar.dagger_hilt_mvvm.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.marysugar.dagger_hilt_mvvm.R
import com.marysugar.dagger_hilt_mvvm.databinding.ItemBlogBinding
import com.marysugar.dagger_hilt_mvvm.model.Blog
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class BlogAdapter (private val blogs: List<Blog>, listener: MainActivity) :
    RecyclerView.Adapter<BlogAdapter.ItemViewHolder>() {
    private val listener: PostsAdapterListener?

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding: ItemBlogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_blog, parent, false);
        return ItemViewHolder(binding)
    }

    inner class ItemViewHolder(itemBinding: ItemBlogBinding) : ViewHolder(itemBinding.root) {
        val binding = itemBinding
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.model = blogs[position]
        holder.binding.image.setOnClickListener {
            listener?.onPostClicked(post = blogs[position])
        }
    }

    // 画像をセット
    companion object {
        @JvmStatic
        @BindingAdapter("profileImage")
        fun loadImage(view: ImageView, imageUrl: String?) {
            if(imageUrl != null) {
                Glide.with(view.context)
                    .load(imageUrl).apply(RequestOptions().circleCrop())
                    .into(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return blogs.size
    }

    interface PostsAdapterListener {
        fun onPostClicked(post: Blog?)
    }

    init {
        this.listener = listener
    }
}

