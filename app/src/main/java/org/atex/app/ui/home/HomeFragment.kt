package org.atex.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import org.atex.app.R
import org.atex.app.activity.MainActivity
import org.atex.app.model.*
import org.atex.app.ui.adapter.ArticleAdapter
import org.atex.app.ui.adapter.DailyMissionAdapter
import org.atex.app.ui.adapter.RankingAdapter

import java.util.*


class HomeFragment : Fragment(), DailyMissionAdapter.OnItemClickListener,
    ArticleAdapter.OnItemClickListener {
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var dailyMissionAdapter: DailyMissionAdapter
    private lateinit var rankingAdapter: RankingAdapter
    private lateinit var main: MainActivity
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = activity as MainActivity
        homeViewModel = ViewModelProvider(main).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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
        homeViewModel.loadMongodbData()

        homeViewModel.articleUpdated.observe(viewLifecycleOwner,  {
            articleAdapter.insertItems(homeViewModel.article)
        })

        homeViewModel.rankingUpdated.observe(viewLifecycleOwner,  {
            rankingAdapter.insertItems(homeViewModel.ranking)
        })
        createDailyMission()
        return root
    }


    private fun getRandomHexString(numchars: Int): String {
        val r = Random()
        val sb = StringBuffer()
        while (sb.length < numchars) {
            sb.append(Integer.toHexString(r.nextInt()))
        }
        return sb.toString().substring(0, numchars)
    }

    private fun createDailyMission() {
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
    }
//    private fun createArticles(): ArrayList<article> {
//        val articles: ArrayList<article> = ArrayList()
//        val article1 = article()
//        article1._id = ObjectId(getRandomHexString(24))
//        article1.title = "Recycle clean bottles, cans, paper and cardboard"
//        article1.thumbnailUrl =
//            "https://github.com/atex-org/davishack2021/raw/main/public/images/article/article-what-to-recycle.png"
//
//        val article2 = article()
//        article2._id = ObjectId(getRandomHexString(24))
//        article2.title = "Keep food and liquid out of your recycling"
//        article2.thumbnailUrl =
//            "https://github.com/atex-org/davishack2021/raw/main/public/images/article/article-no-food-recycling-illustration.png"
//
//        val article3 = article()
//        article3._id = ObjectId(getRandomHexString(24))
//        article3.title = "No loose plastic bags and no bagged recyclables"
//        article3.thumbnailUrl =
//            "https://github.com/atex-org/davishack2021/raw/main/public/images/article/article-no-plastic-recycling-illustration.png"
//
//        val article4 = article()
//        article4._id = ObjectId(getRandomHexString(24))
//        article4.title = "Food & Beverage Containers"
//        article4.thumbnailUrl =
//            "https://github.com/atex-org/davishack2021/raw/main/public/images/article/Beverage-Containers.jpg"
//
//        articles.add(article1)
//        articles.add(article2)
//        articles.add(article3)
//        articles.add(article4)
//        return articles
//    }

    fun item(id: String, title: String, description: String): DailyMission {
        val item = DailyMission()
        item.id = id
        item.title = title
        item.description = description
        return item
    }

    override fun onItemClickListener(view: View, item: DailyMission) {

    }

    override fun onItemClickListener(view: View, item: article) {

    }

}