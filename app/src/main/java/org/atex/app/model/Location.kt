package org.atex.app.model

import io.realm.RealmObject

open class Location : RealmObject() {
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var address: String = ""
}