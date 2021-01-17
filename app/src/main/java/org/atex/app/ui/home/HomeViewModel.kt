package org.atex.app.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.where
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration
import org.atex.app.PARTITION
import org.atex.app.TAG
import org.atex.app.atexApp
import org.atex.app.model.article
import org.atex.app.model.ranking

class HomeViewModel : ViewModel() {

    private val _article: MutableList<article> = mutableListOf()
    val article: MutableList<article>
        get() = _article
    private val _articleUpdated = MutableLiveData<Boolean>().apply {
        value = false
    }
    val articleUpdated: LiveData<Boolean> = _articleUpdated


    private val _ranking: MutableList<ranking> = mutableListOf()
    val ranking: MutableList<ranking>
        get() = _ranking

    private val _rankingUpdated = MutableLiveData<Boolean>().apply {
        value = false
    }
    val rankingUpdated: LiveData<Boolean> = _rankingUpdated


    fun loadMongodbData() {
        val user: User? = atexApp.currentUser()
        val config = SyncConfiguration.Builder(user, PARTITION)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()
        Realm.getInstanceAsync(config, object : Realm.Callback() {
            override fun onSuccess(realm: Realm) {
                Log.v(TAG(), "Successfully opened a realm .")
                realm.where<article>().findAll().sort("date", Sort.ASCENDING)
                    .addChangeListener { it ->
                        if (it.size > 0) {
                            _article.addAll(it)
                            when (articleUpdated.value) {
                                true -> {
                                    _articleUpdated.value = false
                                }
                                else -> {
                                    _articleUpdated.value = true
                                }
                            }
                        }
                    }
                realm.where<ranking>().findAll().sort("point", Sort.DESCENDING)
                    .addChangeListener { it ->
                        _ranking.addAll(it)
                        when (rankingUpdated.value) {
                            true -> {
                                _rankingUpdated.value = false
                            }
                            else -> {
                                _rankingUpdated.value = true
                            }
                        }
                    }
            }
        })
    }

}
