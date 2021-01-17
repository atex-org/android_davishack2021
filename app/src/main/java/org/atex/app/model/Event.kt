package org.atex.app.model

import io.realm.RealmObject

open class Event() : RealmObject() {
    var _id = 0
    var title: String? = ""
    var eventname: String? = ""
    var thumbnailUrl: String? = ""
    var detail: String? = ""
    var location: Location? = null

    constructor(
        id: Int,
        _title: String,
        _eventname: String,
        _thumbnailUrl: String,
        _detail: String,
        _location: Location?,
    ) : this() {
        _id = id
        title = _title
        eventname = _eventname
        thumbnailUrl = _thumbnailUrl
        detail = _detail
        location = _location
    }
}




