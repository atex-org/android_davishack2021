package org.atex.app.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class event_location( var address: String? = null,  var latitude: Double? = null,
    var longitude: Double? = null

): RealmObject() {
}
