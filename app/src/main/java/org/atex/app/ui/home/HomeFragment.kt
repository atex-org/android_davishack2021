package org.atex.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import org.atex.app.R
import org.atex.app.model.Article
import org.atex.app.model.DailyMission
import org.atex.app.ui.adapter.ArticleAdapter
import org.atex.app.ui.adapter.DailyMissionAdapter
import org.atex.app.ui.adapter.RankingAdapter


class HomeFragment : Fragment(), DailyMissionAdapter.OnItemClickListener,
    ArticleAdapter.OnItemClickListener {

    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var dailyMissionAdapter: DailyMissionAdapter
    private lateinit var rankingAdapter: RankingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerArticle = root.findViewById<RecyclerView>(R.id.recycler_article)
        val recyclerDailyMission = root.findViewById<RecyclerView>(R.id.recycler_daily_mission)

        val recyclerRanking = root.findViewById<RecyclerView>(R.id.recycler_ranking)


        articleAdapter = ArticleAdapter()
        articleAdapter.initRecyclerViews(recyclerArticle)
        articleAdapter.setOnItemClickListener(this)

        dailyMissionAdapter = DailyMissionAdapter()
        dailyMissionAdapter.initRecyclerViews(recyclerDailyMission)
        dailyMissionAdapter.setOnItemClickListener(this)

        rankingAdapter = RankingAdapter()
        rankingAdapter.initRecyclerViews(recyclerRanking)



        val items = mutableListOf<DailyMission>()
        var item = item(
            "1",
            "Collect 5 bottles",
            ""
        )
        items.add(item)
        item = item(
            "2",
            "Where to recycle light bulbs",
            "How you dispose of a light bulb depends on which kind of light bulb you have..."
        )
        items.add(item)
        item = item(
            "3",
            "How to reduce waste?",
            "Learn to repair rather than discard. Cancel unnecessary mail. Stop using disposable plates."
        )
        items.add(item)
        item = item(
            "4",
            "What is Composting?",
            "Compost is organic matter that has been decomposed in a process called composting."
        )
        items.add(item)
        item = item(
            "5",
            "Where to recycle electronics?",
            "If your e-waste is no longer functional, visit a drop-off location..."
        )
        items.add(item)


        dailyMissionAdapter.insertItems(items)
        articleAdapter.insertItems(createArticles())
        return root
    }

    private fun createArticles(): ArrayList<Article> {
        val articles: ArrayList<Article> = ArrayList()
        val article1 = Article()
        article1._id = 1
        article1.title = "Recycle clean bottles, cans, paper and cardboard"
        article1.thumbnailUrl =
            "https://github.com/atex-org/davishack2021/raw/main/public/images/article/article-what-to-recycle.png"

        val article2 = Article()
        article2._id = 2
        article2.title = "Keep food and liquid out of your recycling"
        article2.thumbnailUrl =
            "https://github.com/atex-org/davishack2021/raw/main/public/images/article/article-no-food-recycling-illustration.png"

        val article3 = Article()
        article3._id = 3
        article3.title = "No loose plastic bags and no bagged recyclables"
        article3.thumbnailUrl =
            "https://github.com/atex-org/davishack2021/raw/main/public/images/article/article-no-plastic-recycling-illustration.png"

        val article4 = Article()
        article4._id = 4
        article4.title = "Food & Beverage Containers"
        article4.thumbnailUrl =
            "https://github.com/atex-org/davishack2021/raw/main/public/images/article/Beverage-Containers.jpg"

        articles.add(article1)
        articles.add(article2)
        articles.add(article3)
        articles.add(article4)
        return articles
    }

    fun item(id: String, title: String, description: String): DailyMission {
        val item = DailyMission()
        item.id = id
        item.title = title
        item.description = description
        return item
    }

    override fun onItemClickListener(view: View, item: DailyMission) {

    }

    override fun onItemClickListener(view: View, item: Article) {

    }

}