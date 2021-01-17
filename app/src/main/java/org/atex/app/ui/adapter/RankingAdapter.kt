package org.atex.app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.atex.app.R
import org.atex.app.model.Ranking
import org.atex.app.utils.StartSnapHelper


class RankingAdapter() : RecyclerView.Adapter<RankingAdapter.ViewHolder>() {

    var items = mutableListOf<Ranking>()
    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_ranking, viewGroup, false)
        return ViewHolder(v)
    }

    fun clear(){
        this.items.clear()
    }
    fun insertItem(item: Ranking) {
        this.items.add(item)
        notifyDataSetChanged()
    }

    fun insertItem(item: Ranking, position: Int) {
        this.items.add(item)
        notifyItemInserted(position)
    }

    fun insertItems(items: MutableList<Ranking>) {
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
        fun onItemClickListener(view: View, item: Ranking)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun initRecyclerViews(recyclerView: RecyclerView) {
        recyclerView.setHasFixedSize(true)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, RecyclerView.VERTICAL, false)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = this
        StartSnapHelper().attachToRecyclerView(recyclerView)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val display_name: TextView = view.findViewById(R.id.display_name)
        private val image_top_1: ImageView = view.findViewById(R.id.image_top_1)
        private val text_ranking: TextView = view.findViewById(R.id.text_ranking)
        private val profile_image: ImageView = view.findViewById(R.id.profile_image)

        fun bind(item: Ranking) {
//            if (item.ranking == 1) {
//                image_top_1.visibility = View.VISIBLE
//            }
//            else{
//                image_top_1.visibility = View.GONE
//            }
//            text_ranking.text = item.ranking.toString()
////            loadImage(imagePoster, AppConstants.POSTER_PATH + item.posterPath)
//            display_name.text = item.displayName

//            GlideApp.with(profile_image.context).load(item.photoUrl)
//                .apply(RequestOptions.centerInsideTransform())
//                .into(profile_image)
        }
    }


}
