package org.atex.app.ui.place

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
import org.atex.app.model.event
import org.atex.app.model.ranking

class PlaceViewModel : ViewModel() {

    private val _event: MutableList<event> = mutableListOf()
    val event: MutableList<event>
        get() = _event

    private val _eventUpdated = MutableLiveData<Boolean>().apply {
        value = false
    }
    val eventUpdated: LiveData<Boolean> = _eventUpdated



    fun loadEvent() {
        val user: User? = atexApp.currentUser()
        val config = SyncConfiguration.Builder(user, PARTITION)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()
        Realm.getInstanceAsync(config, object : Realm.Callback() {
            override fun onSuccess(realm: Realm) {
                Log.v(TAG(), "Successfully opened a realm .")
                realm.where<event>().findAll().sort("date_time", Sort.ASCENDING)
                    .addChangeListener { it ->
                        if (it.size > 0) {
                            _event.addAll(it)
                            when (eventUpdated.value) {
                                true -> {
                                    _eventUpdated.value = false
                                }
                                else -> {
                                    _eventUpdated.value = true
                                }
                            }
                        }
                    }
            }
        })
    }
}