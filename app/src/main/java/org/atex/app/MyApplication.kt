package org.atex.app

import android.app.Application
import android.util.Log
import io.realm.Realm
import io.realm.log.LogLevel
import io.realm.log.RealmLog
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration

lateinit var atexApp: App
inline fun <reified T> T.TAG(): String = T::class.java.simpleName
const val PARTITION = "version_2"
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        atexApp = App(
            AppConfiguration.Builder(BuildConfig.MONGODB_REALM_APP_ID)
                .build())
        // Enable more logging in debug mode
        if (BuildConfig.DEBUG) {
            RealmLog.setLevel(LogLevel.ALL)
        }
        Log.v(TAG(), "Initialized the Realm App configuration for: ${atexApp.configuration.appId}")
    }
}