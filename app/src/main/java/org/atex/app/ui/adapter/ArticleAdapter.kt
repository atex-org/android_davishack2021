package org.atex.app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.atex.app.R
import org.atex.app.model.Article
import org.atex.app.ui.home.HomeFragment
import org.atex.app.utils.CustomInfoWindow
import org.atex.app.utils.StartSnapHelper


class ArticleAdapter() : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    var items = mutableListOf<Article>()
    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_article, viewGroup, false)
        return ViewHolder(v)
    }

    fun insertItem(item: Article) {
        this.items.add(item)
        notifyDataSetChanged()
    }

    fun insertItem(item: Article, position: Int) {
        this.items.add(item)
        notifyItemInserted(position)
    }

    fun insertItems(items: MutableList<Article>) {
        this.items = items
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { v ->
            listener?.onItemClickListener(v, items.get(holder.layoutPosition))
        }
    }

    /**
     * The interface that receives onItemClick messages.
     */
    interface OnItemClickListener {
        fun onItemClickListener(view: View, item: Article)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: HomeFragment) {
        this.listener = listener
    }


    fun initRecyclerViews(recyclerView: RecyclerView) {
        recyclerView.setHasFixedSize(true)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = this
//        StartSnapHelper().attachToRecyclerView(recyclerView)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imagePoster: ImageView = view.findViewById(R.id.image_poster)
        private val text_title: TextView = view.findViewById(R.id.text_title)
        fun bind(item: Article) {
//            loadImage(imagePoster, AppConstants.POSTER_PATH + item.posterPath)
            text_title.text = item.title
            val url = item.thumbnailUrl
            if (!url.isNullOrEmpty()) {
                Picasso.get().load(url).error(R.drawable.maps_sv_error_icon)
                    .placeholder(R.drawable.ic_app_icon)
                    .into(imagePoster)
            }
//            imagePoster.setImageResource(getResource(item._id))
        }
    }


}
